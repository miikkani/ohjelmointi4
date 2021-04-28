package vtracker.data;

import java.util.Map;
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
    private double totalWP;
    private Map<Agent,Double> agentsWinP;
    private double overallWinP;

    private AbstractDatabase db;


    /**
     * Maybe obsolete.
     */
    public StatsBuilder() {

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













}
