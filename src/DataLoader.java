import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class DataLoader {
    private LinkedList<Integer> data = new LinkedList<>();

    public void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] sources = line.split(" ");
                for (String source : sources) {
                    data.add(source.length());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read from file.");
            System.exit(1);
        }

        System.out.println("Done loading data.");
    }

    public LinkedList<Integer> getData() {
        return data;
    }
}
