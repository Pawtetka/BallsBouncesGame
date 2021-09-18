import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 750;

    private final ArrayList<JTextField> textFields = new ArrayList<>();

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle("Balls and Bounces game");
        this.canvas = new BallCanvas();
        canvas.setBackground(new Color(107,142,35));
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonsPanel();
        JPanel labelsPanel = createLabelsPanel();

        content.add(buttonPanel, BorderLayout.SOUTH);
        content.add(labelsPanel, BorderLayout.NORTH);
    }

    private JPanel createButtonsPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");
        buttonStart.addActionListener(e -> createBall());
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        return buttonPanel;
    }

    private JPanel createLabelsPanel(){
        JPanel labelsPanel = new JPanel();
        labelsPanel.setBackground(Color.lightGray);
        createPocketsTextFields(labelsPanel);

        return labelsPanel;
    }

    private void createBall(){
        Ball b = new Ball(canvas);
        canvas.addBall(b);

        BallThread thread = new BallThread(b);
        thread.start();
        System.out.println("Thread name = " +
                thread.getName());
    }

    private void createPocketsTextFields(JPanel panel){
        for (int i = 0; i < 6; i++){
            JTextField field = new JTextField(3);
            textFields.add(field);
            JLabel label = new JLabel("Pocket " + (i+1));
            panel.add(label);
            panel.add(field);
        }
    }

    public void createPockets(){
        createPocket(-Pocket.PSIZE/2, -Pocket.PSIZE/2, textFields.get(0));
        createPocket(-Pocket.PSIZE/2, canvas.getHeight() - Pocket.PSIZE/2, textFields.get(1));
        createPocket(canvas.getWidth()/2 - Pocket.PSIZE/2, -Pocket.PSIZE/2 - 15, textFields.get(4));
        createPocket(canvas.getWidth()/2 - Pocket.PSIZE/2, canvas.getHeight() - Pocket.PSIZE/2 + 15, textFields.get(5));
        createPocket(canvas.getWidth() - Pocket.PSIZE/2, -Pocket.PSIZE/2, textFields.get(2));
        createPocket(canvas.getWidth() - Pocket.PSIZE/2, canvas.getHeight() - Pocket.PSIZE/2, textFields.get(3));
    }

    private void createPocket(int x, int y, JTextField field){
        Pocket pocket = new Pocket(x, y, field);
        this.canvas.addPocket(pocket);
    }
}
