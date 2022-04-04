import java.util.LinkedList;

public class Foreman {
    private LinkedList<Integer> data;
    private final Worker[] workers;
    private final Thread[] thWorkers;
    private int nMined = 0;
    private final int tWorker;
    private final Transport tr;

    public Foreman(int cWorker, int tWorker, Transport tr) {
        this.workers = new Worker[cWorker];
        this.thWorkers = new Thread[cWorker];
        this.tWorker = tWorker;
        this.tr = tr;
    }

    public void getBlocks() {
        System.out.println("Foreman - Loading data.");

        data = Main.getData().loadData();
        Main.getData().writeData("Foreman;0;blocks=" + Main.getData().getBlocks() + " " + "ores=" + Main.getData().getOres());

        System.out.println("Foreman - Done loading data.");
        System.out.println("Foreman - Mine has " + Main.getData().getBlocks() + " blocks and " + Main.getData().getOres() + " ores.");
    }

    public void work() {
        System.out.println("Foreman - Creating workers");

        for (int i = 0; i < thWorkers.length; i++) {
            workers[i] = new Worker(i, tWorker,this, tr);
            thWorkers[i] = new Thread(workers[i]);
            thWorkers[i].start();
            System.out.println("Worker " + workers[i].getWorkerID() + " - started.");
        }

        System.out.println("Foreman - Waiting for workers to finnish.");
        for (Thread thW : thWorkers) {
            try {
                thW.join();
            } catch (InterruptedException e) {
                System.out.println("Workers could not finnish.");
            }
        }

        tr.setLastLorry(true);
        tr.lastLorry();

        System.out.println("Foreman - Done working.");
    }

    public synchronized int getJob(int workerId) {
        System.out.println("Worker " + workerId + " - Requesting a job.");

        if (nMined < data.size()) {
            int block = data.get(nMined);
            nMined++;
            return block;
        }

        System.out.println("Worker " + workerId + " - No more jobs available.");
        return -1;
    }
}
