public class Main {
    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadData("test.txt");

        Foreman foreman = new Foreman(dataLoader.getData(), 4);
    }
}