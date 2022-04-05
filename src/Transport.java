import java.util.ArrayList;

/**
 * handles lorrys and ferry
 * @author Xuan Toan Dinh
 * @version 05.04.2022
 */
public class Transport {
    /** maximal capacity of lorry */
    private final int capLorry;

    /** time for lorry to get to the destination */
    private final int tLorry;

    /** current lorry that workers are loading on */
    private Lorry lorry;

    /** ferry passed to lorry, get the total ammount of ores transported*/
    private final Ferry ferry;

    /** array of lorrys that are going to ferry */
    private final ArrayList<Thread> thLorry;

    /** number of lorrrys created */
    private int lorCount = 0;

    /**
     * Transport constructor
     * @param capLorry maximal capacity of lorry
     * @param tLorry time for lorry to get to the destination
     * @param capFerry maximal capacity of ferry
     */
    public Transport(int capLorry, int tLorry, int capFerry) {
        this.capLorry = capLorry;
        this.tLorry = tLorry;
        this.thLorry = new ArrayList<>();
        this.ferry = new Ferry(capFerry);
        this.lorry = new Lorry(lorCount, capLorry, tLorry, ferry);
        lorCount++;
    }

    /**
     * workers load ores on lorry
     */
    public synchronized void loadLorry() {
        // worker loading lorry
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // increaces the current load of lorry
        // retruns true if the lorry is full after loading
        if (lorry.incCap()) {
            // lorry is full and it is going to the ferry
            Thread th = new Thread(lorry);
            th.start();
            thLorry.add(th);

            // new lorry is created so workers can load it
            lorry = new Lorry(lorCount, capLorry, tLorry, ferry);
            lorCount++;
        }
    }

    /**
     * sends the last lorry to ferry
     * if it is the last lorry workers might not fill the capacity of it
     * this method sends the lorry even if it is not full
     */
    public void lastLorry() {
        // sends lorry
        Thread thr = new Thread(lorry);
        thr.start();
        thLorry.add(thr);

        // joins the threads after all lorrys have finnished their work
        for (Thread th : thLorry) {
            try {
                th.join();
            } catch (InterruptedException e) {
                System.out.println("Could not join Lorry");
                e.printStackTrace();
            }
        }

        // prints the final amount of ores transported by ferry
        System.out.println("Total amount of ores is " + ferry.getSum());
    }
}
