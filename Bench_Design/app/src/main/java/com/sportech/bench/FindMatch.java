package com.sportech.bench;

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
    private static int CompareCriterias(Match match, Player player){
        if(!IsAppropriateDate(player.GetEarliestTime(), player.GetLatestTime(), match.GetTime())){
            return -1;
        }

        return CompareAddresses(match.GetAddress(), player.GetAddress());
    }
    public static ArrayList<Match> FindMatches(Player player, int matchCount){
        ArrayList<Match> AppropriateMatches = new ArrayList<Match>(matchCount);
        TreeMap<Integer, Match> MatchesAndSimilarities = new TreeMap<Integer, Match>();

        HashSet<Match> matches = Database.GetAllMatchInfo();

        for(Match match: matches){
            int Similarity = CompareCriterias(match, player);

            if(Similarity != -1){
                MatchesAndSimilarities.put(Similarity, match);
            }
        }

        AppropriateMatches.addAll(MatchesAndSimilarities.values());

        return AppropriateMatches;
    }
}