package functions.movecatalog;

import userinterface.AppColors;

import javax.swing.*;
import java.awt.*;

public class CatalogDataArea extends JPanel {

    private JFrame moveCatalog;

    private int PANEL_WIDTH = 360;
    private int PANEL_HEIGHT = 720;

    public CatalogDataArea(JFrame moveCatalog)
    {
        this.moveCatalog = moveCatalog;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setVisible(true);
        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        setBackground(AppColors.BACKGROUND);
        setLayout(new GridLayout(6, 1));
    }
}
