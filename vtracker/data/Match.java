package vtracker.data;


/**
 * A match class. This class describes one match including a timestamp
 * when match was created. The name of the agent used and the result of
 * this match.
 */
public class Match {
    private final String agent;
    private final MatchResult result;
    private final long timestamp;

    /**
     * Constructs a Match and initializes timestamp to current time.
     *
     * @param agent         name of the agent
     * @param result        result of the match
     */
    public Match(String agent, MatchResult result) {
        this.agent = agent;
        this.result = result;
        timestamp = System.currentTimeMillis();
    }

    /**
     * Constructs a Match to specific point in time.
     *
     * @param timestamp     point in time in milliseconds
     * @param agent         name of the agent
     * @param result        result of the match
     */
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
