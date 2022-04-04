import java.util.ArrayList;

public class Transport {
    private final int capLorry;
    private final int tLorry;
    private Lorry lorry;
    private final Ferry ferry;
    private final ArrayList<Thread> thLorry;
    private int lorCount = 0;
    private boolean lastLorry = false;

    public Transport(int capLorry, int tLorry, int capFerry) {
        this.capLorry = capLorry;
        this.tLorry = tLorry;
        this.thLorry = new ArrayList<>();
        this.ferry = new Ferry(capFerry);
        this.lorry = new Lorry(lorCount, capLorry, tLorry, ferry);
        lorCount++;
    }

    public synchronized void loadLorry(int id) {
        System.out.println("Worker " + id + " - loading ore.");

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (lorry.incCap()) {
            Thread th = new Thread(lorry);
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
            th.start();
            System.out.println("Transport lastLorry started lorry.");
            thLorry.add(th);
        }

        for (Thread th : thLorry) {
            try {
                th.join();
            } catch (InterruptedException e) {
                System.out.println("Could not join Lorry");
                e.printStackTrace();
            }
        }
    }

    public void setLastLorry(boolean lastLorry) {
        this.lastLorry = lastLorry;
    }
}
