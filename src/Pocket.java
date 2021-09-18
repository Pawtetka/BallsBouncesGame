import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.atomic.AtomicInteger;

public class Pocket {
    public static final int PSIZE = 120;
    private final int x;
    private final int y;

    private final AtomicInteger counter = new AtomicInteger(0);
    private final JTextField counterText;

    public Pocket(int x, int y, JTextField field){
        this.x = x;
        this.y = y;
        counterText = field;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void increaseCounter(){
        String text = String.valueOf(counter.incrementAndGet());
        counterText.setText(text);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.fill(new Ellipse2D.Double(x,y,PSIZE,PSIZE));
    }
}
