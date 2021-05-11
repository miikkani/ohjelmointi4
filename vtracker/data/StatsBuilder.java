package vtracker.data;


import java.util.ArrayList;
import java.util.Hashtable;

/**     !!Work In Progress!!
 *
 * This class calculates statistics from given match database.
 *
 * TODO:
 *  - Make this a factory class etc.?
 *
 */
public class StatsBuilder {
    private double overallWinP;

    private String[] agents;
    private VtrackerDatabase db;
    private ArrayList<Match> matches;

    private Hashtable<String, Double> winpercentages;



    /**
     * Main constructor.
     */
    public StatsBuilder(VtrackerDatabase db, String[] agents) {
        this.db = db;
        this.agents = agents;

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
        for(Match m : matches);



    }


    /**
     * Maybe...
     */
    public double getOverallWinPercentage() {
        return overallWinP;
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
