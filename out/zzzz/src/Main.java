/**
 * Main class
 * @author Xuan Toan Dinh
 * @version 05.04.2022
 */
public class Main {
    /** input file */
    private static String INPUT_FILE_NAME;

    /** output file */
    private static String OUTPUT_FILE_NAME;

    /** number of workers */
    private static int cWorker = -1;

    /** time for worker to mine an ore */
    private static int tWorker = -1;

    /** maximal capacity of lorry */
    private static int capLorry = -1;

    /** time for lorry to get to the destination */
    private static int tLorry = -1;

    /** maximal capacity of ferry */
    private static int capFerry = -1;

    /** class Data wich handels file reading/writing/creating */
    private static Data data;

    /** time when the program starts */
    private static long startTime;

    /**
     * main function
     * @param args input paramethers
     */
    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        loadInput(args);
        checkInputs();

        data = new Data(OUTPUT_FILE_NAME, INPUT_FILE_NAME);
        data.createFile();

        Transport tr = new Transport(capLorry,tLorry,capFerry);
        Foreman foreman = new Foreman(cWorker,tWorker, tr);
        foreman.getBlocks();
        foreman.work();

        long endTime = System.currentTimeMillis();
        System.out.println("This program ran for " + (endTime - startTime)/1000 + " seconds");
    }

    /**
     * checks if inputs are valid
     * if not valid program exits
     */
    private static void checkInputs() {
        boolean ss = false;
        if (INPUT_FILE_NAME == null) {
            ss = true;
        }
        if (OUTPUT_FILE_NAME == null) {
            ss = true;
        }
        if (cWorker < 1) {
            ss = true;
        }
        if (tWorker < 1) {
            ss = true;
        }
        if (capLorry < 1) {
            ss = true;
        }
        if (tLorry < 1) {
            ss = true;
        }
        if (capFerry < 1) {
            ss = true;
        }

        if (ss) {
            System.out.println("Missing/Wrong input");
            System.exit(1);
        }
    }

    /**
     * parses individual inputs into given variables
     * @param args input string
     */
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

    /**
     * converts string input into integer
     * if the input can not be parsed then the program exits
     * @param sNum string input number
     * @return integer value of input
     */
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

    /**
     * getter for data class
     * @return data class
     */
    public static Data getData() {
        return data;
    }

    /**
     * gets the starting time
     * @return value of starting time
     */
    public static long getStartTime() {
        return startTime;
    }
}