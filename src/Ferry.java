public class Ferry {
    private int id;
    private final int maxCap;
    private int curCap;
    private int counterTest = 0;

    // TODO dont know
    private final Barrier barrier = new Barrier(5);

    public Ferry(int id, int maxCap) {
        this.id = id;
        this.maxCap = maxCap;
        this.curCap = 0;
    }

    public void load(int ld) {
        this.curCap += ld;
    }

    public boolean isFull() {
        if (curCap == maxCap) {
            return true;
        }

        return false;
    }

    public void unLoad() {
        // TODO this shit
        barrier.synchronizujBezpecne();
        id++;
        curCap = 0;
    }

    // TODO fix result to not be ID
    public int getResult() {
        return id;
    }
}
