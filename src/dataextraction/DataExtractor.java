package dataextraction;

import com.github.greycode.xlsx.StreamingReader;
import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;
import dataextraction.datacomponents.CycleTimeRow;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Iterator;

public class DataExtractor {

    /*
     * Generates an ACT from workbook.
     */

    private ActiveCycleTimes fullACT = new ActiveCycleTimes();

    public void run(File file)
    {
        loadWorkbook(file);
    }

    private void loadWorkbook(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);

            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)
                    .bufferSize(4096)
                    .open(fis);

            extractData(workbook);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractData(Workbook workbook)
    {
        //Load only the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        boolean firstRow = true;

        //Toggles for when jobActive = 1
        boolean inBetween = false;
        CycleTimeBlock ctb = null;

        for(Row r : sheet)
        {
            if(firstRow) //Ignore first row - the headers
            {
                firstRow = false;
            }
            else
            {
                CycleTimeRow ctr = loadRowData(r);
                if(ctr.getJobActive() == 1 && !inBetween) //Start of CycleTimeBlock
                {
                    inBetween = true;
                    ctb = new CycleTimeBlock();
                    ctb.setName(Integer.toString(fullACT.size() + 1));
                    ctb.add(ctr);
                }
                if(ctr.getJobActive() == 1 && inBetween) //Middle of CycleTimeBlock
                {
                    ctb.add(ctr);
                }
                if(ctr.getJobActive() == 0 && inBetween) //End of CycleTimeBlock
                {
                    ctb.add(ctr);
                    ctb.setDuration(getDuration(ctb.get(0).getDate(), ctb.getLast().getDate(), ctb.get(0).getTime(), ctb.getLast().getTime()));
                    inBetween = false;
                    fullACT.add(ctb);
                    ctb = null; //Flush ctb - just in case
                }
            }
        }
    }

    private CycleTimeRow loadRowData(Row r)
    {
        CycleTimeRow ctr = new CycleTimeRow();
        Iterator<Cell> cellIterator = r.cellIterator();
        SimpleDateFormat format;

        int columnNumber = 1;
        while(cellIterator.hasNext())
        {
            Cell cell = cellIterator.next();
            switch(columnNumber)
            {
                case 1: // Relative Number
                    ctr.setRelativeNumber(cell.getNumericCellValue());
                    break;
                case 2: // Date
                    ctr.setDate(cell.getStringCellValue());
                    break;
                case 3: // Time
                    format = new SimpleDateFormat("HH:mm:ss.SSS");
                    String time = null;

                    if(cell.getCellType().equals(CellType.STRING)){
                        time = cell.getStringCellValue();
                    }
                    else if(cell.getCellType().equals(CellType.NUMERIC)){
                        if(DateUtil.isCellDateFormatted(cell)){
                            time = format.format(cell.getDateCellValue());
                        }
                    }
                    ctr.setTime(time);
                    break;
                case 4: // Crane Master Status
                    ctr.setCraneMasterStatus(cell.getNumericCellValue());
                    break;
                case 5: // MI Active
                    ctr.setMIActive(cell.getNumericCellValue());
                    break;
                case 6: // Intervention to RCOS
                    ctr.setRCOSIntervention(cell.getNumericCellValue());
                    break;
                case 7: // RCS Control Enable
                    ctr.setRCSControl(cell.getNumericCellValue());
                    break;
                case 8: // Job Active
                    ctr.setJobActive(cell.getNumericCellValue());
                    break;
                case 9: // Spreader Lock
                    ctr.setSpreaderLock(cell.getNumericCellValue());
                    break;
                case 10: // Spreader Unlock
                    ctr.setSpreaderUnlock(cell.getNumericCellValue());
                    break;
                case 11: // Trolley Position
                    ctr.setTrolleyPosition(cell.getNumericCellValue());
                    break;
                case 12: // Trolley Speed
                    ctr.setTrolleySpeed(cell.getNumericCellValue());
                    break;
                case 13: // Hoist Position
                    ctr.setHoistPosition(cell.getNumericCellValue());
                    break;
                case 14: // Hoist Speed
                    ctr.setHoistSpeed(cell.getNumericCellValue());
                    break;
                case 15: // Gantry Position
                    ctr.setGantryPosition(cell.getNumericCellValue());
                    break;
                case 16: // Gantry Speed
                    ctr.setGantrySpeed(cell.getNumericCellValue());
                    break;
                case 17: // FLS Activate
                    ctr.setFLSActivate(cell.getNumericCellValue());
                    break;
                case 18: // LCPS Activate
                    ctr.setLCPSActivate(cell.getNumericCellValue());
                    break;
                case 19: // SPMS Activate
                    ctr.setSPMSActivate(cell.getNumericCellValue());
                    break;
                case 20: // TPS Cold Restart
                    ctr.setTPSColdRestart(cell.getNumericCellValue());
                    break;
                case 21: // TPS Positioning
                    ctr.setTPSPositioning(cell.getNumericCellValue());
                    break;
                case 22: // CTDS Activate
                    ctr.setCTDSActivate(cell.getNumericCellValue());
                    break;
                case 23: // ATIDS State
                    ctr.setATIDSState(cell.getNumericCellValue());
                    break;
            }
            columnNumber++;
        }
        return ctr;
    }

    private double getDuration(String startDate, String endDate, String startTime, String endTime){
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("dd.MM.yyyy HH:mm:ss")
                .appendPattern("[.SSSSSSSSS][.SSSSSS][.SSS]")
                .toFormatter();

        String start = startDate + " " + startTime;
        String end = endDate + " " + endTime;

        try{
            LocalDateTime t1 = LocalDateTime.parse(start, format);
            LocalDateTime t2 = LocalDateTime.parse(end, format);
            double duration = (Duration.between(t1, t2).toMillis() * 1.0) / 1000;
            return duration;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public ActiveCycleTimes getFullACT()
    {
        return fullACT;
    }
}
