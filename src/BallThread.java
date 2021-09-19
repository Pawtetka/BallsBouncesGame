public class BallThread extends Thread{
    private final Ball b;
    private Thread joinedThread = null;

    public BallThread(Ball ball, int priority){
        b = ball;
        this.setPriority(priority);
    }

    public BallThread(Ball ball, int priority, Thread thread){
        this(ball, priority);
        this.joinedThread = thread;
    }

    @Override
    public void run(){
        try{
            if(joinedThread != null){
                joinedThread.join();
            }
            for(int i=1; i<10000; i++){
                b.move();
                if(b.checkHit()){
                    return;
                }
                Thread.sleep(5);
            }
        } catch(InterruptedException ignored){
        }
    }
}
