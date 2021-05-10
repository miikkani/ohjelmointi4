package vtracker.data;

import java.util.ArrayList;

/**
 * This is a general API for database operations in ValorantTracker.
 */
public interface VtrackerDatabase {

    /**
     * Returns a list of Match objects from the database.
     *
     * @return              list of Match objects
     * @throws Exception    if operation failed.
     */
    ArrayList<Match> getMatches() throws Exception;

    /**
     * Returns latest match from the database.
     *
     * @return              a match object representing one match
     * @throws Exception    if operation failed.
     */
    Match getLatestMatch() throws Exception ;

    /**
     * Adds new match to the database.
     *
     * @param match         a match to be added
     * @throws Exception    if operation failed.
     */
    void addMatch(Match match) throws Exception;


}
