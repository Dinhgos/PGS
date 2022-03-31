public class Ferry {
    private final int id;
    private final int tLorry;
    private final int maxCap;
    private int curCap;

    public Ferry(int id, int tLorry, int maxCap) {
        this.id = id;
        this.tLorry = tLorry;
        this.maxCap = maxCap;
        this.curCap = 0;
    }

    public void load(int curCap) {

    }

    public boolean isFull() {
        return true;
    }
}
