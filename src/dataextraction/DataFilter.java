package dataextraction;

import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFilter {

    public ActiveCycleTimes getCycleWithinRange(ActiveCycleTimes act,
                                                String dateStart, String dateEnd,
                                                String timeStart, String timeEnd,
                                                String durationStart, String durationEnd)
    {
        ActiveCycleTimes firstFilteredACT  = new ActiveCycleTimes();
        ActiveCycleTimes secondFilteredACT = new ActiveCycleTimes();
        ActiveCycleTimes thirdFilteredACT = new ActiveCycleTimes();
        Date[] parsedDateTimes = parseDateTime(dateStart, dateEnd, timeStart, timeEnd);

        // Filter date
        for(CycleTimeBlock ctb : act) {
            Date ctb_startDate = parseDate(ctb.get(0).getDate());
            Date ctb_endDate = parseDate(ctb.getLast().getDate());
            if(!ctb_startDate.before(parsedDateTimes[0]) && !ctb_endDate.after(parsedDateTimes[1])){
                firstFilteredACT.add(ctb);
            }
        }

        //Filter time
        for(CycleTimeBlock ctb : firstFilteredACT) {
            Date ctb_startTime = parseTime(ctb.get(0).getTime());
            Date ctb_endTime = parseTime(ctb.getLast().getTime());
            if(!ctb_startTime.before(parsedDateTimes[2]) && !ctb_endTime.after(parsedDateTimes[3])){
                secondFilteredACT.add(ctb);
            }
        }

        //Filter duration
        for(CycleTimeBlock ctb : secondFilteredACT) {
            double start = Double.parseDouble(durationStart) * 1.0;
            double end = Double.parseDouble(durationEnd) * 1.0;

            if(!(ctb.getDuration() < start) && !(ctb.getDuration() > end))
            {
                thirdFilteredACT.add(ctb);
            }
        }

        return thirdFilteredACT;
    }

    private Date[] parseDateTime(String dateStart, String dateEnd, String timeStart, String timeEnd)
    {
        Date[] parsedDateTimes = new Date[4];
        parsedDateTimes[0] = parseDate(dateStart);
        parsedDateTimes[1] = parseDate(dateEnd);
        parsedDateTimes[2] = parseTime(timeStart);
        parsedDateTimes[3] = parseTime(timeEnd);

        return parsedDateTimes;
    }

    private Date parseDate(String date)
    {
        Date parsedDate = null;
        try{
            SimpleDateFormat date_formatter = new SimpleDateFormat("dd.MM.yyyy");
            parsedDate = date_formatter.parse(date);
        } catch (ParseException e) { e.printStackTrace(); }

        return parsedDate;
    }

    private Date parseTime(String time)
    {
        Date parsedTime = null;
        try{
            SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm:ss.SSS");
            parsedTime = time_formatter.parse(time);
        } catch (ParseException e) { e.printStackTrace(); }

        return parsedTime;
    }
}
