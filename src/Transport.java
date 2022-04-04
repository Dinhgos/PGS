import java.util.ArrayList;

public class Transport {
    private final int capLorry;
    private final int tLorry;
    private final int capFerry;
    private Lorry lorry;
    private final Ferry ferry;
    private ArrayList<Thread> thLorry;
    private int lorCount = 0;
    private int ferId = 0;
    // TODO test variavble
    private int counterTest = 0;
    private boolean lastLorry = false;

    public Transport(int capLorry, int tLorry, int capFerry) {
        this.capLorry = capLorry;
        this.tLorry = tLorry;
        this.capFerry = capFerry;
        this.thLorry = new ArrayList<>();
        this.ferry = new Ferry(ferId, capFerry);
        this.lorry = new Lorry(lorCount, capLorry, tLorry, ferry);
    }

    public void work() {
        System.out.println("Depo - Creating lorry.");
//        for (int i = 0; i < capFerry; i++) {
//            Lorry lorry = new Lorry(i, capLorry, tLorry);
//            thLorry[i] = new Thread(lorry);
//            // TODO start lorry
//            System.out.println("Lorry " + lorry.getId() + " - started.");
//        }

//        System.out.println("Depo - Waiting for lorry to finnish.");
//        for (Thread thL : thLorry) {
//            try {
//                thL.join();
//            } catch (InterruptedException e) {                System.out.println("Lorry could not finnish.");
//            }
//        }

        System.out.println("Depo - Done working.");
    }

    public synchronized void loadLorry(int id) {
        System.out.println("Worker " + id + " - loading ore.");

        //TODO 1 sec loading from worker
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        lorry.incCap();
        if (lorry.isFull()) {
            Thread th = new Thread(lorry);

            // TODO join later
            th.start();
            System.out.println("Transport isFull started lorry.");
            thLorry.add(th);

            lorry = new Lorry(lorCount, capLorry, tLorry, ferry);
            lorCount++;
        }
    }

    public void lastLorry() {
        if (lastLorry) {
            Thread th = new Thread(lorry);
            // TODO join later
            th.start();
            System.out.println("Transport lastLorry started lorry.");
            thLorry.add(th);
        }
    }

    public Lorry getLorry() {
        return lorry;
    }

    public Ferry getFerry() {
        return ferry;
    }

    public int getCounterTest() {
        return counterTest;
    }

    public void setLastLorry(boolean lastLorry) {
        this.lastLorry = lastLorry;
    }
}
