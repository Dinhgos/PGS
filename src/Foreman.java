import java.util.LinkedList;

public class Foreman {
    private LinkedList<Integer> data;
    private Worker[] workers;

    public Foreman(LinkedList<Integer> data, int numOfWorkers) {
        this.data = data;
        workers = new Worker[numOfWorkers];
    }

    public void work() {

    }

}
