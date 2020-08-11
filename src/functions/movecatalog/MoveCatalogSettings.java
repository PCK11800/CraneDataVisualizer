package functions.movecatalog;

import font.Inconsolata;
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
    private int APP_HEIGHT = 360;

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
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 0));
        panel.setBackground(AppColors.BACKGROUND);

        add(panel);

        JTextField inputField = new JTextField(10);
        new GhostText(inputField, "trolleyPositionBound");
        setFieldSettings(inputField);
        inputField.setBounds(10, 10, APP_WIDTH - 20, APP_HEIGHT / 16);
        panel.add(inputField);

        JLabel label = new JLabel();
        label.setFont(new Inconsolata().getFont(16));
        label.setBackground(AppColors.BACKGROUND);
        label.setForeground(AppColors.BORDER);
        label.setText("Default: 37030   Current: " + MoveCatalogCalculator.trolleyPositionBound);
        label.setBounds(10, 55, APP_WIDTH - 20, APP_HEIGHT / 16);
        panel.add(label);

        AppButton set = new AppButton();
        set.setText("Set");
        set.setFontSize(16);
        set.setBounds(10, 10, APP_WIDTH - 20, APP_HEIGHT / 16);
        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fieldInput = Integer.parseInt(inputField.getText());
                MoveCatalogCalculator.trolleyPositionBound = fieldInput;
                label.setText("Default: 37030   Current: " + MoveCatalogCalculator.trolleyPositionBound);
                repaint();
            }
        });
        panel.add(set);
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
                        "You can set the trolley distance where >distance is " +
                        "where the trolley is hanging over the truck."
        );
        add(instructions);
    }
}
