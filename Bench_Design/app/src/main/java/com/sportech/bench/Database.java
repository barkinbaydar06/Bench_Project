package com.sportech.bench;

import android.content.DialogInterface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

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
    public static void AddMatchUnderPlayer(User userInfo, Match matchInfo){
        userReference.child(userInfo.GetUserName()).child("JoinedMatches").setValue(matchInfo.GetMatchID());
    }
    public static void AddPlayerUnderMatch(User userInfo, Match matchInfo){
        matchReference.child(matchInfo.GetMatchID()).child("JoinedPlayers").setValue(userInfo.GetUserName());
    }


    public static void GetAllUserInfo(UserListCallback callback){
         userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> users = new ArrayList<User>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    users.add((dsp.getValue(User.class)));
                }
                callback.onCallback(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public static void GetAllMatchInfo(MatchListCallback callback){
        matchReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Match> matches = new ArrayList<Match>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    matches.add(dsp.getValue(Match.class));
                }
                callback.onCallback(matches);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public interface UserListCallback {
        void onCallback(ArrayList<User> value);
    }
    public interface MatchListCallback {
        void onCallback(ArrayList<Match> value);
    }
    public interface UserCallback {
        void onCallback(User value);
    }
    public interface MatchCallback {
        void onCallback(Match value);
    }
    public interface BooleanCallback {
        void onCallback(boolean value);
    }
    public static void UserExists(String username, BooleanCallback callback){
        GetAllUserInfo(new UserListCallback() {
            @Override
            public void onCallback(ArrayList<User> value) {
                boolean exists = false;
                for(User u: value){
                    if(u.GetUserName().equals(username)){
                        exists = true;
                        break;
                    }
                }
                callback.onCallback(exists);
            }
        });
    }
    public static void GetUserInfo(String username, UserCallback callback){
        GetAllUserInfo(new UserListCallback() {
            @Override
            public void onCallback(ArrayList<User> value) {
                User user = null;
                for(User u: value){
                    if(u.GetUserName().equals(username)){
                        user = u;
                        break;
                    }
                }
                callback.onCallback(user);
            }
        });
    }
}

