package vtracker.data;

import java.util.ArrayList;

/**
 * Provides general interface for database implementations. This interface
 * is used in all communications between application and database.
 */
public interface VtrackerDatabase {

    /**
     * Returns an ArrayList of {@link Match} objects from the database.
     *
     * @return              an ArrayList of Match objects
     * @throws Exception    if operation failed.
     */
    ArrayList<Match> getMatches() throws Exception;

    /**
     * Returns a {@link Match} object representing latest match from
     * the database.
     *
     * @return              a match object representing one match
     * @throws Exception    if operation failed.
     */
    Match getLatestMatch() throws Exception ;

    /**
     * Adds new match to the database. Returns true if operation was
     * successful.
     *
     * @param match         a match to be added
     * @return              true if operation was successful
     */
    boolean addMatch(Match match);

    /**
     * Deletes database. Returns true if operation was
     * successful.
     *
     *
     * @return              true if operation was successful
     */
    boolean deleteDatabase();

    /**
     * Deletes latest match. Returns true if operation was
     * successful.
     *
     *
     * @return              true if operation was successful
     */
    boolean deleteLatestMatch() ;



}
