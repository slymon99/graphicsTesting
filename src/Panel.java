import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private TreeDrawer td;

    public static void main(String[] args) {
        JFrame window = new JFrame("Fractals");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 1440, 900); //(x, y, w, h)

        Panel panel = new Panel();
        window.add(panel);

        window.setVisible(true);
    }

    public Panel() {
         td = new TreeDrawer();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(153,76,0));
        td.drawTree(getWidth()/2, getHeight(), 8, 20, 100, 3*Math.PI/2, g2);

    }

    public void drawTree(int x, int y, int width, int height, double angle, int d, Graphics2D g2) {

        if (d == 0) return;

        int x2 = x + (int) (height * Math.cos(angle));
        int y2 = y + (int) (height * Math.sin(angle));

        g2.setStroke(new BasicStroke(width));
        g2.drawLine(x, y, x2, y2);

        drawTree(x2, y2, width * 5 / 6, height * 6 / 7, angle, d - 1, g2);
        drawTree(x2, y2, width * 3 / 4, height * 7 / 8, angle - (Math.PI / 8), d - 1, g2);
    }

    public void complexTree(int x, int y, int width, int height, double angle, int d, Graphics2D g2, double[][] data) {

        if (d == 0) return;

        int x2 = x + (int) (height * Math.cos(angle));
        int y2 = y + (int) (height * Math.sin(angle));

        g2.setStroke(new BasicStroke(width));
        g2.drawLine(x, y, x2, y2);


        for (int i = 0; i < data[0].length; i++) {
            complexTree(x2, y2, (int) (width * data[0][i]), (int) (height * data[1][i]), angle + data[2][i], d - 1, g2, data);
        }
    }

    public void colorTree(int x, int y, int width, int height, double angle, int d, Graphics2D g2, double[][] data, float color) {

        if (d == 0) return;

        if (color == 1) {
            color = 0;
        } else {
            color += 0.05f;
        }

        g2.setColor(Color.getHSBColor(color, 1f, 0.5f));

        int x2 = x + (int) (height * Math.cos(angle));
        int y2 = y + (int) (height * Math.sin(angle));

        g2.setStroke(new BasicStroke(width));
        g2.drawLine(x, y, x2, y2);


        for (int i = 0; i < data[0].length; i++) {
            colorTree(x2, y2, (int) (width * data[0][i]), (int) (height * data[1][i]), angle + data[2][i], d - 1, g2, data, color);
        }
    }

    public void circles1(int x, int y, int rad, int width, int d, Graphics2D g2) {
        if (d == 0) return;
        g2.setStroke(new BasicStroke(width));

        int xC = x - rad;
        int yC = y - rad;

        g2.drawOval(xC, yC, rad * 2, rad * 2);

        circles1(x + rad, y, rad * 1 / 2, width * 2 / 3, d - 1, g2);
        circles1(x - rad, y, rad * 1 / 2, width * 2 / 3, d - 1, g2);
    }

    public void circles2(int x, int y, int rad, int width, int d, Graphics2D g2, float color, float cMod) {
        if (d == 0) return;
        g2.setStroke(new BasicStroke(width));

        if (color + cMod >= 1) {
            color = 0;
        } else {
            color += cMod;
        }

        g2.setColor(Color.getHSBColor(color, 1f, 0.5f));

        int xC = x - rad;
        int yC = y - rad;

        g2.drawOval(xC, yC, rad * 2, rad * 2);

        circles2(x + rad, y, rad * 1 / 2, width * 2 / 3, d - 1, g2, color, cMod);
        circles2(x - rad, y, rad * 1 / 2, width * 2 / 3, d - 1, g2, color, cMod);
    }

    public void circles3(int x, int y, int rad, int width, double angle, int d, Graphics2D g2) {
        if (d == 0) return;
        g2.setStroke(new BasicStroke(width));

        int xC = x - rad;
        int yC = y - rad;

        g2.drawOval(xC, yC, rad * 2, rad * 2);

        circles3(x + (int) (rad * Math.cos(angle)), y + (int) (rad * Math.sin(angle)), rad * 1 / 2, width * 2 / 3, angle + (Math.PI / 4), d - 1, g2);
        circles3(x - (int) (rad * Math.cos(angle)), y - (int) (rad * Math.cos(angle)), rad * 1 / 2, width * 2 / 3, angle + (Math.PI / 4), d - 1, g2);
    }

}