public class Ferry {
    private final int capFerry;
    private int counter = 0;
    private int sum = 0;
    private boolean sleep = true;

    public Ferry(int capFerry) {
        this.capFerry = capFerry;
    }

    public synchronized void synchronize(int load) {
        sum += load;

        while (!sleep) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter++;

        if (counter == capFerry) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ferry - With " + sum);

            sleep = false;
            notifyAll();
        }

        while (sleep) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter--;

        if (counter == 0) {
            sleep = true;
            notifyAll();
        }
    }
}
