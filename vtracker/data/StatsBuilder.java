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
        winpercentages = new Hashtable<>(agents.length);
        for(String agent : agents) {
            winpercentages.put(agent, 0.0);
        }

    }


    /**
     *
     */
    /**
     * Maybe...
     */
    private double calculateWinPercent() {


        return 0.0;
    }


    /**
     * Calculates all percentages and updates internal data
     * structures. Make this run in own thread.
     *
     */
    public void calculateStats() {
        int total = matches.size();

        for(String agent : agents) {
            double winp = 0.0;
            int wins = 0;
            for(Match m : matches) {
                if(m.getResult() == MatchResult.WIN) {
                    wins++;
                }
            }
            winp = ((double)wins)/ ((double)total);
            winpercentages.put(agent, winp);
        }
    }

    /**
     * Returns a collection of agents and their current winpercentages.
     *
     * @return          a Hashtable containing agents and winpercentages.
     */
    public Hashtable<String, Double> getStats() {
        return winpercentages;
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
    public void refresh() {
       try {
           matches = db.getMatches();
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error with database!");
       }
    }










}
