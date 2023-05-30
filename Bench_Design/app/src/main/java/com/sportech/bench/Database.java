package com.sportech.bench;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;

public class Database {
    static FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    static DatabaseReference userReference = rootNode.getReference("UserInfo");
    static DatabaseReference matchReference = rootNode.getReference("MatchInfo");

    public static void AddUser(User info){
        userReference.child(info.GetUserName()).setValue(info);
    }
    public static void AddMatch(Match info){
        matchReference.child(info.GetMatchID()).setValue(info);
    }
    public static void RemoveUser(User info){
        userReference.child(info.GetUserName()).removeValue();
    }
    public static void RemoveMatch(Match info){
        matchReference.child(info.GetMatchID()).removeValue();
    }
    public static HashSet<User> GetAllUserInfo(){
        HashSet<User> users = new HashSet<User>();

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    users.add((User)dsp.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return users;
    }
    public static HashSet<Match> GetAllMatchInfo(){
        HashSet<Match> matches = new HashSet<Match>();

        matchReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    matches.add((Match) dsp.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return matches;
    }
    public static boolean UserExists(String userName){
        final boolean[] userExists = new boolean[1];
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userExists[0] = snapshot.hasChild(userName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        return userExists[0];
    }
}

