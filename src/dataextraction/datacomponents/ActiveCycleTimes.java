package dataextraction.datacomponents;

import java.util.ArrayList;

public class ActiveCycleTimes extends ArrayList<CycleTimeBlock> {

    public CycleTimeBlock getLast()
    {
        return this.get(this.size() - 1);
    }
}
