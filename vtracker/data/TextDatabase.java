package vtracker.data;

import java.io.File;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

import java.util.ArrayList;

import java.io.IOException;


public class TextDatabase implements VtrackerDatabase {
    private static TextDatabase db;
    private final File file;

    private TextDatabase(String path){
        this.file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.print("Cannot create database! ");
                ioe.getMessage();
            }
        }
    }

    /**
     * Creates new database or returns a reference if it already exists.
     *
     * @param path      the location of the database file
     * @return          an instance of the database
     */
    public static TextDatabase getInstance(String path) {
        if(db == null) {
            db = new TextDatabase(path);
        }
        return db;
    }

    @Override
    public ArrayList<Match> getMatches() throws IOException {
        String match;
        String[] tokens;

        ArrayList<Match> m = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(this.file))){
            while((match = in.readLine()) != null) {
                tokens = match.split(":");
                m.add(new Match(tokens[1], MatchResult.valueOf(tokens[2])));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("IO error! Could not read database!");
            throw ioe;
        }

        return m;
    }

    @Override
    public Match getLatestMatch() throws IOException {
        try {
            ArrayList<Match> matches = getMatches();
            return matches.get(matches.size()-1);
        } catch(IOException ioe) {
            throw ioe;
        }


    }

    @Override
    public boolean addMatch(Match match) {
        try(FileWriter out = new FileWriter(this.file, true)){
            out.write(match.toString() + "\n");
            System.out.println("Added match: " + match);
            return true;

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Could not add match. IO error.");
            return false;
        }


    }

    @Override
    public boolean deleteDatabase() {
        try(FileWriter out = new FileWriter(this.file, false)){
            out.write("");
            System.out.println("Deleted database in " + this.file);
            return true;

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Could not delete database.");
            return false;
        }
    }


}
