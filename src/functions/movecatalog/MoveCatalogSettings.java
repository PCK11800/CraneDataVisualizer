package functions.movecatalog;

import font.Inconsolata;
import functions.movecatalog.MoveCatalogCalculator;
import userinterface.AppColors;
import userinterface.OSCheck;
import userinterface.components.AppButton;
import userinterface.components.extras.GhostText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveCatalogSettings extends JFrame {

    private int APP_WIDTH = 360;
    private int APP_HEIGHT = 720;
    private JTextField[] fields = new JTextField[2];
    private JLabel[] labels = new JLabel[2];

    public MoveCatalogSettings()
    {
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setTitle("Move Catalog Settings");
        getContentPane().setBackground(AppColors.BACKGROUND);
        getContentPane().setLayout(new GridLayout(2, 0));
        setResizable(false);

        initSettingsArea();
        initInstructionsArea();

        if(OSCheck.getOS().equals("WIN"))
        {
            APP_WIDTH = APP_WIDTH + 20;
            APP_HEIGHT = APP_HEIGHT + 45;
        }
        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));

        pack();
        repaint();
    }

    private void initSettingsArea()
    {
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayout(3, 0));
        settingPanel.setBackground(AppColors.BACKGROUND);

        add(settingPanel);

        for(int i = 0; i < 2; i++)
        {
            settingPanel.add(settingsPanel(i));
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(AppColors.BACKGROUND);
        buttonPanel.setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT / 8));

        AppButton set = new AppButton();
        set.setText("Set");
        set.setFontSize(16);
        set.setBounds(10, 10, APP_WIDTH - 20, APP_HEIGHT / 16);
        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < 2; i++){
                    int fieldInput = Integer.parseInt(fields[i].getText());

                    switch(i) {
                        case 0:
                            MoveCatalogCalculator.foreSideTrolleyPositionBound = fieldInput;
                            break;
                        case 1:
                            MoveCatalogCalculator.aftSideTrolleyPositionBound = fieldInput;
                            break;
                    }

                    setLabelSettings(i, labels[i]);
                    repaint();
                }
            }
        });
        buttonPanel.add(set);

        settingPanel.add(buttonPanel);
    }

    private JPanel settingsPanel(int i)
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT / 8));
        panel.setBackground(AppColors.BACKGROUND);
        //panel.setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        panel.setLayout(null);

        JTextField inputField = new JTextField(10);
        fields[i] = inputField;
        if(i == 0) { new GhostText(inputField, "foreSideTrolleyPositionBounds"); }
        else { new GhostText(inputField, "aftSideTrolleyPositionBounds"); }
        setFieldSettings(inputField);
        inputField.setBounds(10, 10, APP_WIDTH - 20, APP_HEIGHT / 16);
        panel.add(inputField);

        JLabel label = new JLabel();
        labels[i] = label;
        setLabelSettings(i, label);
        label.setBounds(10, 55, APP_WIDTH - 20, APP_HEIGHT / 16);
        panel.add(label);

        return panel;
    }

    private void setFieldSettings(JTextField textField)
    {
        int fontSize = 22;
        textField.setBackground(AppColors.BACKGROUND);
        textField.setForeground(AppColors.BORDER);
        textField.setFont(new Inconsolata().getFont(fontSize));
        textField.setCaretColor(AppColors.BORDER);
        textField.setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    private void setLabelSettings(int i, JLabel label)
    {
        label.setFont(new Inconsolata().getFont(16));
        label.setBackground(AppColors.BACKGROUND);
        label.setForeground(AppColors.BORDER);
        switch(i)
        {
            case 0:
                label.setText("Default: 37030   Current: " + MoveCatalogCalculator.foreSideTrolleyPositionBound);
                break;
            case 1:
                label.setText("Default: 0   Current: " + MoveCatalogCalculator.aftSideTrolleyPositionBound);
                break;
        }
    }

    private void initInstructionsArea()
    {
        JTextArea instructions = new JTextArea();
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setFont(new Inconsolata().getFont(16));
        instructions.setForeground(AppColors.BORDER);
        instructions.setBackground(AppColors.BACKGROUND);
        instructions.setSelectedTextColor(AppColors.BACKGROUND);
        instructions.setSelectionColor(AppColors.BORDER);

        instructions.setText(
                "Settings Instructions \n\n" +
                        "You can set the specific position where the trolley can be considered to " +
                        "over the foreside or aftside of the crane. \n\n" +
                        "If trolley position > foreSideTrolleyBound: trolley is over the foreside. \n\n" +
                        "If trolley position < aftSideTrolleyBound: trolley is over the aftside."
        );
        add(instructions);
    }
}
