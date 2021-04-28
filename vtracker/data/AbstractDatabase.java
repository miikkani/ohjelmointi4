package vtracker.data;

import java.util.ArrayList;

public interface AbstractDatabase {

    ArrayList<Match> getMatches();
    Match getMatch(int id);


}
