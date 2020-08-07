package functions.movecatalog;

import dataextraction.datacomponents.ActiveCycleTimes;
import userinterface.AppColors;
import userinterface.OSCheck;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MoveCatalog extends JFrame {

    private int APP_WIDTH = 720;
    private int APP_HEIGHT = 720;
    private ActiveCycleTimes act;
    private CatalogNumberArea catalogNumberArea;
    private CatalogDataArea catalogDataArea;
    private ArrayList<ActiveCycleTimes> moveCatalogs;

    public MoveCatalog(ActiveCycleTimes act)
    {
        this.act = act;
        initCalculations();
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setTitle("Move Catalog");
        getContentPane().setBackground(AppColors.BACKGROUND);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setResizable(false);

        initCatalogNumberArea();
        initCatalogDataArea();

        if(OSCheck.getOS().equals("WIN"))
        {
            APP_WIDTH = APP_WIDTH + 20;
            APP_HEIGHT = APP_HEIGHT + 45;
        }
        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        pack();
        repaint();
    }

    private void initCalculations()
    {
        MoveCatalogCalculator mvc = new MoveCatalogCalculator(act);
        moveCatalogs = mvc.getMoveCatalogs();
    }

    private void initCatalogNumberArea()
    {
        catalogNumberArea = new CatalogNumberArea(this);
        add(catalogNumberArea);
    }

    private void initCatalogDataArea()
    {
        catalogDataArea = new CatalogDataArea(this);
        for(int i = 0; i < 6; i++){
            catalogDataArea.add(new CatalogPanel(catalogDataArea, moveCatalogs.get(i), i, catalogNumberArea));
        }
        add(catalogDataArea);
    }
}
