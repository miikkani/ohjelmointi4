package vtracker.data;


import java.util.ArrayList;

/**     !!Work In Progress!!
 *
 * This class calculates statistics from given match database.
 *
 * TODO:
 *  - Make this a factory class etc.?
 *
 */
public class StatsBuilder {
    private double totalWP;
    private double overallWinP;

    private VtrackerDatabase db;
    private ArrayList<Match> matches;


    /**
     * Maybe obsolete.
     */
    public StatsBuilder(VtrackerDatabase db) {
        this.db = db;

    }


    /**
     *
     */
    /**
     * Maybe...
     */
    public double calculateWinPercent() {

        return 0.0;
    }


    /**
     * Calculates all percentages and updates internal data
     * structures. Make this run in own thread.
     *
     */
    public void updateStats() {


    }


    /**
     * Maybe...
     */
    public double getTotalWinPercentage() {
        return totalWP;
    }


    /**
     * Gets latest data from database.
     */
    private void refresh() {
       try {
           matches = db.getMatches();
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error with database!");
       }
    }










}
