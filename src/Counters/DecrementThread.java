package Counters;

public class DecrementThread extends Thread{
    private final ICounter counter;

    public DecrementThread(ICounter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100000; i++){
            counter.decrement();
        }
    }
}
