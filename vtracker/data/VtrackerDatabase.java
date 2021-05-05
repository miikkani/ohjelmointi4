package vtracker.data;

import java.util.ArrayList;

public interface VtrackerDatabase {

    ArrayList<Match> getMatches();
    Match getMatch(int id);


}
