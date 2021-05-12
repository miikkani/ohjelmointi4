package vtracker.data;


/**
 * A match class. This class describes one match including a timestamp
 * when match was created.
 */
public class Match {
    private final String agent;
    private final MatchResult result;
    private final long timestamp;

    public Match(String agent, MatchResult result) {
        this.agent = agent;
        this.result = result;
        timestamp = System.currentTimeMillis();
    }

    public Match(long timestamp, String agent, MatchResult result) {
        this.agent = agent;
        this.result = result;
        this.timestamp = timestamp;
    }



    public String getAgent(){return this.agent;}
    public MatchResult getResult(){return this.result;}
    public long getTimestamp(){return this.timestamp;}

    @Override
    public String toString() {
        return timestamp
                + ":" + agent
                + ":" + result;
    }
}
