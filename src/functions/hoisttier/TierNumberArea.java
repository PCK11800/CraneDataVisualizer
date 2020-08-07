package functions.hoisttier;

import font.Inconsolata;
import userinterface.AppColors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class TierNumberArea extends JPanel {

    private JFrame hoistTier;
    private JTextArea tierNumberArea;
    private JScrollPane scrollPane;

    private int PANEL_WIDTH = 350;
    private int PANEL_HEIGHT = 710;

    public TierNumberArea(JFrame hoistTier)
    {
        this.hoistTier = hoistTier;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        initTierNumberArea();
        initScrollPane();

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(new FlowLayout());
        setBackground(AppColors.BACKGROUND);
        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
    }

    private void initTierNumberArea()
    {
        tierNumberArea = new JTextArea();
        tierNumberArea.setEditable(false);
        tierNumberArea.setBounds(10, 10, PANEL_WIDTH, PANEL_HEIGHT);
        tierNumberArea.setFont(new Inconsolata().getFont(18));
        tierNumberArea.setForeground(AppColors.BORDER);
        tierNumberArea.setBackground(AppColors.BACKGROUND);
        tierNumberArea.setSelectedTextColor(AppColors.BACKGROUND);
        tierNumberArea.setSelectionColor(AppColors.BORDER);
        tierNumberArea.setLineWrap(true);
        tierNumberArea.setWrapStyleWord(true);
    }

    private void initScrollPane()
    {
        scrollPane = new JScrollPane(tierNumberArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

    public JTextArea getTierNumberArea()
    {
        return tierNumberArea;
    }
}
