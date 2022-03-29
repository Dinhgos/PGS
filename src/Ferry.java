public class Ferry implements Runnable {
    private final int capLorry;
    private final int tLorry;
    private final int capFerry;
    private final Thread[] thLorry;

    public Ferry(int capLorry, int tLorry, int capFerry) {
        this.capLorry = capLorry;
        this.tLorry = tLorry;
        this.capFerry = capFerry;
        this.thLorry = new Thread[capFerry];
    }


    @Override
    public void run() {

    }
}
