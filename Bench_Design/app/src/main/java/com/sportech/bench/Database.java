package com.sportech.bench;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database {

    public static User currentUser;
    public static Match currentMatch;

    static FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    static DatabaseReference userReference = rootNode.getReference("UserInfo");
    static DatabaseReference matchReference = rootNode.getReference("MatchInfo");

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
    public interface StringListCallback {
        void onCallback(ArrayList<String> value);
    }
    public interface IntCallback{
        void onCallback(int value);
    }

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
        userReference.child(userInfo.GetUserName()).child("JoinedMatches").child(matchInfo.GetMatchID()).setValue(matchInfo.GetMatchID());
    }
    public static void AddPlayerUnderMatch(User userInfo, Match matchInfo){
        matchReference.child(matchInfo.GetMatchID()).child("JoinedPlayers").child(userInfo.GetUserName()).setValue(userInfo.GetUserName());
    }
    public static void RemoveMatchUnderPlayer(User userInfo, Match matchInfo){
        userReference.child(userInfo.GetUserName()).child("JoinedMatches").child(matchInfo.GetMatchID()).removeValue();
    }
    public static void RemovePlayerUnderMatch(User userInfo, Match matchInfo){
        matchReference.child(matchInfo.GetMatchID()).child("JoinedPlayers").child(userInfo.GetUserName()).removeValue();
    }


    public static void GetAllUserInfo(UserListCallback callback){
         userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> users = new ArrayList<>();
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
                ArrayList<Match> matches = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    matches.add(dsp.getValue(Match.class));
                }
                callback.onCallback(matches);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public static void UserExists(String username, BooleanCallback callback){
        GetAllUserInfo(value -> {
            boolean exists = false;
            for(User u: value){
                if(u.GetUserName().equals(username)){
                    exists = true;
                    break;
                }
            }
            callback.onCallback(exists);
        });
    }
    public static void GetUserInfo(String username, UserCallback callback){
        GetAllUserInfo(value -> {
            User user = null;
            for(User u: value){
                if(u.GetUserName().equals(username)){
                    user = u;
                    break;
                }
            }
            callback.onCallback(user);
        });
    }
    public static void GetMatchInfo(String matchID, MatchCallback callback){
        GetAllMatchInfo(value -> {
            Match match = null;
            for(Match m: value){
                if(m.GetMatchID().equals(matchID)){
                    match = m;
                    break;
                }
            }
            callback.onCallback(match);
        });
    }
    public static void GetJoinedMatches(String userName, StringListCallback callback){
        userReference.child(userName).child("JoinedMatches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> matches = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    matches.add(dsp.getValue(String.class));

                }
                callback.onCallback(matches);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public static void GetJoinedPlayers(String matchID, StringListCallback callback){
        matchReference.child(matchID).child("JoinedPlayers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> users = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    users.add(dsp.getValue(String.class));

                }
                callback.onCallback(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public static void GetRequiredPlayerCount(String matchID, IntCallback callback){
        matchReference.child(matchID).child("RequiredPlayerCount").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer count = dataSnapshot.getValue(Integer.class);
                if(count != null){
                    callback.onCallback(count);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public static void SetRequiredPlayerCount(String matchID, int newCount){
        matchReference.child(matchID).child("RequiredPlayerCount").setValue(newCount);
    }
    public static void GetIfUserJoinedMatch(User user, Match match, BooleanCallback callback){
        userReference.child(user.GetUserName()).child("JoinedMatches").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean joined = false;
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    if(match.GetMatchID().equals(dsp.getValue(String.class))){
                        joined = true;
                    }
                }
                callback.onCallback(joined);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}

