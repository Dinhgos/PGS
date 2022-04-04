import java.util.Random;
import java.util.Spliterators;

public class Lorry implements Runnable {
    private final int id;
    private final int capLorry;
    private final int tLorry;
    private Ferry ferry;
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

        // TODO load what ?
        // TODO do it better
        ferry.load(curCap);
        if (ferry.isFull()) {
            ferry.unLoad();
        }
    }

    // TODO maybe sync
    public boolean isFull() {
        if (curCap == capLorry) {
            return true;
        }

        return false;
    }

    public void incCap() {
        this.curCap++;
    }

    public int getId() {
        return id;
    }

    public int getCurCap() {
        return curCap;
    }

}
