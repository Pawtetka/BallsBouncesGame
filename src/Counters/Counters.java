package Counters;

public class Counters {
    public static void main(String[] args){
        ICounter counter = new Counter();
        testCounter(counter);

        ICounter counter2 = new SynchronizedMethodCounter();
        testCounter(counter2);

        ICounter counter3 = new SynchronizedBlockCounter();
        testCounter(counter3);

        ICounter counter4 = new BlockObjectCounter();
        testCounter(counter4);
    }

    private static void testCounter(ICounter counter){
        IncrementThread thread1 = new IncrementThread(counter);
        DecrementThread thread2 = new DecrementThread(counter);
        thread1.start();
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(counter.getResult());
    }
}
