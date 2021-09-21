package Counters;

public class BlockObjectCounter implements ICounter{
    private final Object object = new Object();
    private int value;

    public void increment() {
        synchronized (object){
            ++value;
        }
    }

    public void decrement() {
        synchronized (object){
            --value;
        }
    }

    public int getResult() {
        return value;
    }
}
