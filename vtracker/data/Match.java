package vtracker.data;


public class Match {
    private final String agent;
    private final MatchResult result;
    private final long timestamp;

    public Match(String agent, MatchResult result) {
        this.agent = agent;
        this.result = result;
        timestamp = System.currentTimeMillis();
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
