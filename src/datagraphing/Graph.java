package datagraphing;

import dataextraction.datacomponents.CycleTimeBlock;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import userinterface.AppColors;

import javax.swing.*;
import java.awt.*;

public class Graph extends JFrame {

    /*
     * This JFrame contains two JPanels - one panel holds the two charts
     * and the other holds a JTextArea showing information
     *
     * Contains two datasets - one for discrete data with a singular value
     * , and another for continuous data with two dimensional values.
     *
     * What this means exactly is that some data such as hoist position have
     * both a height value that varies and a time value where as some has a 0
     * or 1 value which corresponds to on or off.
     *
     * Thus two different charts.
     *
     * PS: Legend max length: 17 char (incl space)
     */

    static final int APP_WIDTH = 1300;
    static final int APP_HEIGHT = 840;
    static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private CycleTimeBlock ctb = null;

    public Graph(CycleTimeBlock ctb)
    {
        this.ctb = ctb;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setTitle("Cycle Time " + ctb.getBlockName());
        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        setResizable(false);
        getContentPane().setBackground(AppColors.BACKGROUND);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        initDataPanel();
        initChartPanel();
        initPadding();

        pack();
        repaint();
    }

    private void initChartPanel()
    {
        JPanel chartPanel = new JPanel();
        chartPanel.setPreferredSize(new Dimension(980, APP_HEIGHT));
        //chartPanel.setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        chartPanel.setBackground(AppColors.BACKGROUND);
        chartPanel.setVisible(true);
        chartPanel.setLayout(new GridLayout(2, 1));

        initContinuousChart(chartPanel);
        initDiscreteChart(chartPanel);

        add(chartPanel);
    }

    private void initDataPanel()
    {
        JPanel dataPanel = new JPanel();
        dataPanel.setPreferredSize(new Dimension(300, APP_HEIGHT));
        //dataPanel.setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        dataPanel.setBackground(AppColors.BACKGROUND);
        dataPanel.setVisible(true);
        add(dataPanel);
    }

    private void initPadding()
    {
        /*
         * Don't wanna use JFreeChart padding because it can and will
         * fucking break something. No need to tempt fate.
         */
        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(20, APP_HEIGHT));
        padding.setBackground(AppColors.BACKGROUND);
        padding.setVisible(true);
        add(padding);
    }

    private void initContinuousChart(JPanel chartPanel)
    {
        ContinuousDataset dataset = new ContinuousDataset(ctb);
        JFreeChart continuousChart = new ContinuousChart(dataset).getContinuousChart();
        ChartPanel continuousChartPanel = new ChartPanel(continuousChart);
        chartPanel.add(continuousChartPanel);
    }

    private void initDiscreteChart(JPanel chartPanel)
    {
        DiscreteDataset dataset = new DiscreteDataset(ctb);
        JFreeChart discreteChart = new DiscreteChart(dataset).getDiscreteChart();
        ChartPanel discreteChartPanel = new ChartPanel(discreteChart);
        chartPanel.add(discreteChartPanel);
    }

}
