package userinterface.components;

import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;
import userinterface.AppColors;
import font.Inconsolata;
import userinterface.Application;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class Screenview extends JPanel {

    /*
     * This panel holds a JTextArea that presents the user
     * with the cycle time data.
     */

    private static final int PANEL_WIDTH = 680;
    private static final int PANEL_HEIGHT = 710;

    private Application app;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public Screenview(Application app)
    {
        this.app = app;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        initTextArea();
        initScrollPane();

        setLayout(new FlowLayout());
        setVisible(true);
        setBounds(10, 10, PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(AppColors.BACKGROUND);
        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));

        repaint();
    }

    private void initTextArea()
    {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        textArea.setFont(new Inconsolata().getFont(18));
        textArea.setForeground(AppColors.BORDER);
        textArea.setBackground(AppColors.BACKGROUND);
        textArea.setSelectedTextColor(AppColors.BACKGROUND);
        textArea.setSelectionColor(AppColors.BORDER);
        initTextAreaExampleText();
    }

    private void initScrollPane()
    {
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT - 15));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5,1));
        scrollPane.getVerticalScrollBar().setBackground(AppColors.BACKGROUND);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
           @Override
            protected void configureScrollBarColors(){
               this.thumbColor = AppColors.BORDER;
           }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
        scrollPane.setBackground(AppColors.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createLineBorder(AppColors.BACKGROUND, 1));

        add(scrollPane);
    }

    private void initTextAreaExampleText()
    {
        textArea.setText("This application takes a .xlsx sheet of cycle time raw data and " +
                "outputs charts visualizing the data. \n" +
                "\n" +
                "Instructions:" +
                "\n" +
                "1. Open a .xlsx file containing cycle time raw data. \n" +
                "2. Wait for program to load file. \n" +
                "3. Enter cycle time and hit preview to generate a chart.");

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    public void printData()
    {
        ActiveCycleTimes act = app.getFullACT();
        if(app.getPartialACT() != null)
        {
            act = app.getPartialACT();
        }

        StringBuilder row = new StringBuilder();
        int firstColumnLength = 9, secondColumnLength = 14, thirdColumnLength = 16, fourthColumnLength = 16, fifthColumnLength = 11;
        row.append("|= Cycle =|==== Date ====|== Start Time ==|=== End Time ===|= Time(s) =|\n");
        for(CycleTimeBlock ctb : act){
            String ctbName = ctb.getBlockName();
            String ctbStartDate = ctb.get(0).getDate();
            String ctbEndDate = ctb.getLast().getDate();
            String startTime = ctb.get(0).getTime();
            String endTime = ctb.get(ctb.size() - 1).getTime();
            String duration = Double.toString(ctb.getDuration());

            //Build Row
            row.append(constructRow(firstColumnLength, ctbName, false));
            row.append(constructRow(secondColumnLength, ctbStartDate, false));
            row.append(constructRow(thirdColumnLength, startTime, false));
            row.append(constructRow(fourthColumnLength, endTime, false));
            row.append(constructRow(fifthColumnLength, duration, true));
        }
        row.append("|=========|==============|================|================|===========|");

        textArea.setText(row.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        repaint();
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
