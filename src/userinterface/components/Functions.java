package userinterface.components;

import userinterface.AppColors;

import javax.swing.*;
import java.awt.*;

public class Functions extends JPanel {

    /*
     * This panel holds a grid of buttons that provides functions
     * which does things to the data. Like make a chart, or show
     * frequency of things occuring etc.
     */

    private static final int PANEL_WIDTH = 460;
    private static final int PANEL_HEIGHT = 700;

    public Functions()
    {
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        initButtons();

        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        setLayout(new GridLayout(2, 1));
        setBounds(610, 10, PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(AppColors.BACKGROUND);

        repaint();
    }

    private void initButtons()
    {
        AppButton button1 = new AppButton();
        button1.setText("Button 1");
        add(button1);

        AppButton button2 = new AppButton();
        button2.setText("Button 2");
        add(button2);
    }

}
