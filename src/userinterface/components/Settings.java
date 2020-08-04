package userinterface.components;

import font.Inconsolata;
import userinterface.AppColors;
import userinterface.components.extras.GhostText;
import userinterface.components.extras.JTextFieldLimit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Settings extends JPanel {

    /*
     * This panel holds all the filters and the all important
     * select file button (and maybe some other stuff when I
     * think of them)
     */

    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 270;

    public Settings()
    {
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        initFileChooser();
        initFilterOptions();
        initPreviewButton();

        setLayout(null);
        setVisible(true);
        setBounds(10, 440, PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(AppColors.BACKGROUND);
        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));

        repaint();
    }

    private void initFileChooser()
    {
        AppButton fileChooserButton = new AppButton();
        fileChooserButton.setText("Choose file");
        fileChooserButton.setFontSize(20);
        fileChooserButton.setBounds(25, 0, 550, 50);

        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser();
            }
        });

        add(fileChooserButton);
    }

    private void openFileChooser()
    {
        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.dir")));
        int status = chooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file == null) {
                return;
            }
        }
    }

    private void initFilterOptions()
    {
        JLabel startLabel = new JLabel("Start");
        setLabelSettings(startLabel);
        startLabel.setBounds(35, 70, 80, 55);
        add(startLabel);

        JLabel endLabel = new JLabel("End");
        setLabelSettings(endLabel);
        endLabel.setBounds(35, 130, 80, 55);
        add(endLabel);

        JTextField startDateField = new JTextField(10);
        startDateField.setBounds(115, 70, 180, 50);
        setFieldSettings(startDateField);
        startDateField.setDocument(new JTextFieldLimit(10));
        new GhostText(startDateField, "dd.mm.yyyy");
        add(startDateField);

        JTextField endDateField = new JTextField(10);
        endDateField.setBounds(115, 130, 180, 50);
        setFieldSettings(endDateField);
        endDateField.setDocument(new JTextFieldLimit(10));
        new GhostText(endDateField, "dd.mm.yyyy");
        add(endDateField);

        JTextField startTimeField = new JTextField(12);
        startTimeField.setBounds(305, 70, 180, 50);
        setFieldSettings(startTimeField);
        startTimeField.setDocument(new JTextFieldLimit(12));
        new GhostText(startTimeField, "hh:mm:ss:SSS");
        add(startTimeField);

        JTextField endTimeField = new JTextField(12);
        endTimeField.setBounds(305, 130, 180, 50);
        setFieldSettings(endTimeField);
        endTimeField.setDocument(new JTextFieldLimit(12));
        new GhostText(endTimeField, "hh:mm:ss:SSS");
        add(endTimeField);

        AppButton filterButton = new AppButton();
        filterButton.setText("Filter");
        filterButton.setFontSize(20);
        filterButton.setBounds(495, 70, 80, 110);
        add(filterButton);
    }

    private void setFieldSettings(JTextField textField)
    {
        int fontSize = 25;
        textField.setBackground(AppColors.BACKGROUND);
        textField.setForeground(AppColors.BORDER);
        textField.setFont(new Inconsolata().getFont(fontSize));
        textField.setCaretColor(AppColors.BORDER);
    }

    private void setLabelSettings(JLabel label)
    {
        int fontSize = 25;
        label.setFont(new Inconsolata().getFont(fontSize));
        label.setBackground(AppColors.BACKGROUND);
        label.setForeground(AppColors.BORDER);
    }

    private void initPreviewButton()
    {
        AppButton previewButton = new AppButton();
        previewButton.setText("Preview");
        previewButton.setFontSize(20);
        previewButton.setBounds(25, 200, 550, 70);
        add(previewButton);
    }
}
