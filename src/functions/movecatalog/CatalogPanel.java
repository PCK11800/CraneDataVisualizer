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
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s");
                break;
            case 1:
                data.append("FSTLM Offloading: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s");
                break;
            case 2:
                data.append("ASTLM Loading: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s");
                break;
            case 3:
                data.append("ASTLM Offloading: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s");
                break;
            case 4:
                data.append("Shuffling: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s");
                break;
            case 5:
                data.append("N/A: " + moveCatalog.size() + "\n");
                data.append("Avg Time: " + MoveCatalogCalculator.getAverageTime(moveCatalog) + "s");
                break;
        }
        data.setFont(new Inconsolata().getFont(20));
        data.setBackground(AppColors.BACKGROUND);
        data.setForeground(AppColors.BORDER);
        data.setEditable(false);
        data.setSelectedTextColor(AppColors.BACKGROUND);
        data.setSelectionColor(AppColors.BORDER);
        data.setBounds(10, 30, 220, 50);
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
                for(CycleTimeBlock ctb : moveCatalog)
                {
                    catalogNumberArea.getCatalogNumberArea().append(ctb.getBlockName() + " ");
                }
            }
        });
        add(showCatalogButton);
    }
}
