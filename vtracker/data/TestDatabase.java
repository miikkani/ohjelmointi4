package vtracker.data;

/**
 * Simple implementation to get sampledata to UI
 * components.
 */
public class TestDatabase implements AbstractDatabase {
    private Match match;

    public TestDatabase() {
       match = new Match("Astra", MatchResult.WIN);
    }

   public Match getMatch(int id) {
        return this.match;
   }
}
