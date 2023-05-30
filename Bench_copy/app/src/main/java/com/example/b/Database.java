package com.example.b;

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

    public static void AddUser(UserInfo info){
        userReference.child(info.GetUserName()).setValue(info);
    }
    public static void AddMatch(MatchInfo info){
        matchReference.child(info.GetMatchID()).setValue(info);
    }
    public static void RemoveUser(UserInfo info){
        userReference.child(info.GetUserName()).removeValue();
    }
    public static void RemoveMatch(MatchInfo info){
        matchReference.child(info.GetMatchID()).removeValue();
    }
    public static HashSet<UserInfo> GetAllUserInfo(){
        HashSet<UserInfo> users = new HashSet<UserInfo>();

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    users.add((UserInfo)dsp.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return users;
    }
    public static HashSet<MatchInfo> GetAllMatchInfo(){
        HashSet<MatchInfo> matches = new HashSet<MatchInfo>();

        matchReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    matches.add((MatchInfo) dsp.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return matches;
    }
    public static boolean UserExists(UserInfo user){
        final boolean[] userExists = new boolean[1];
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userExists[0] = snapshot.hasChild(user.GetUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        return userExists[0];
    }
}

