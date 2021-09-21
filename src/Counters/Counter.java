package Counters;

public class Counter implements ICounter{
    private int value;

    public void increment(){
        ++value;
    }

    public void decrement(){
        --value;
    }

    public int getResult(){
        return value;
    }
}
