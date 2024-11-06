package org.image.viewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageViewer extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menu, submenu;
    private JMenuItem menuItem;

    private JFrame frame;

    public ImageViewer () {

        menuBar = new JMenuBar();

        // create a menu
        menu = new JMenu("File");

        // create menuitems
        menuItem = new JMenuItem("Open");

        menu.add(menuItem);
        menuBar.add(menu);

        frame = new JFrame();
        frame.setJMenuBar(menuBar);

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new ImageViewer().show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
