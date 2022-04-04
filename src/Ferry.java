public class Ferry {
    private final int capFerry;
    private int counter = 0;
    private double sum = 0;
    private boolean sleep = true;

    public Ferry(int capFerry) {
        this.capFerry = capFerry;
    }

    public synchronized void synchronize(int load) {
        sum += load;

        while (sleep == false) {
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

            sum = 0;
            sleep = false;

            notifyAll();
        }

        while (sleep == true) {
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
