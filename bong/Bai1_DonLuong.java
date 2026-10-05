import javax.swing.*;
import java.awt.*;

public class Bai1_DonLuong extends JPanel implements Runnable {
    private int x = 50, y = 50;
    private int dx = 4, dy = 4;
    private final int radius = 30;
    private JButton btnStart;

    public Bai1_DonLuong() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        btnStart = new JButton("BAT DAU CHAY");
        btnStart.addActionListener(e -> {
            btnStart.setEnabled(false);
            new Thread(this).start();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnStart);
        add(btnPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, radius, radius);
    }

    @Override
    public void run() {
        while (true) {
            x += dx;
            y += dy;

            if (x <= 0 || x + radius >= getWidth()) dx = -dx;
            if (y <= 0 || y + radius >= getHeight()) dy = -dy;

            repaint();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bai 1: Don Luong");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new Bai1_DonLuong());
        frame.setVisible(true);
    }
}
