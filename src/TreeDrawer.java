import java.awt.*;

/**
 * Created by simon_clark on 10/7/16.
 */
public class TreeDrawer {


    public void drawTree(int x, int y, int d, int width, int height, double angle, Graphics2D g2) {
        if (d == 0) return;

        int x2 = x + (int) (height * Math.cos(angle));
        int y2 = y + (int) (height * Math.sin(angle));

        g2.setStroke(new BasicStroke(width));
        g2.drawLine(x, y, x2, y2);

        drawTree(x2, y2, d - 1, width * 5 / 6, height * 6 / 7, angle + Math.PI/4, g2);
        drawTree(x2, y2, d - 1, width * 3 / 4, height * 7 / 8, angle - Math.PI / 8, g2);

    }

}
