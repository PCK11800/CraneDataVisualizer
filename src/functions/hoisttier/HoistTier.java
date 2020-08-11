package functions.hoisttier;

import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;
import userinterface.AppColors;
import userinterface.OSCheck;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HoistTier extends JFrame {

    private int APP_WIDTH = 720;
    private int APP_HEIGHT = 720;
    private ActiveCycleTimes act;
    private ArrayList<CycleTimeBlock>[] tiers;
    private TierNumberArea tierNumberArea;
    private TierDataArea tierDataArea;

    public HoistTier(ActiveCycleTimes act)
    {
        this.act = act;
        initCalculations();
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setTitle("Hoist Tier Frequency");
        getContentPane().setBackground(AppColors.BACKGROUND);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setResizable(false);

        initTierNumberArea();
        initTierDataArea();

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
        HoistTierCalculator htc = new HoistTierCalculator(act);
        tiers = htc.getTiers();
    }

    private void initTierNumberArea()
    {
        tierNumberArea = new TierNumberArea(this);
        add(tierNumberArea);
    }

    private void initTierDataArea()
    {
        tierDataArea = new TierDataArea(this);
        for(int i = 0; i < 5; i++){
            tierDataArea.add(new TierPanel(tierDataArea, tiers[i], i, tierNumberArea));
        }
        add(tierDataArea);
    }
}
