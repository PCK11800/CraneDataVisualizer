package functions.hoisttier;

import dataextraction.datacomponents.ActiveCycleTimes;
import dataextraction.datacomponents.CycleTimeBlock;
import dataextraction.datacomponents.CycleTimeRow;

import java.util.ArrayList;

public class HoistTierCalculator {

    private ActiveCycleTimes act = null;
    private ArrayList<String>[] tiers = new ArrayList[5];

    /*
     * Yes, static variables. Scary shit.
     * Reason I am using static variables for the bounds
     * is because it carries on with new instances of HoistTier
     * as long as the main application is not shut down,
     * so when people need to change the hoist tier bounds
     * for once, they don't have to repeat it every single time.
     *
     * Is this good design practice? Fuck no.
     * Can I do better? Yea.
     * Will I do better? No.
     * This fucking code won't be accessed by anyone else, and
     * this project is solo developed so no one will fuck with
     * these variables intentionally. If my professors sees this
     * shit I would get a zero immediately, but hey, no one here
     * can code so who cares.
     */
    public static double bound1 = 14500;
    public static double bound2 = 17500;
    public static double bound3 = 20500;

    public HoistTierCalculator(ActiveCycleTimes act)
    {
        this.act = act;
        calculate();
    }

    private void calculate()
    {
        tiers[0] = new ArrayList<>();
        tiers[1] = new ArrayList<>();
        tiers[2] = new ArrayList<>();
        tiers[3] = new ArrayList<>();
        tiers[4] = new ArrayList<>();

        for(CycleTimeBlock ctb : act)
        {
            double height = getMaxHeightWhile_NoMI_Locked_Movement(ctb);

            if(height >= 0 && height < bound1) {
                tiers[0].add(ctb.getBlockName());
            }
            else if(height >= bound1 && height < bound2) {
                tiers[1].add(ctb.getBlockName());
            }
            else if(height >= bound2 && height < bound3) {
                tiers[2].add(ctb.getBlockName());
            }
            else if(height >= bound3) {
                tiers[3].add(ctb.getBlockName());
            }
            else{
                tiers[4].add(ctb.getBlockName());
            }
        }
    }

    private double getMaxHeightWhile_NoMI_Locked_Movement(CycleTimeBlock ctb)
    {
        boolean first = true;
        CycleTimeRow maxHeightRow = null;

        for(CycleTimeRow ctr : ctb)
        {
            if(ctr.getMIActive() == 0 && ctr.getSpreaderLock() == 1 && ctr.getTrolleySpeed() != 0){
                if(first){
                    maxHeightRow = ctr;
                    first = false;
                }
                else {
                    if(ctr.getHoistPosition() > maxHeightRow.getHoistPosition()){
                        maxHeightRow = ctr;
                    }
                }
            }
        }

        if(maxHeightRow != null){
            return maxHeightRow.getHoistPosition();
        }
        else{
            return -1;
        }
    }

    public ArrayList<String>[] getTiers()
    {
        return tiers;
    }
}
