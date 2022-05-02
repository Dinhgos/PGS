import java.io.*;
import java.util.LinkedList;

/**
 * Data class handles reading from input file, writing to output file and creating of output file
 * @author Xuan Toan Dinh
 * @version 02.05.2022
 */
public class Data {
    /** amount of blocks in input */
    private int blocks = 0;

    /** amount of ores/sources in input */
    private int ores = 0;

    /** output file */
    private final String outFileName;

    /** input file */
    private final String inFilePath;

    /**
     * cornstructor of data class
     * @param outFileName output file
     * @param inFilePath input file
     */
    public Data(String outFileName, String inFilePath) {
        this.outFileName = outFileName;
        this.inFilePath = inFilePath;
    }

    /**
     * reads from input file and parses the data into a LinkedList
     * @return linked list which contains all the data from input file
     */
    public LinkedList<Integer> loadData() {
        LinkedList<Integer> data = new LinkedList<>();

        // reads from file
        try (BufferedReader br = new BufferedReader(new FileReader(inFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // splits line into array of string to get number of words/blocks
                line = line.replaceAll("\\s+"," ");
                String[] sources = line.split(" ");
                blocks += sources.length;

                // counts the amount of ores in a block and adds it into the list as a integer
                for (String source : sources) {
                    data.add(source.length());
                    ores += source.length();
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read from file.");
            System.exit(1);
        }

        return data;
    }

    /**
     * writes into the output file
     * @param msg string to write into output file
     */
    public synchronized void writeData(String msg) {
        // calculates time to insert timestamp into output
        long endTime = System.currentTimeMillis();
        long time = endTime - Main.getStartTime();

        // timestamp before each message
        String out = time + ";" + msg;

        // writing into the file
        // it appends into the file meaning if there is something in the file this writes it bellow it
        try {
            FileWriter fw = new FileWriter(outFileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(out);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * creates the output file
     * if there already is a file with the same name this also overwrites it into an empty file
     */
    public void createFile() {
        try {
            File myObj = new File(outFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                // empties the file if it exists
                FileWriter myWriter = new FileWriter(outFileName);
                myWriter.write("");
                myWriter.close();
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating output file.");
            System.exit(1);
        }
    }

    /**
     * getter for the number of blocks
     * @return number of blocks
     */
    public int getBlocks() {
        return blocks;
    }

    /**
     * getter for the number of ores
     * @return number of ores
     */
    public int getOres() {
        return ores;
    }
}
