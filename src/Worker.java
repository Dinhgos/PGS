import java.util.Random;

public class Worker implements Runnable {
    private final int id;
    private final int tWorker;
    private final Foreman foreman;
    private final Transport tr;
    // TODO make better lorry (spread to other workers)
    private Lorry lorry;

    public Worker(int id, int tWorker, Foreman foreman, Transport tr) {
        this.id = id;
        this.tWorker = tWorker;
        this.foreman = foreman;
        this.tr = tr;
    }

    @Override
    public void run() {
        int job;
        int jobCount = 0;

        Random rand = new Random();
        int maxR = tWorker + 1;
        int wSpeed;

        while ((job = foreman.getJob(id)) != -1) {
            System.out.println("Worker " + id + " - Got a job.");

            for (int i = 0; i < job; i++) {
                wSpeed = rand.nextInt(maxR);
                try {
                    Thread.sleep(wSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // TODO loading lorry
            jobCount++;

            for (int i = 0; i < job; i++) {
                tr.loadLorry();
            }

            foreman.reportResult(job, id);
            System.out.println("Worker " + id + " - Mined " + job + " ores.");
        }

        System.out.println("Worker " + id + " - Done mining " + jobCount + " blocks.");
    }

    public int getWorkerID() {
        return id;
    }
}
