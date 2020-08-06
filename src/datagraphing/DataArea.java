package datagraphing;

import dataextraction.datacomponents.CycleTimeBlock;
import dataextraction.datacomponents.CycleTimeRow;
import font.Inconsolata;
import userinterface.AppColors;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class DataArea extends JTextPane {

    private CycleTimeBlock ctb = null;
    private JPanel dataPanel = null;

    public DataArea(CycleTimeBlock ctb, JPanel dataPanel)
    {
        this.ctb = ctb;
        this.dataPanel = dataPanel;
        initUI();
        initData();
    }

    private void initUI()
    {
        setBounds(0, 0, dataPanel.getWidth(), dataPanel.getHeight());
        setBackground(AppColors.BACKGROUND);
        setForeground(AppColors.BORDER);
        setSelectedTextColor(AppColors.BACKGROUND);
        setSelectionColor(AppColors.BORDER);
        setFont(new Inconsolata().getFont(20));
        setEditable(false);
    }

    private double[] calculateData()
    {
        int jobActive = 0;
        int flsScan = 0;
        int rcosIntervention = 0;
        int miActive = 0;
        int rcsActive = 0;
        int tpsActive = 0;
        int ATIDSActive = 0;
        int gaMotion = 0;
        int hoistMotion = 0;
        int trolleyMotion = 0;
        int RCSRCOSEnabled = 0;

        for(CycleTimeRow ctr : ctb)
        {
            jobActive++;
            if(ctr.getFLSActivate() != 0){
                flsScan++;
            }
            if(ctr.getRCOSIntervention() != 0){
                rcosIntervention++;
            }
            if(ctr.getMIActive() != 0){
                miActive++;
            }
            if(ctr.getRCSControl() != 0){
                rcsActive++;
            }
            if(ctr.getTPSColdRestart() != 0){
                tpsActive++;
            }
            if(ctr.getATIDSState() != 0){
                ATIDSActive++;
            }
            if(ctr.getGantrySpeed() != 0){
                gaMotion++;
            }
            if(ctr.getHoistSpeed() != 0){
                hoistMotion++;
            }
            if(ctr.getTrolleySpeed() != 0){
                trolleyMotion++;
            }
            if(ctr.getRCSControl() != 0 && ctr.getRCOSIntervention() != 0){
                RCSRCOSEnabled++;
            }
        }

        double deskActive = 0;
        double[] data = {flsScan, deskActive, rcsActive, tpsActive, ATIDSActive,
                gaMotion, hoistMotion, trolleyMotion, RCSRCOSEnabled};

        double divideBy = ctb.getDuration() / jobActive;
        data[1] = rcosIntervention - miActive;
        for(int i = 0; i < data.length; i++)
        {
            data[i] = (double) Math.round((data[i] * divideBy) * 100) / 100;
        }
        return data;
    }

    private void initData()
    {
        double[] data = calculateData();
        StringBuilder str = new StringBuilder();

        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        StyleConstants.setLineSpacing(style, 0.25f);
        doc.setParagraphAttributes(doc.getLength(), doc.getLength(), style, false);

        str.append("\n");

        str.append("Date\n");
        str.append(ctb.getDate() + "\n");
        str.append("\n");

        str.append("Start Time\n");
        str.append(ctb.get(0).getTime() + "\n");
        str.append("End Time\n");
        str.append(ctb.getLast().getTime() + "\n");
        str.append("Duration\n");
        str.append(ctb.getDuration() + "s\n");
        str.append("\n");

        str.append("Desk Waiting\n");
        str.append(data[1] + "s\n");
        str.append("FLS Scan Waiting\n");
        str.append(data[0]+ "s\n");
        str.append("RCS Active\n");
        str.append(data[2] + "s\n");
        str.append("TPS Scan Active\n");
        str.append(data[3] + "s\n");
        str.append("ATIDS Active\n");
        str.append(data[4] + "s\n");
        str.append("RCS RCOS Enabled\n");
        str.append(data[8] + "s\n");
        str.append("\n");

        str.append("GA Motion\n");
        str.append(data[5] + "s\n");
        str.append("Hoist Motion\n");
        str.append(data[6] + "s\n");
        str.append("Trolley Motion\n");
        str.append(data[7] + "s\n");
        str.append("\n");

        try{
            doc.insertString(doc.getLength(), str.toString(), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
