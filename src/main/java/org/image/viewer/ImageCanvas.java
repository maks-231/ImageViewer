package org.image.viewer;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class ImageCanvas extends Canvas {
  Object parent;

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  private BufferedImage image;

  public ImageCanvas(BufferedImage img, Object parent) {
    this.image = img;
    this.parent = parent;
  }

  public ImageCanvas(Object parent) {
    this.parent = parent;
  }

  @Override
  public Dimension getPreferredSize() {
    return image == null ? new Dimension(200, 200) : new Dimension(image.getWidth(), image.getHeight());
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    int width =((JFrame) parent).getWidth();
    int height = ((JFrame) parent).getHeight();

    if (image != null) {
      int x = (getWidth() - image.getWidth()) / 2;
      int y = (getHeight() - image.getHeight()) / 2;
      g.drawImage(image, x, y, this);
    }
  }
}
