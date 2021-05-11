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
     * Adds new match to the database. Returns true if operation was
     * successful and false if there was a problem.
     *
     * @param match         a match to be added
     * @return              a boolean of whether operation completed
     */
    boolean addMatch(Match match);

    /**
     * Deletes database. Returns true if operation was
     * successful and false if there was a problem.
     *
     *
     * @return              a boolean of whether operation completed
     */
    boolean deleteDatabase() ;



}
