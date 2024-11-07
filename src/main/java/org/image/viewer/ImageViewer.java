package org.image.viewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ImageViewer implements ActionListener, MouseWheelListener, MouseMotionListener {
    private static final String OPEN_COMMAND = "Open";

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JFrame frame;
    private ImageCanvas imageCanvas;

    private int dragX = 0;
    private int dragY = 0;

    public ImageViewer() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItem = new JMenuItem("Open");

        menuItem.setActionCommand(OPEN_COMMAND);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);

        frame = new JFrame();
        frame.setJMenuBar(menuBar);

        imageCanvas = new ImageCanvas();
        frame.add(imageCanvas).addMouseMotionListener(this);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseWheelListener(this);
        frame.show();
        frame.setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        new ImageViewer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (OPEN_COMMAND.equals(command)) {
            browseFiles();
        }
    }

    private void browseFiles() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE FILES", "png", "jpg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File imageFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                imageCanvas.setImage(ImageIO.read(imageFile));
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int zoom = imageCanvas.getZoom();
        if (e.getWheelRotation() < 0) {
            imageCanvas.setZoom(zoom + 10);
        } else {
            imageCanvas.setZoom(zoom - 10);
        }
        imageCanvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Math.abs(dragX - e.getX()) > 5 || Math.abs(dragY - e.getY()) > 5) {
            if (e.getX() > dragX) {
                imageCanvas.setDragX(imageCanvas.getDragX() + Math.abs(dragX - e.getX()));
            } else {
                imageCanvas.setDragX(imageCanvas.getDragX() - Math.abs(dragX - e.getX()));
            }
            if (e.getY() > dragY) {
                imageCanvas.setDragY(imageCanvas.getDragY() + Math.abs(dragY - e.getY()));
            } else {
                imageCanvas.setDragY(imageCanvas.getDragY() - Math.abs(dragY - e.getY()));
            }

            dragX = e.getX();
            dragY = e.getY();
            imageCanvas.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        dragX = e.getX();
        dragY = e.getY();
    }
}
