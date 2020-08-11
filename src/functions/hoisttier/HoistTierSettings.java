package functions.hoisttier;

import font.Inconsolata;
import userinterface.AppColors;
import userinterface.OSCheck;
import userinterface.components.AppButton;
import userinterface.components.extras.GhostText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoistTierSettings extends JFrame {

    private int APP_WIDTH = 360;
    private int APP_HEIGHT = 720;
    private JTextField[] fields = new JTextField[3];
    private JLabel[] labels = new JLabel[3];

    public HoistTierSettings()
    {
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setTitle("Hoist Tier Settings");
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
        settingPanel.setLayout(new GridLayout(4, 0));
        settingPanel.setBackground(AppColors.BACKGROUND);

        add(settingPanel);

        for(int i = 0; i < 3; i++)
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
                for(int i = 0; i < 3; i++){
                    int fieldInput = Integer.parseInt(fields[i].getText());

                    switch(i) {
                        case 0:
                            HoistTierCalculator.bound1 = fieldInput;
                            break;
                        case 1:
                            HoistTierCalculator.bound2 = fieldInput;
                            break;
                        case 2:
                            HoistTierCalculator.bound3 = fieldInput;
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
        new GhostText(inputField, "bound" + (i + 1));
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
                label.setText("Default: 14500   Current: " + HoistTierCalculator.bound1);
                break;
            case 1:
                label.setText("Default: 17500   Current: " + HoistTierCalculator.bound2);
                break;
            case 2:
                label.setText("Default: 20500   Current: " + HoistTierCalculator.bound3);
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
                "Hoist tiers are determined by calculating whether or not the maximum hoist " +
                "position while MI is off, spreader is locked and the trolley is moving is " +
                "within four different bounds.\n\n" +
                "Let's say max hoist position is p: \n" +
                "Tier 3: 0 < p < bound1 \n" +
                "Tier 4: bound1 <= p < bound2 \n" +
                "Tier 5: bound2 <= p < bound3 \n" +
                "Tier 6: p >= bound3 \n\n" +
                "Here, you can change the default hoist setting values if required, although " +
                "it shouldn't be required at all since cargo containers don't change height " +
                "spontaneously."
        );
        add(instructions);
    }
}
