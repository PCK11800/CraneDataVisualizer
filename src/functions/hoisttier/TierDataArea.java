package functions.hoisttier;

import userinterface.AppColors;

import javax.swing.*;
import java.awt.*;

public class TierDataArea extends JPanel {

    private JFrame hoistTier;

    private int PANEL_WIDTH = 360;
    private int PANEL_HEIGHT = 720;

    public TierDataArea(JFrame hoistTier)
    {
        this.hoistTier = hoistTier;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setVisible(true);
        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        setBackground(AppColors.BACKGROUND);
        setLayout(new GridLayout(5, 1));
    }
}
