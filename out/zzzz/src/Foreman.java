import java.util.LinkedList;

/**
 * handles workers
 * creates workers, gets data from Data class, gives workers jobs
 * @author Xuan Toan Dinh
 * @version 05.04.2022
 */
public class Foreman {
    /** structure that holds data from input */
    private LinkedList<Integer> data;

    /** array workers */
    private final Worker[] workers;

    /** array of threads of workers */
    private final Thread[] thWorkers;

    /** counter of blocks mined */
    private int nMined = 0;

    /** time for worker to mine ore */
    private final int tWorker;

    /** Transport class tells him when is the last truck */
    private final Transport tr;

    /** Foreman constructor */
    public Foreman(int cWorker, int tWorker, Transport tr) {
        this.workers = new Worker[cWorker];
        this.thWorkers = new Thread[cWorker];
        this.tWorker = tWorker;
        this.tr = tr;
    }

    /**
     * loads data from Data class into LinkedList data
     */
    public void getBlocks() {
        data = Main.getData().loadData();
        Main.getData().writeData("Foreman;0;blocks=" + Main.getData().getBlocks() + " " + "ores=" + Main.getData().getOres());
        System.out.println("Mine has " + Main.getData().getBlocks() + " blocks and " + Main.getData().getOres() + " ores.");
    }

    /**
     * main function of foreman
     */
    public void work() {
        // creates workers and then starts them as with thread so they can work parallel
        for (int i = 0; i < thWorkers.length; i++) {
            workers[i] = new Worker(i, tWorker,this, tr);
            thWorkers[i] = new Thread(workers[i]);
            thWorkers[i].start();
        }

        // after workers are done join the threads to clean up
        for (Thread thW : thWorkers) {
            try {
                thW.join();
            } catch (InterruptedException e) {
                System.out.println("Workers could not finnish.");
            }
        }

        // after all workers have finnished tell Transport thats it is the last truck / no more workers will load
        tr.lastLorry();
    }

    /**
     * gives workers a block to mine
     * @return amount of ores in blocks or -1 if there are no more blocks to mine
     */
    public synchronized int getJob() {
        // checks if there are any mone blocks to mine
        if (nMined < data.size()) {
            int block = data.get(nMined);
            nMined++;
            return block;
        }

        return -1;
    }
}
