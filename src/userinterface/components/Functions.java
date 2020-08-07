package userinterface.components;

import functions.hoisttier.HoistTier;
import functions.hoisttier.HoistTierSettings;
import functions.movecatalog.MoveCatalog;
import userinterface.AppColors;
import userinterface.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class Functions extends JPanel {

    /*
     * This panel holds a grid of buttons that provides functions
     * which does things to the data. Like make a chart, or show
     * frequency of things occuring etc.
     */

    private Application app;
    private static final int PANEL_WIDTH = 550;
    private static final int PANEL_HEIGHT = 420;

    public Functions(Application app)
    {
        this.app = app;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        initButtons();

        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        setLayout(new GridLayout(2, 0));
        setBounds(720, 290, PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(AppColors.BACKGROUND);

        repaint();
    }

    private void initButtons()
    {
        initHoistTierFreqButton();
        initMoveCatalogButton();
    }

    private void initHoistTierFreqButton()
    {
        JPanel hoistTierFreqPanel = new JPanel();
        hoistTierFreqPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        AppButton hoistTierFreqButton = new AppButton();
        hoistTierFreqButton.setText("Hoist Tier Frequency");
        hoistTierFreqButton.setFontSize(25);
        hoistTierFreqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.getPartialACT() == null){
                    new HoistTier(app.getFullACT());
                }
                else{ new HoistTier(app.getPartialACT()); }
            }
        });

        AppButton hoistTierFreqSettingsButton = new AppButton();
        hoistTierFreqSettingsButton.setText("Settings");
        hoistTierFreqSettingsButton.setFontSize(25);
        hoistTierFreqSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HoistTierSettings();
            }
        });

        c.ipady = 190;
        c.ipadx = 132;
        hoistTierFreqPanel.add(hoistTierFreqButton, c);
        c.ipadx = 50;
        hoistTierFreqPanel.add(hoistTierFreqSettingsButton, c);
        add(hoistTierFreqPanel);
    }

    private void initMoveCatalogButton()
    {
        AppButton moveCatalogButton = new AppButton();
        moveCatalogButton.setText("Move Catalog");
        moveCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.getPartialACT() == null){
                    new MoveCatalog(app.getFullACT());
                }
                else{
                    new MoveCatalog(app.getPartialACT());
                }
            }
        });
        add(moveCatalogButton);
    }

}
