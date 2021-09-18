import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {
    private final BallCanvas canvas;
    private static final int XSIZE = 40;
    private static final int YSIZE = 40;
    private int x;
    private int y;
    private int dx = 3;
    private int dy = 3;


    public Ball(BallCanvas c){
        this.canvas = c;

        x = new Random().nextInt(this.canvas.getWidth());
        y = new Random().nextInt(this.canvas.getHeight());
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.white);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }

    public void move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    public boolean checkHit(){
        int pocketSize = Pocket.PSIZE;
        for(Pocket p : canvas.getPockets()){
            if(x >= p.getX()
                    && x + XSIZE <= p.getX() + pocketSize
                    && y >= p.getY()
                    && y + YSIZE <= p.getY() + pocketSize){
                p.increaseCounter();
                synchronized (canvas){
                    canvas.removeBall(this);
                }
                return true;
            }
        }
        return false;
    }
}
