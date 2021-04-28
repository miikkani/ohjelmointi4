package vtracker.data;

public class Match {
    private static int count;
    private int key;
    private final String agent;
    private final MatchResult result;

    public Match(String agent, MatchResult result) {
        this.agent = agent;
        this.result = result;
        count++;
        key = count;
    }


    public String getAgent(){return this.agent;}
    public MatchResult getMatchResult(){return this.result;}
    public int getKey(){return key;}

    @Override
    public String toString() {
        return "Match #" + key
                + ": " + agent
                + ", " + result;
    }
}
