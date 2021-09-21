package Counters;

public class SynchronizedMethodCounter implements ICounter{
    private int value;

    public synchronized void increment() {
        ++value;
    }

    public synchronized void decrement() {
        --value;
    }

    public int getResult() {
        return value;
    }
}
