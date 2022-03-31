import java.util.Random;

public class Lorry implements Runnable {
    private final int id;
    private final int capLorry;
    private final int tLorry;
    private int curCap = 0;
    // TODO make ferry in all lorry
    private Ferry ferry = new Ferry(0,123,123);

    public Lorry(int id, int capLorry, int tLorry) {
        this.id = id;
        this.capLorry = capLorry;
        this.tLorry = tLorry;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int spLorry = rand.nextInt(tLorry);

        try {
            Thread.sleep(spLorry);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ferry.load(curCap);
        if (ferry.isFull()) {

        }

    }

    // TODO maybe sync
    public boolean isFull() {
        if (curCap == capLorry) {
            return false;
        }

        return true;
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
