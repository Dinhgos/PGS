/**
 * ferry holds of lorrys until the capacity is reached
 * it uses barier function to hold lorrys and prevent them from spurious wakeup
 * @author Xuan Toan Dinh
 * @version 01.05.2022
 */
public class Ferry {
    /** maximal cappacity of lorry */
    private final int capFerry;

    /** number of lorrys on ferry */
    private int counter = 0;

    /** total amount of ores transported */
    private int sum = 0;

    /** thread is spleeping -> to prevent spurious wakeup */
    private boolean sleep = true;

    /** starting time of ferry -> how long ferry has been waiting */
    private long startTime;

    /** number of ferrys departed */
    private int couFer = 0;

    /**
     * Ferry constructor
     * @param capFerry maximal cappacity of lorry
     */
    public Ferry(int capFerry) {
        this.capFerry = capFerry;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * main function of ferry
     * functions like a barrier
     * holds lorrys until the capacity is reached
     * @param load amount of ores loaded on ferry
     */
    public synchronized void synchronize(int load) {
        // increases the total amount of ores transported
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
            // time how long ferry has been wainting
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            Main.getData().writeData("Ferry;"+ couFer +";go;" + time);

            // ferry has departed
            couFer++;
            System.out.println("Ferry " + couFer + " - departed.");

            sleep = false;
            notifyAll();

            this.startTime = System.currentTimeMillis();
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

    /**
     * getter for the total amount of ores
     * @return total amount of ores
     */
    public int getSum() {
        return sum;
    }
}
