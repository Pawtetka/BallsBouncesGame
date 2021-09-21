package Counters;

public class IncrementThread extends Thread{
    private final ICounter counter;

    public IncrementThread(ICounter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100000; i++){
            counter.increment();
        }
    }
}
