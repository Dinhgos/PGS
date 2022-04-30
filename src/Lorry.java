import java.util.Random;

/**
 * lorry waits for workers to fill its capacity the goes to ferry
 * @author Xuan Toan Dinh
 * @version 01.05.2022
 */
public class Lorry implements Runnable {
    /** ID of lorry */
    private final int id;

    /** maximal cappacity of lorry */
    private final int capLorry;

    /** time for lorry to get to the destination */
    private final int tLorry;

    /** load ferry */
    private final Ferry ferry;

    /** current load on lorry */
    private int curCap = 0;

    /** staring time of lorry / time when was the lorry created */
    private long startTime;

    /**
     * Lorry constructor
     * @param id ID of lorry
     * @param capLorry maximal cappacity of lorry
     * @param tLorry time for lorry to get to the destination
     * @param ferry ferry class
     */
    public Lorry(int id, int capLorry, int tLorry, Ferry ferry) {
        this.id = id;
        this.capLorry = capLorry;
        this.tLorry = tLorry;
        this.ferry = ferry;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * main function of lorry
     * is already full and goes to ferry after that it waits at the ferry
     * after the ferry goes the lorry goes to its destination
     */
    @Override
    public void run() {
        // time it took to load the lorry
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        Main.getData().writeData("Lorry;" + id + ";full;" + time);

        // (0, tLorry) time lorry goes to ferry
        Random rand = new Random();
        int spLorry = rand.nextInt(tLorry);
        startTime = System.currentTimeMillis();

        try {
            Thread.sleep(spLorry);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        Main.getData().writeData("Lorry;" + id + ";go;" + time);

        startTime = System.currentTimeMillis();

        // lorry waiting at ferry
        ferry.synchronize(curCap);

        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        Main.getData().writeData("Lorry;" + id + ";ferry;" + time);

        // ferry is done lorry goes too
        startTime = System.currentTimeMillis();

        // (0, tLorry> time lorry goes to the final destination
        int maxR = tLorry + 1;
        spLorry = rand.nextInt(maxR);

        try {
            Thread.sleep(spLorry);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // lorry is at the end and done
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        Main.getData().writeData("Lorry;" + id + ";end;" + time);
    }

    /**
     * increaces its current load by one ore
     * @return true if capacity is full / false if capacity is not full
     */
    public synchronized boolean incCap() {
        this.curCap++;

        if (curCap == capLorry) {
            return true;
        }

        return false;
    }
}
