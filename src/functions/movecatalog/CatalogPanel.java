package functions.movecatalog;

import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;
import font.Inconsolata;
import functions.hoisttier.HoistTierCalculator;
import userinterface.AppColors;
import userinterface.components.AppButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CatalogPanel extends JPanel {

    private JPanel catalogDataPanel;
    private ActiveCycleTimes moveCatalog;
    private int rank;
    private CatalogNumberArea catalogNumberArea;

    public CatalogPanel(JPanel catalogDataPanel, ActiveCycleTimes moveCatalog, int rank, CatalogNumberArea catalogNumberArea)
    {
        this.catalogDataPanel = catalogDataPanel;
        this.moveCatalog = moveCatalog;
        this.rank = rank;
        this.catalogNumberArea = catalogNumberArea;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setPreferredSize(new Dimension(catalogDataPanel.getWidth(), catalogDataPanel.getHeight() / 6));
        setLayout(null);
        setBackground(AppColors.BACKGROUND);

        initCatalogLabel();
        initShowButton();
    }

    private void initCatalogLabel()
    {
        JTextArea data = new JTextArea();
        switch(rank)
        {
            case 0:
                data.append("FSTLM Loading: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s\n");
                data.append("Gantry Avg Time: " + MoveCatalogCalculator.getGantryAverageTime(moveCatalog) + "s");
                break;
            case 1:
                data.append("FSTLM Offloading: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s\n");
                data.append("Gantry Avg Time: " + MoveCatalogCalculator.getGantryAverageTime(moveCatalog) + "s");
                break;
            case 2:
                data.append("ASTLM Loading: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s\n");
                data.append("Gantry Avg Time: " + MoveCatalogCalculator.getGantryAverageTime(moveCatalog) + "s");
                break;
            case 3:
                data.append("ASTLM Offloading: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s\n");
                data.append("Gantry Avg Time: " + MoveCatalogCalculator.getGantryAverageTime(moveCatalog) + "s");
                break;
            case 4:
                data.append("Shuffling: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s\n");
                data.append("Gantry Avg Time: " + MoveCatalogCalculator.getGantryAverageTime(moveCatalog) + "s");
                break;
            case 5:
                data.append("N/A: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s\n");
                data.append("Gantry Avg Time: " + MoveCatalogCalculator.getGantryAverageTime(moveCatalog) + "s");
                break;
        }
        data.setFont(new Inconsolata().getFont(18));
        data.setBackground(AppColors.BACKGROUND);
        data.setForeground(AppColors.BORDER);
        data.setEditable(false);
        data.setSelectedTextColor(AppColors.BACKGROUND);
        data.setSelectionColor(AppColors.BORDER);
        data.setBounds(10, 30, 220, 80);
        add(data);
    }

    private void initShowButton()
    {
        AppButton showCatalogButton = new AppButton();
        showCatalogButton.setText("Show");
        showCatalogButton.setBounds(240, 30, 100, 50);
        showCatalogButton.setVisible(true);
        showCatalogButton.setFontSize(20);
        showCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogNumberArea.getCatalogNumberArea().setText("");
                StringBuilder row = new StringBuilder();
                int firstColumnLength = 9; int secondColumnLength = 12; int thirdColumnLength = 11;
                row.append("|= Cycle =|=== Date ===|= Time(s) =|\n");
                for(CycleTimeBlock ctb : moveCatalog)
                {
                    row.append(constructRow(firstColumnLength, ctb.getBlockName(), false));
                    row.append(constructRow(secondColumnLength, ctb.getDate(), false));
                    row.append(constructRow(thirdColumnLength, Double.toString(ctb.getDuration()), true));
                }
                row.append("|=========|============|===========|");
                catalogNumberArea.getCatalogNumberArea().append(row.toString());
            }
        });
        add(showCatalogButton);
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
