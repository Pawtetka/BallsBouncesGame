package Symbols;

import java.util.concurrent.atomic.AtomicInteger;

public class Sync {
    private AtomicInteger counter;

    public Sync(int counter){
        this.counter = new AtomicInteger(counter);
    }

    public void decrease(){
        while(true){
            if(counter.get() > 0){
                counter.decrementAndGet();
                break;
            }
        }
    }

    public void increase(){
        counter.incrementAndGet();
    }
}
