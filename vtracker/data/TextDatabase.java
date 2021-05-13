package vtracker.data;

import java.io.*;
import java.util.ArrayList;


/**
 * Database using text file. Converts {@link Match} objects to and from text
 * representation and implements database operations.
 *
 */
public class TextDatabase implements VtrackerDatabase {
    private static TextDatabase db;
    private final File file;

    private TextDatabase(String path){
        this.file = new File(path);
        if(!file.exists()){
            try { file.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.print("Cannot create database! ");
                System.out.println(ioe.getMessage());
            }
        }
    }

    /**
     * Returns a {@link TextDatabase} instance. Creates database object once and
     * returns a reference if database object already exists.
     *
     * @param path      a string representing the path of the database file
     * @return          a TextDatabase instance
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
                m.add(new Match(Long.parseLong(tokens[0]),tokens[1], MatchResult.valueOf(tokens[2])));
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
            if(matches.size() != 0) {
                return matches.get(matches.size() - 1);
            } else {
                return null;
            }
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

    @Override
    public boolean deleteLatestMatch() {
        try (RandomAccessFile f = new RandomAccessFile(this.file, "rw")) {
            long length = f.length() - 1;
            byte b;
            do {
                length -= 1;
                f.seek(length);
                b = f.readByte();
            } while (b != 10 && length > 0);
            if (length == 0) {
                f.setLength(length);
            } else {
                f.setLength(length + 1);
            }
            System.out.println("Deleted last match in " + this.file);
            return true;

        } catch (IOException ioe) {
            System.out.println("Could not delete latest match.");
            return false;
        }
    }


}
