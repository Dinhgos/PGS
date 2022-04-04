import java.util.Random;

public class Lorry implements Runnable {
    private final int id;
    private final int capLorry;
    private final int tLorry;
    private final Ferry ferry;
    private int curCap = 0;

    public Lorry(int id, int capLorry, int tLorry, Ferry ferry) {
        this.id = id;
        this.capLorry = capLorry;
        this.tLorry = tLorry;
        this.ferry = ferry;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int spLorry = rand.nextInt(tLorry);

        System.out.println("Lorry " + id + " arriving at ferry in " + spLorry + " ms.");
        System.out.println("Lorry " + id + " current capacity " + curCap);

        try {
            Thread.sleep(spLorry);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ferry.synchronize(curCap);
    }

    public synchronized boolean incCap() {
        this.curCap++;

        if (curCap == capLorry) {
            return true;
        }

        return false;
    }
}
