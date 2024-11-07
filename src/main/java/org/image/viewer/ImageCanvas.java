package org.image.viewer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCanvas extends Canvas {
    private int zoom = 0;
    private int dragX = 0;
    private int dragY = 0;

    private BufferedImage image;

    public ImageCanvas() {
    }

    @Override
    public Dimension getPreferredSize() {
        return image == null ? new Dimension(200, 200) : new Dimension(image.getWidth(), image.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (image != null) {
            int xScale = getWidth() + zoom;
            int yScale = getHeight() + zoom;

            Image scaledImage = image.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT);

            int x = (getWidth() - xScale) / 2;
            int y = (getHeight() - yScale) / 2;
            g.drawImage(scaledImage, x + dragX, y + dragY, this);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        zoom = 0;
        repaint();
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getDragX() {
        return dragX;
    }

    public void setDragX(int dragX) {
        this.dragX = dragX;
    }

    public int getDragY() {
        return dragY;
    }

    public void setDragY(int dragY) {
        this.dragY = dragY;
    }
}
