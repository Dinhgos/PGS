import java.util.Random;

/**
 * mines ores and loads lorry
 * @author Xuan Toan Dinh
 * @version 01.05.2022
 */
public class Worker implements Runnable {
    /** ID of the worker */
    private final int id;

    /** time needed to mine an ore */
    private final int tWorker;

    /** Foreman to ask for a job */
    private final Foreman foreman;

    /** Transport to get lorry to load on */
    private final Transport tr;

    /** Worker constructor */
    public Worker(int id, int tWorker, Foreman foreman, Transport tr) {
        this.id = id;
        this.tWorker = tWorker;
        this.foreman = foreman;
        this.tr = tr;
    }

    /**
     * main function of worker
     * asks forman for a block to mine
     * loads a lorry after mining a block
     */
    @Override
    public void run() {
        // block size
        int job;

        // number of ores mined
        int jobCount = 0;

        Random rand = new Random();
        // (0, tWorker>
        int maxR = tWorker + 1;
        int wSpeed;

        // asks forman for a job until there is no more jobs
        while ((job = foreman.getJob()) != -1) {
            long startTime = System.currentTimeMillis();
            long endTime;
            long time;

            // mines ores
            for (int i = 0; i < job; i++) {
                long startTime2 = System.currentTimeMillis();

                wSpeed = rand.nextInt(maxR);
                try {
                    Thread.sleep(wSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jobCount++;

                // writes into output
                endTime = System.currentTimeMillis();
                time = endTime - startTime2;
                Main.getData().writeData("Worker;" + id + ";ore;" + time);
            }

            // writes into output
            endTime = System.currentTimeMillis();
            time = endTime - startTime;
            Main.getData().writeData("Worker;" + id + ";block;" + time);

            // loads lorry
            for (int i = 0; i < job; i++) {
                tr.loadLorry();
            }
        }

        System.out.println("Worker " + id + " - Mined " + jobCount + " ores.");
    }
}
