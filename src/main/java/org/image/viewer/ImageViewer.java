package org.image.viewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageViewer implements ActionListener {
    private static final String OPEN_COMMAND="Open";

    private JMenuBar menuBar;
    private JMenu menu, submenu;
    private JMenuItem menuItem;

    private JFrame frame;

    private BufferedImage image;
    private ImageCanvas imageCanvas;

    public ImageViewer () {

        menuBar = new JMenuBar();

        // create a menu
        menu = new JMenu("File");

        // create menuitems
        menuItem = new JMenuItem("Open");

        menuItem.setActionCommand(OPEN_COMMAND);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);

        frame = new JFrame();
        frame.setJMenuBar(menuBar);

        imageCanvas = new ImageCanvas(frame);
        frame.add(imageCanvas);

        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void browseFiles(){
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE FILES", "png", "jpg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File imageFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                int width = frame.getWidth();
                int height = frame.getHeight();
                image = ImageIO.read(imageFile);
                frame.remove(imageCanvas);
                imageCanvas = new ImageCanvas(image, frame);
                frame.add(imageCanvas);
                frame.pack();
                frame.setSize(width, height);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
