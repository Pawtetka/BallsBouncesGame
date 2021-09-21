package Counters;

public class SynchronizedBlockCounter implements ICounter{
    private int value;

    public void increment() {
        synchronized (this){
            ++value;
        }
    }

    public void decrement() {
        synchronized (this){
            --value;
        }
    }

    public int getResult() {
        return value;
    }
}
