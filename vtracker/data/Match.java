package vtracker.data;

public class Match {
    private final String agent;
    private final MatchResult result;

    public Match(String agent, MatchResult result) {
        this.agent = agent;
        this.result = result;
    }


    public String getAgent(){return this.agent;}
    public MatchResult getResult(){return this.result;}

    @Override
    public String toString() {
        return this.hashCode()
                + ":" + agent
                + ":" + result;
    }
}
