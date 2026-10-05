import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bai2_DaLuong extends JPanel {

    private static final int BALL_COUNT = 5;
    private final int[] x = new int[BALL_COUNT];
    private final int[] y = new int[BALL_COUNT];
    private final int[] dx = new int[BALL_COUNT];
    private final int[] dy = new int[BALL_COUNT];
    private final int radius = 30;
    private JButton btnStart;

    public Bai2_DaLuong() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        btnStart = new JButton("BAT DAU CHAY (" + BALL_COUNT + " QUA BONG)");
        btnStart.addActionListener(e -> startAnimation());

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnStart);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void startAnimation() {
        btnStart.setEnabled(false);
        Random rand = new Random();

        for (int i = 0; i < BALL_COUNT; i++) {
            x[i] = rand.nextInt(350) + 20;
            y[i] = rand.nextInt(200) + 20;
            dx[i] = rand.nextInt(5) + 2;
            dy[i] = rand.nextInt(5) + 2;

            int index = i;

            new Thread(() -> runBallThread(index)).start();
        }
    }

    private void runBallThread(int index) {
        while (true) {
            x[index] += dx[index];
            y[index] += dy[index];

            // Nảy lại khi chạm 4 biên
            if (x[index] <= 0 || x[index] + radius >= getWidth()) dx[index] = -dx[index];
            if (y[index] <= 0 || y[index] + radius >= getHeight()) dy[index] = -dy[index];

            repaint();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (int i = 0; i < BALL_COUNT; i++) {
            g.fillOval(x[i], y[i], radius, radius);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bai 2: Da Luong");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new Bai2_DaLuong());
        frame.setVisible(true);
    }
}
