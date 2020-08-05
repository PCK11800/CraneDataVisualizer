package userinterface.components;

import dataextraction.CSVConverter;
import dataextraction.DataExtractor;
import dataextraction.DataFilter;
import datagraphing.Graph;
import font.Inconsolata;
import userinterface.AppColors;
import userinterface.Application;
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

    private Application app;
    private static final int PANEL_WIDTH = 550;
    private static final int PANEL_HEIGHT = 270;

    public Settings(Application app)
    {
        this.app = app;
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        initFileChooser();
        initFilterOptions();
        initPreview();

        setLayout(null);
        setVisible(true);
        setBounds(720, 10, PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(AppColors.BACKGROUND);
        //setBorder(BorderFactory.createLineBorder(AppColors.BORDER, 1));

        repaint();
    }

    private void initFileChooser()
    {
        AppButton fileChooserButton = new AppButton();
        fileChooserButton.setText("Choose file");
        fileChooserButton.setFontSize(20);
        fileChooserButton.setBounds(0, 0, 550, 50);

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
            String fileName = chooser.getSelectedFile().getName();
            if(fileName.endsWith(".xlsx"))
            {
                DataExtractor dE = new DataExtractor();
                dE.run(file);
                app.setFullACT(dE.getFullACT());
                app.screenview.printData();
            }
            else if(fileName.endsWith(".csv"))
            {
                CSVConverter converter = new CSVConverter(file);
                DataExtractor dE = new DataExtractor();
                dE.run(converter.getOutputFile());
                app.setFullACT(dE.getFullACT());
                app.screenview.printData();
            }
        }
    }

    private void initFilterOptions()
    {
        JLabel startLabel = new JLabel("Start");
        setLabelSettings(startLabel);
        startLabel.setBounds(0, 70, 80, 55);
        add(startLabel);

        JLabel endLabel = new JLabel("End");
        setLabelSettings(endLabel);
        endLabel.setBounds(0, 130, 80, 55);
        add(endLabel);

        JTextField startDateField = new JTextField(10);
        startDateField.setBounds(75, 70, 120, 50);
        setFieldSettings(startDateField);
        startDateField.setDocument(new JTextFieldLimit(10));
        new GhostText(startDateField, "dd.mm.yyyy");
        add(startDateField);

        JTextField endDateField = new JTextField(10);
        endDateField.setBounds(75, 130, 120, 50);
        setFieldSettings(endDateField);
        endDateField.setDocument(new JTextFieldLimit(10));
        new GhostText(endDateField, "dd.mm.yyyy");
        add(endDateField);

        JTextField startTimeField = new JTextField(12);
        startTimeField.setBounds(200, 70, 140, 50);
        setFieldSettings(startTimeField);
        startTimeField.setDocument(new JTextFieldLimit(12));
        new GhostText(startTimeField, "hh:mm:ss.SSS");
        add(startTimeField);

        JTextField endTimeField = new JTextField(12);
        endTimeField.setBounds(200, 130, 140, 50);
        setFieldSettings(endTimeField);
        endTimeField.setDocument(new JTextFieldLimit(12));
        new GhostText(endTimeField, "hh:mm:ss.SSS");
        add(endTimeField);

        JTextField startDurationField = new JTextField();
        startDurationField.setBounds(345, 70, 120, 50);
        setFieldSettings(startDurationField);
        new GhostText(startDurationField, "Min.Sec");
        add(startDurationField);

        JTextField endDurationField = new JTextField();
        endDurationField.setBounds(345, 130, 120, 50);
        setFieldSettings(endDurationField);
        new GhostText(endDurationField, "Max.Sec");
        add(endDurationField);

        AppButton filterButton = new AppButton();
        filterButton.setText("Filter");
        filterButton.setFontSize(20);
        filterButton.setBounds(470, 70, 80, 110);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterButtonAction(startDateField, endDateField, startTimeField, endTimeField, startDurationField, endDurationField);
            }
        });
        add(filterButton);
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

    private void setLabelSettings(JLabel label)
    {
        int fontSize = 25;
        label.setFont(new Inconsolata().getFont(fontSize));
        label.setBackground(AppColors.BACKGROUND);
        label.setForeground(AppColors.BORDER);
    }

    private void initPreview()
    {
        JTextField previewInput = new JTextField();
        previewInput.setBounds(0, 200, 270, 70);
        setFieldSettings(previewInput);
        add(previewInput);

        AppButton previewButton = new AppButton();
        previewButton.setText("Preview");
        previewButton.setFontSize(20);
        previewButton.setBounds(280, 200, 270, 70);
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ctbNum = Integer.parseInt(previewInput.getText());
                new Graph(app.getFullACT().get(ctbNum - 1));
            }
        });
        add(previewButton);
    }

    private void filterButtonAction(JTextField startDateField, JTextField endDateField,
                                    JTextField startTimeField, JTextField endTimeField,
                                    JTextField startDurationField, JTextField endDurationField) {
        String startDate = null, endDate = null, startTime = null, endTime = null, startDuration = null, endDuration = null;
        //Load default values
        startDate = app.getFullACT().get(0).getDate();
        endDate = app.getFullACT().getLast().getDate();
        startTime = "00:00:00.000";
        endTime = "24:00:00.000";
        startDuration = "0";
        endDuration = "9999999999";

        if (!startDateField.equals(null) && !startDateField.getText().equals("dd.mm.yyyy")) {
            startDate = startDateField.getText();
        }
        if (!endDateField.equals(null) && !endDateField.getText().equals("dd.mm.yyyy")) {
            endDate = endDateField.getText();
        }
        if (!startTimeField.equals(null) && !startTimeField.getText().equals("hh:mm:ss.SSS")) {
            startTime = startTimeField.getText();
        }
        if (!endTimeField.equals(null) && !endTimeField.getText().equals("hh:mm:ss.SSS")) {
            endTime = endTimeField.getText();
        }
        if (!startDurationField.equals(null) && !startDurationField.getText().equals("Min.Sec")) {
            startDuration = startDurationField.getText();
        }
        if (!endDurationField.equals(null) && !endDurationField.getText().equals("Max.Sec")) {
            endDuration = endDurationField.getText();
        }

        DataFilter filter = new DataFilter();
        app.setPartialACT(filter.getCycleWithinRange(app.getFullACT(), startDate, endDate, startTime, endTime, startDuration, endDuration));
        app.screenview.printData();
    }
}
