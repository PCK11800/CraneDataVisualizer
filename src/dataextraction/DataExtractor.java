package dataextraction;

import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;
import dataextraction.datacomponents.CycleTimeRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        loadCSV(file);
    }

    private void loadCSV(File file) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .withIgnoreSurroundingSpaces()
                    .withNullString("")
            );
            extractData(csvParser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractData(CSVParser csvParser)
    {
        boolean firstRow = true;

        //Toggles for when jobActive = 1
        boolean inBetween = false;
        CycleTimeBlock ctb = null;

        for(CSVRecord record : csvParser)
        {
            if(firstRow) //Ignore first row - the headers
            {
                firstRow = false;
            }
            else
            {
                if(!checkIfContainsEmptyString(record)){
                    CycleTimeRow ctr = loadRowData(record);
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
    }

    private boolean checkIfContainsEmptyString(CSVRecord record)
    {
        boolean containsEmpty = false;
        for(int i = 0; i < 22; i++)
        {
            if(StringUtils.isEmpty(record.get(i)))
            {
                containsEmpty = true;
                break;
            }
        }
        return containsEmpty;
    }

    private CycleTimeRow loadRowData(CSVRecord record)
    {
        CycleTimeRow ctr = new CycleTimeRow();

        ctr.setRelativeNumber(Double.parseDouble(record.get(0)));
        ctr.setDate(record.get(1));
        ctr.setTime(record.get(2));
        ctr.setCraneMasterStatus(Double.parseDouble(record.get(3)));
        ctr.setMIActive(Double.parseDouble(record.get(4)));
        ctr.setRCOSIntervention(Double.parseDouble(record.get(5)));
        ctr.setRCSControl(Double.parseDouble(record.get(6)));
        ctr.setJobActive(Double.parseDouble(record.get(7)));
        ctr.setSpreaderLock(Double.parseDouble(record.get(8)));
        ctr.setSpreaderUnlock(Double.parseDouble(record.get(9)));
        ctr.setTrolleyPosition(Double.parseDouble(record.get(10)));
        ctr.setTrolleySpeed(Double.parseDouble(record.get(11)));
        ctr.setHoistPosition(Double.parseDouble(record.get(12)));
        ctr.setHoistSpeed(Double.parseDouble(record.get(13)));
        ctr.setGantryPosition(Double.parseDouble(record.get(14)));
        ctr.setGantrySpeed(Double.parseDouble(record.get(15)));
        ctr.setFLSActivate(Double.parseDouble(record.get(16)));
        ctr.setLCPSActivate(Double.parseDouble(record.get(17)));
        ctr.setSPMSActivate(Double.parseDouble(record.get(18)));
        ctr.setTPSColdRestart(Double.parseDouble(record.get(19)));
        ctr.setTPSPositioning(Double.parseDouble(record.get(20)));
        ctr.setCTDSActivate(Double.parseDouble(record.get(21)));
        ctr.setATIDSState(Double.parseDouble(record.get(22)));

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
