package functions.movecatalog;

import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;
import dataextraction.datacomponents.CycleTimeRow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class MoveCatalogCalculator {

    private ActiveCycleTimes act;
    public static int foreSideTrolleyPositionBound = 37030;
    public static int aftSideTrolleyPositionBound = 0;

    private ActiveCycleTimes foreSideLoadingJobMoves = new ActiveCycleTimes();
    private ActiveCycleTimes foreSideOffloadingJobMoves = new ActiveCycleTimes();
    private ActiveCycleTimes afterSideLoadingJobMoves = new ActiveCycleTimes();
    private ActiveCycleTimes afterSideOffloadingJobMoves = new ActiveCycleTimes();
    private ActiveCycleTimes shuffles = new ActiveCycleTimes();
    private ActiveCycleTimes na = new ActiveCycleTimes();

    public MoveCatalogCalculator(ActiveCycleTimes act)
    {
        this.act = act;
        calculate();
    }

    private void calculate()
    {
        for(CycleTimeBlock ctb : act)
        {
            double lockStatus = ctb.get(0).getSpreaderLock();
            CycleTimeRow lockRow = null;
            CycleTimeRow unlockRow = null;
            // Get row where spreader goes from unlock -> lock and lock -> unlock
            for(CycleTimeRow ctr : ctb)
            {
                if(ctr.getSpreaderLock() > lockStatus) //Lock
                {
                    lockRow = ctr;
                    lockStatus = ctr.getSpreaderLock();
                }
                if(ctr.getSpreaderLock() < lockStatus) //Unlock
                {
                    unlockRow = ctr;
                    lockStatus = ctr.getSpreaderLock();
                }
            }

            // If lock happens in "zone" and unlock happens within bounds, then it's an offloading job
            // If lock happens within bounds and unlock happens in "zone", then it's a loading job
            if(lockRow != null && unlockRow != null){

                if(unlockRow.getTrolleyPosition() <= foreSideTrolleyPositionBound && unlockRow.getTrolleyPosition() >= aftSideTrolleyPositionBound)
                {
                    if(lockRow.getTrolleyPosition() > foreSideTrolleyPositionBound){
                        foreSideOffloadingJobMoves.add(ctb);
                    }
                    else if(lockRow.getTrolleyPosition() < 0){
                        afterSideOffloadingJobMoves.add(ctb);
                    }
                }

                if(lockRow.getTrolleyPosition() <= foreSideTrolleyPositionBound && lockRow.getTrolleyPosition() >= aftSideTrolleyPositionBound)
                {
                    if(unlockRow.getTrolleyPosition() > foreSideTrolleyPositionBound){
                        foreSideLoadingJobMoves.add(ctb);
                    }
                    else if(unlockRow.getTrolleyPosition() < 0){
                        afterSideLoadingJobMoves.add(ctb);
                    }
                }

                if(lockRow.getTrolleyPosition() <= foreSideTrolleyPositionBound && lockRow.getTrolleyPosition() >= aftSideTrolleyPositionBound
                && unlockRow.getTrolleyPosition() <= foreSideTrolleyPositionBound && unlockRow.getTrolleyPosition() >= aftSideTrolleyPositionBound){
                    shuffles.add(ctb);
                }
            }
            else{
                na.add(ctb);
            }
        }
    }

    private void print()
    {
        System.out.println("FS LJM: " + foreSideLoadingJobMoves.size());
        System.out.println("FS OJM: " + foreSideOffloadingJobMoves.size());
        System.out.println("AS LJM: " + afterSideLoadingJobMoves.size());
        System.out.println("AS OJM: " + afterSideOffloadingJobMoves.size());
        System.out.println("Shuffle: " + shuffles.size());
        System.out.println("N/A: " + na.size());
    }

    public ArrayList<ActiveCycleTimes> getMoveCatalogs()
    {
        ArrayList<ActiveCycleTimes> moveCatalogs = new ArrayList<>();
        moveCatalogs.add(foreSideLoadingJobMoves);
        moveCatalogs.add(foreSideOffloadingJobMoves);
        moveCatalogs.add(afterSideLoadingJobMoves);
        moveCatalogs.add(afterSideOffloadingJobMoves);
        moveCatalogs.add(shuffles);
        moveCatalogs.add(na);

        return moveCatalogs;
    }

    public static double getAverageTime(ActiveCycleTimes moveCatalog)
    {
        double totalTime = 0;
        double averageTime = 0;

        for(CycleTimeBlock ctb : moveCatalog)
        {
            totalTime = totalTime + ctb.getDuration();
        }

        if (!(totalTime <= 0)) {
            averageTime = totalTime / moveCatalog.size();
            averageTime = round(averageTime, 2);
        }
        return averageTime;
    }

    public static double getGantryAverageTime(ActiveCycleTimes moveCatalog)
    {
        double totalTime = 0;
        double averageTime = 0;

        for(CycleTimeBlock ctb : moveCatalog)
        {
            int jobActive = 0;
            int gaMotion = 0;
            for(CycleTimeRow ctr : ctb)
            {
                jobActive++;
                if(ctr.getGantrySpeed() != 0) {
                    gaMotion++;
                }
            }
            double divideBy = ctb.getDuration() / jobActive;
            totalTime = totalTime + (double) Math.round((gaMotion * divideBy) * 100) / 100;
        }

        if (!(totalTime <= 0)) {
            averageTime = totalTime / moveCatalog.size();
            averageTime = round(averageTime, 2);
        }
        return averageTime;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
