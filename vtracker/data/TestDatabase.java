package vtracker.data;

import java.util.ArrayList;

/**
 * Simple implementation to get sampledata to UI
 * components.
 */
public class TestDatabase implements VtrackerDatabase {
    private final ArrayList<Match> match;
    private static TestDatabase tdb;

    private TestDatabase() {
        match = new ArrayList<>();

        //Fill database. This is temporary.
        match.add(new Match("Breach",MatchResult.WIN));
        match.add(new Match("Cypher",MatchResult.DRAW));
        match.add(new Match("Sage",MatchResult.LOSS));

    }

    /**
     * Initializes new TestDatabase with test data or returns a reference if
     * database already exists.
     *
     * @return tdb    a reference to a TestDatabase object
     */
    public static TestDatabase getInstance() {
        if(tdb == null) {
            tdb = new TestDatabase();
        }
        return tdb;
    }




    @Override
    public ArrayList<Match> getMatches() {
        return this.match;
    }

    @Override
    public Match getLatestMatch() {
        return new Match("Sage", MatchResult.LOSS);
    }

    @Override
    public boolean addMatch(Match match) {
        System.out.println("Printing from addMatch()");
        return true;

    }

    public boolean deleteDatabase(){
        System.out.println("Printing from deleteDatabse()");
        return true;
    }

    public boolean deleteLatestMatch(){
        System.out.println("Printing from deleteLatestMatch()");
        return true;
    }

    public Match getMatch(int id) {
        return match.get(id-1);
    }
}
