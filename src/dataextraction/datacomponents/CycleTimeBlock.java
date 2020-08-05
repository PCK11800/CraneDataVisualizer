package dataextraction.datacomponents;

import java.util.ArrayList;

public class CycleTimeBlock extends ArrayList<CycleTimeRow> {

    private String blockName;
    private double duration;

    public void setName(String blockName)
    {
        this.blockName = blockName;
    }

    public String getBlockName()
    {
        return blockName;
    }

    public CycleTimeRow getLast()
    {
        return get(size() - 1);
    }

    public String getDate()
    {
        return this.get(0).getDate();
    }

    public void setDuration(double duration)
    {
        this.duration = duration;
    }

    public double getDuration()
    {
        return duration;
    }

}
