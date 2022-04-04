import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Foreman {
    private final LinkedList<Integer> data;
    private final Worker[] workers;
    private final Thread[] thWorkers;
    private int nMined = 0;
    private static int ores = 0;
    private final int tWorker;
    private final Transport tr;

    public Foreman(int cWorker, int tWorker, Transport tr) {
        this.data = new LinkedList<>();
        this.workers = new Worker[cWorker];
        this.thWorkers = new Thread[cWorker];
        this.tWorker = tWorker;
        this.tr = tr;
    }

    public void getBlocks(String filePath) {
        System.out.println("Foreman - Loading data.");
        int blocks = 0;
        int ores = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] sources = line.split(" ");
                blocks += sources.length;
                for (String source : sources) {
                    data.add(source.length());
                    ores += source.length();
                }
            }
        } catch (IOException e) {
            System.out.println("Foreman - Could not read from file.");
            System.exit(1);
        }

        System.out.println("Foreman - Done loading data.");
        System.out.println("Foreman - Mine has " + blocks + " blocks and " + ores + " ores.");
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

        System.out.println("Foreman - Printing result.");
        // TODO foreman cant print result
        //printResult();
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
        // TODO last worker should tell last load
        //tr.setLastLorry(true);
        return -1;
    }

    // TODO someone else report result to foreman
    public synchronized void reportResult(int results, int workerId) {
        System.out.println("Worker " + workerId + " - Reporting result.");
        ores += results;
    }

    // TODO print the right result
    private void printResult() {
        System.out.println("Total amount of ores - " + tr.getFerry().getResult());
        System.out.println("Total amount of ores - " + tr.getCounterTest());
    }
}
