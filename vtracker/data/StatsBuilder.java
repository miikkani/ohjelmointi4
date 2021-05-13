package vtracker.data;


import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This class provides methods for calculating win percentages. Uses
 * {@link VtrackerDatabase} and String array of agent names for operations.
 */
public class StatsBuilder {
    private final String[] agents;
    private final VtrackerDatabase db;
    private ArrayList<Match> matches;
    private final Hashtable<String, Double> winpercentages;

    /**
     * Class constructor specifying database and list of agents.
     *
     * @param db        a database containing matches
     * @param agents    a string array containing name of agents
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
     * Calculates win percentages for all agents and updates internal data
     * structure.
     */
    public void calculateStats() {
        for (String agent : agents) {
            int total = 0;
            double winp = 0.0;
            int wins = 0;
            for (Match m : matches) {
                if (m.getAgent().equals(agent)) {
                    total++;
                    if (m.getResult() == MatchResult.WIN &&
                            m.getAgent().equals(agent)) {
                        wins++;
                    }
                }
            }
            if (total != 0) winp = ((double) wins) / ((double) total);
            winpercentages.put(agent, winp);
        }
    }

    /**
     * Returns a Hashtable of agents and their win percentages.
     *
     * @return          a hashtable containing agents and win percentages
     */
    public Hashtable<String, Double> getStats() {
        return winpercentages;
    }

    /**
     * Returns overall win percentage. Calculates win percentage of current
     * matches and returns the result as a double representation.
     *
     * @return          a double representing win percentage
     */
    public double getOverallWinPercentage() {
            int total = matches.size();
            double winp = 0.0;
            int wins = 0;
            for(Match m : matches) {
                    if (m.getResult() == MatchResult.WIN) {
                        wins++;
                    }
                }
            if(total != 0)winp = ((double)wins)/ ((double)total);
            return winp;
    }

    /**
     * Updates internal data structure of matches by querying database.
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