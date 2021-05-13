package vtracker.data;


/**
 * A Valorant match. This class describes one match including a timestamp
 * when match was created. Also the name of the agent used and the result of
 * a match.
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

    /**
     * Returns a String object representing agent's name.
     *
     * @return          a string representation of agent name
     */
    public String getAgent(){return this.agent;}

    /**
     * Returns a {@link MatchResult} object representing match result.
     *
     * @return          result of this match
     */
    public MatchResult getResult(){return this.result;}

    /**
     * Returns a timestamp of this match as a long value. Timestamp is a
     * point in time as a milliseconds.
     *
     * @return          a long representing time in milliseconds
     */
    public long getTimestamp(){return this.timestamp;}

    @Override
    public String toString() {
        return timestamp
                + ":" + agent
                + ":" + result;
    }
}
