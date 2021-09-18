import javax.swing.*;

public class Bounce {
    public static void main(String[] args) {
        BounceFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.createPockets();

        System.out.println("Thread name = " +
                Thread.currentThread().getName());
    }
}
