package userinterface;

import dataextraction.datacomponents.ActiveCycleTimes;
import userinterface.components.Functions;
import userinterface.components.Screenview;
import userinterface.components.Settings;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    static final String APP_VERSION = "0.0.1";
    static int APP_WIDTH = 1280;
    static int APP_HEIGHT = 720;
    static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public Screenview screenview = new Screenview(this);
    public Functions functions = new Functions(this);
    public Settings settings = new Settings(this);

    private ActiveCycleTimes fullACT = null;
    private ActiveCycleTimes partialACT = null;

    public Application()
    {
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        System.out.println(OSCheck.getOS());
        if(OSCheck.getOS().equals("WIN"))
        {
            APP_WIDTH = APP_WIDTH + 20;
            APP_HEIGHT = APP_HEIGHT + 45;
        }

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
        add(screenview);
        add(functions);
        add(settings);
    }

    public ActiveCycleTimes getFullACT()
    {
        return fullACT;
    }

    public void setFullACT(ActiveCycleTimes act)
    {
        this.fullACT = act;
    }

    public ActiveCycleTimes getPartialACT()
    {
        return partialACT;
    }

    public void setPartialACT(ActiveCycleTimes act)
    {
        this.partialACT = act;
    }
}
