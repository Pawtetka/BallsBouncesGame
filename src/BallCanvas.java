import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Pocket> pockets = new ArrayList<>();

    public ArrayList<Pocket> getPockets(){
        return pockets;
    }

    public void addBall(Ball b){
        this.balls.add(b);
    }

    public void removeBall(Ball b){
        this.balls.remove(b);
        repaint();
    }

    public void addPocket(Pocket p){
        this.pockets.add(p);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for (Ball b : balls) {
            b.draw(g2);
        }

        for (Pocket p : pockets){
            p.draw(g2);
        }
    }
}
