package userinterface;

import userinterface.components.Functions;
import userinterface.components.Screenview;
import userinterface.components.Settings;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    /*
     * CHANGELOG
     * v0.0.1
     *  - Initial Commit
     *  - Creation of Window Frame
     */

    static final String APP_VERSION = "0.0.1";
    static final int APP_WIDTH = 1080;
    static final int APP_HEIGHT = 720;
    static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public Application()
    {
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setTitle("Crane Data Visualizer");
        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(
                (int) (SCREEN_WIDTH / 2) - (APP_WIDTH / 2),
                (int) (SCREEN_HEIGHT / 2) - (APP_HEIGHT / 2)
        );
        setResizable(false);
        getContentPane().setBackground(AppColors.BACKGROUND);
        getContentPane().setLayout(null);

        initComponents();

        pack();
        repaint();
    }

    private void initComponents()
    {
        add(new Screenview());
        add(new Functions());
        add(new Settings());
    }
}
