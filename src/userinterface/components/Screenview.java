package userinterface.components;

import userinterface.AppColors;
import font.Inconsolata;

import javax.swing.*;
import java.awt.*;

public class Screenview extends JPanel {

    /*
     * This panel holds a JTextArea that presents the user
     * with the cycle time data.
     */

    private static final int PANEL_WIDTH = 680;
    private static final int PANEL_HEIGHT = 710;

    private JTextArea textArea;
    private JScrollPane scrollPane;

    public Screenview()
    {
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
        textArea.setFont(new Inconsolata().getFont(40));
        textArea.setForeground(AppColors.BORDER);
        textArea.setBackground(AppColors.BACKGROUND);
        textArea.setSelectedTextColor(AppColors.BACKGROUND);
        textArea.setSelectionColor(AppColors.BORDER);
        initTextAreaExampleText();
    }

    private void initScrollPane()
    {
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.setBackground(AppColors.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createLineBorder(AppColors.BACKGROUND, 1));

        add(scrollPane);
    }

    private void initTextAreaExampleText()
    {
        textArea.setText("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

}
