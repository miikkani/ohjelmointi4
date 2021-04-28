/**
 * TODO: poista tama luokka kokonaan. agentti stringina
 */
package vtracker.data;

public class Agent {
    private String name;
    private double winp;


    public Agent(String name) {
        this.name = name;
    }

    public Agent(String name, double winp) {
        this.name = name;
        this.winp = winp;
    }


    /**
     * Returns agent's name as a String.
     *
     * @return name  the name of the agent
     */
    public String getName() {return this.name;}

    /**
     * Returns agent's win percent as double.
     *
     * @return winp  agent's win percent
     */
    public double getWinp() {return this.winp;}


    /**
     * TODO
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * TODO
     * @param winp
     */
    public void setWinp(double winp) {
        this.winp = winp;
    }

    @Override
    public String toString() {
       return "Agent: " + name + " winp: " + winp;
    }

}
