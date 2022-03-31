import java.util.ArrayList;

public class Transport {
    private final int capLorry;
    private final int tLorry;
    private final int capFerry;
    private Lorry lorry;
    private ArrayList<Thread> thLorry;
    private int lorCount = 1;

    public Transport(int capLorry, int tLorry, int capFerry) {
        this.capLorry = capLorry;
        this.tLorry = tLorry;
        this.capFerry = capFerry;
        this.thLorry = new ArrayList<>();
        this.lorry = new Lorry(lorCount, capLorry, tLorry);
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

    public synchronized void loadLorry() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lorry.incCap();
        if (lorry.isFull()) {
            Thread th = new Thread(lorry);
            th.start();
            thLorry.add(th);

            lorry = new Lorry(lorCount,capLorry,tLorry);
            lorCount++;
        }

    }
}
