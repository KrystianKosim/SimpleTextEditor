package Zajecia11;

import javax.swing.*;
import java.awt.*;

public class OvalIcon implements Icon {
    private final int width;
    private final int heigh;
    private Color color;

    public OvalIcon(int width, int heigh, Color color) {
        this.width = width;
        this.heigh = heigh;
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Color temp = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, getIconWidth(), getIconHeight());
        g.setColor(temp);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return heigh;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
