package functions.hoisttier;

import userinterface.AppColors;
import userinterface.OSCheck;

import javax.swing.*;
import java.awt.*;

public class HoistTierSettings extends JFrame {

    private int APP_WIDTH = 720;
    private int APP_HEIGHT = 720;

    public HoistTierSettings()
    {
        initUI();
        setVisible(true);
        HoistTierCalculator.bound1 = 0;
        HoistTierCalculator.bound2 = 10;
        HoistTierCalculator.bound3 = 20;
    }

    private void initUI()
    {
        setTitle("Hoist Tier Settings");
        getContentPane().setBackground(AppColors.BACKGROUND);
        getContentPane().setLayout(null);
        setResizable(false);

        if(OSCheck.getOS().equals("WIN"))
        {
            APP_WIDTH = APP_WIDTH + 20;
            APP_HEIGHT = APP_HEIGHT + 45;
        }
        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        pack();
        repaint();
    }

    private void initSettingsArea()
    {

    }
}
