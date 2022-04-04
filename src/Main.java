public class Main {
    // ref_input.txt test.txt
    // TODO maybe not static
    private static String INPUT_FILE_NAME;
    private static String OUTPUT_FILE_NAME;
    private static int cWorker = -1;
    private static int tWorker = -1;
    private static int capLorry = -1;
    private static int tLorry = -1;
    private static int capFerry = -1;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        loadInput(args);
        checkInputs();

        Transport tr = new Transport(capLorry,tLorry,capFerry);
        //tr.work();
        Foreman foreman = new Foreman(cWorker,tWorker, tr);
        foreman.getBlocks(INPUT_FILE_NAME);
        foreman.work();

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    private static void checkInputs() {
        boolean ss = false;
        if (INPUT_FILE_NAME == null) {
            ss = true;
        }
        if (OUTPUT_FILE_NAME == null) {
            ss = true;
        }
        if (cWorker == -1) {
            ss = true;
        }
        if (tWorker == -1) {
            ss = true;
        }
        if (capLorry == -1) {
            ss = true;
        }
        if (tLorry == -1) {
            ss = true;
        }
        if (capFerry == -1) {
            ss = true;
        }

        if (ss) {
            System.out.println("Missing input");
            System.exit(1);
        }
    }

    private static void loadInput(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            String in = args[i];
            String in2 = args[i + 1];
            switch (in) {
                case "-i" -> INPUT_FILE_NAME = in2;
                case "-o" -> OUTPUT_FILE_NAME = in2;
                case "-cWorker" -> cWorker = strToInt(in2);
                case "-tWorker" -> tWorker = strToInt(in2);
                case "-capLorry" -> capLorry = strToInt(in2);
                case "-tLorry" -> tLorry = strToInt(in2);
                case "-capFerry" -> capFerry = strToInt(in2);
            }
        }
    }

    private static int strToInt(String sNum) {
        int iNum = 0;

        try {
            iNum = Integer.parseInt(sNum);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Could not parse string to int.");
            System.exit(1);
        }

        return iNum;
    }
}