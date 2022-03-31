public class Main {
    // ref_input.txt test.txt
    private static final String INPUT_FILE_NAME = "test.txt";

    public static void main(String[] args) {
        Transport tr = new Transport(1000,1000,4);
        Foreman foreman = new Foreman(4,50, tr);
        foreman.getBlocks(INPUT_FILE_NAME);
        foreman.work();


    }
}