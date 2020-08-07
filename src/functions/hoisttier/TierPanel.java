package functions.hoisttier;

import font.Inconsolata;
import userinterface.AppColors;
import userinterface.components.AppButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TierPanel extends JPanel {

    private JPanel tierDataPanel;
    private ArrayList<String> tier;
    private int rank;
    private TierNumberArea tierNumberArea;

    public TierPanel(JPanel tierDataPanel, ArrayList<String> tier, int rank, TierNumberArea tierNumberArea)
    {
        this.tierDataPanel = tierDataPanel;
        this.tier = tier;
        this.rank = rank;
        this.tierNumberArea = tierNumberArea;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setPreferredSize(new Dimension(tierDataPanel.getWidth(), tierDataPanel.getHeight() / 5));
        setLayout(null);
        setBackground(AppColors.BACKGROUND);
        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));

        initTierLabel();
        initShowButton();
    }

    private void initTierLabel()
    {
        JLabel tierLabel = new JLabel();
        tierLabel.setText("Tier " + (rank + 3) + ": " + tier.size());
        if((rank + 3) >= 7){
            tierLabel.setText("   N/A: " + tier.size());
        }
        tierLabel.setFont(new Inconsolata().getFont(30));
        tierLabel.setBackground(AppColors.BACKGROUND);
        tierLabel.setForeground(AppColors.BORDER);
        tierLabel.setBounds(10, 50, 200, 50);
        add(tierLabel);
    }

    private void initShowButton()
    {
        AppButton showTiersButton = new AppButton();
        showTiersButton.setText("Show");
        showTiersButton.setBounds(200, 50, 100, 50);
        showTiersButton.setVisible(true);
        showTiersButton.setFontSize(20);
        showTiersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tierNumberArea.getTierNumberArea().setText("");
                for(String str : tier)
                {
                    tierNumberArea.getTierNumberArea().append(str + " ");
                }
            }
        });
        add(showTiersButton);
    }

}
