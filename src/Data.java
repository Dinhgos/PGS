import java.io.*;
import java.util.LinkedList;

public class Data {
    private int blocks = 0;
    private int ores = 0;
    private final String outFileName;
    private final String inFilePath;

    public Data(String outFileName, String inFilePath) {
        this.outFileName = outFileName;
        this.inFilePath = inFilePath;
    }

    public LinkedList<Integer> loadData() {
        LinkedList<Integer> data = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] sources = line.split(" ");
                blocks += sources.length;
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

    public void writeData(String msg) {
        long endTime = System.currentTimeMillis();
        long time = endTime - Main.getStartTime();

        String out = time + ";" + msg;

        try {
            FileWriter myWriter = new FileWriter(outFileName);
            myWriter.write(out);
            myWriter.close();
            System.out.println("Successfully wrote " + msg + " to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void createFile() {
        try {
            File myObj = new File(outFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating output file.");
            System.exit(1);
        }
    }

    public int getBlocks() {
        return blocks;
    }

    public int getOres() {
        return ores;
    }
}
