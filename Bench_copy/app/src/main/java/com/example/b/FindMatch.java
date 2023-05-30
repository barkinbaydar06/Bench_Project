package com.example.b;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class FindMatch {
    private static int CompareAddresses(String matchInfoAddress, String playerInfoAddress){
        String[] MatchAddressWords = matchInfoAddress.split("\\s+");
        String[] PlayerAddressWords = playerInfoAddress.split("\\s+");

        int similarityscore = 0;
        for (String matchAddressWord : MatchAddressWords) {
            for (String playerAddressWord : PlayerAddressWords) {
                if (matchAddressWord.equals(playerAddressWord)) {
                    similarityscore++;
                }
            }
        }

        return similarityscore;
    }
    private static boolean IsAppropriateDate(Calendar earliest, Calendar latest, Calendar matchTime){
        return matchTime.after(earliest) && matchTime.before(latest);
    }
    private static boolean IsAppropriatePosition(HashSet<Position> desiredPositions, ArrayList<Position> requiredPositions){
        for(Position pos : desiredPositions){
            if(requiredPositions.contains(pos)){
                return true;
            }
        }
        return false;
    }
    private static int CompareCriterias(MatchInfo match, PlayerInfo player){
        if(!IsAppropriateDate(player.GetEarliestTime(), player.GetLatestTime(), match.GetTime())){
            return -1;
        }
        if(!IsAppropriatePosition(player.GetPositions(), match.GetPositions())){
            return -1;
        }

        return CompareAddresses(match.GetAddress(), player.GetAddress());
    }
    public static ArrayList<MatchInfo> FindMatches(PlayerInfo player, int matchCount){
        ArrayList<MatchInfo> AppropriateMatches = new ArrayList<MatchInfo>(matchCount);
        TreeMap<Integer, MatchInfo> MatchesAndSimilarities = new TreeMap<Integer, MatchInfo>();

        HashSet<MatchInfo> matches = Database.GetAllMatchInfo();

        for(MatchInfo match: matches){
            int Similarity = CompareCriterias(match, player);

            if(Similarity != -1){
                MatchesAndSimilarities.put(Similarity, match);
            }
        }

        AppropriateMatches.addAll(MatchesAndSimilarities.values());

        return AppropriateMatches;
    }
}
