package vtracker.data;

public class Match {
    private static int id;
    private final Agent agent;
    private final MatchResult result;

    public Match(String agent, MatchResult result) {
        id = ++id;
        this.agent = new Agent(agent);
        this.result = result;
    }


    public String toString() {
        return "Match #" + id
                + ": " + agent
                + ", " + result;
    }
}
