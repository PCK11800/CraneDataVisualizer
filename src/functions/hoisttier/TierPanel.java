package functions.hoisttier;

import dataextraction.datacomponents.CycleTimeBlock;
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
    private ArrayList<CycleTimeBlock> tier;
    private int rank;
    private TierNumberArea tierNumberArea;

    public TierPanel(JPanel tierDataPanel, ArrayList<CycleTimeBlock> tier, int rank, TierNumberArea tierNumberArea)
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
                StringBuilder row = new StringBuilder();
                int firstColumnLength = 9; int secondColumnLength = 12; int thirdColumnLength = 11;
                row.append("|= Cycle =|=== Date ===|= Time(s) =|\n");
                for(CycleTimeBlock ctb: tier)
                {
                    row.append(constructRow(firstColumnLength, ctb.getBlockName(), false));
                    row.append(constructRow(secondColumnLength, ctb.getDate(), false));
                    row.append(constructRow(thirdColumnLength, Double.toString(ctb.getDuration()), true));
                }
                row.append("|=========|============|===========|");
                tierNumberArea.getTierNumberArea().append(row.toString());
            }
        });
        add(showTiersButton);
    }

    private String constructRow(int columnLength, String input, boolean last)
    {
        int inputLength = input.length();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("|");
        for(int i = 0; i <= (columnLength - inputLength); i++){
            if(i == (columnLength - inputLength) / 2){
                stringBuilder.append(input);
            }
            else{
                stringBuilder.append(" ");
            }
        }
        if(last){
            stringBuilder.append("| \n");
        }
        return stringBuilder.toString();
    }
}
