import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
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
        JButton buttonExperiment = new JButton("Priority experiment");
        JButton buttonJoin = new JButton("Test join");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> createBall(randomColor(), Thread.NORM_PRIORITY, true));
        buttonExperiment.addActionListener(e -> doPriorityExperiment());
        buttonJoin.addActionListener(e -> doJoinExperiment());
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonJoin);
        buttonPanel.add(buttonStop);

        return buttonPanel;
    }

    private JPanel createLabelsPanel(){
        JPanel labelsPanel = new JPanel();
        labelsPanel.setBackground(Color.lightGray);
        createPocketsTextFields(labelsPanel);

        return labelsPanel;
    }

    private void createBall(Color color, int priority, boolean inRandomPosition){
        Ball b;
        if(inRandomPosition){
            b = new Ball(canvas, color);
        }else{
            b = new Ball(canvas, color, canvas.getHeight()/2);
        }
        canvas.addBall(b);

        createThreadForBall(b, priority);
    }

    private void createThreadForBall(Ball b, int priority){
        BallThread thread = new BallThread(b, priority);
        thread.start();
        System.out.println("Thread name = " +
                thread.getName());
    }

    private void doPriorityExperiment(){
        for(int i = 0; i < 2000; i++){
            createBall(Color.BLUE, Thread.MIN_PRIORITY, false);
        }
        createBall(Color.RED, Thread.MAX_PRIORITY, false);
    }

    private void doJoinExperiment(){
        Ball b1 = new Ball(canvas, Color.white);
        Ball b2 = new Ball(canvas, Color.RED);
        Ball b3 = new Ball(canvas, Color.BLUE);

        canvas.addBall(b1);
        canvas.addBall(b2);
        canvas.addBall(b3);

        BallThread thread1 = new BallThread(b1, Thread.NORM_PRIORITY);
        BallThread thread2 = new BallThread(b2, Thread.NORM_PRIORITY, thread1);
        BallThread thread3 = new BallThread(b3, Thread.NORM_PRIORITY, thread2);

        thread3.start();
        thread2.start();
        thread1.start();
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

    private Color randomColor(){
        int r = new Random().nextInt(255);
        int g = new Random().nextInt(255);
        int b = new Random().nextInt(255);

        return new Color(r, g, b);
    }
}
