package datagraphing;

import dataextraction.datacomponents.CycleTimeBlock;
import dataextraction.datacomponents.CycleTimeRow;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ContinuousDataset extends XYSeriesCollection{

    private CycleTimeBlock ctb = null;

    public ContinuousDataset(CycleTimeBlock ctb)
    {
        this.ctb = ctb;
        createDataset();
    }

    XYSeries zeroLine = new XYSeries("");
    XYSeries hoistPosition = new XYSeries("Hoist Position");
    XYSeries trolleyPosition = new XYSeries("Trolley Position");

    private void createDataset()
    {
        double firstRowNumber = ctb.get(0).getRelativeNumber();

        for(CycleTimeRow ctr : ctb)
        {
            double xValue = (ctr.getRelativeNumber() - firstRowNumber) / 1000;
            zeroLine.add(xValue, 0);
            hoistPosition.add(xValue, ctr.getHoistPosition());
            trolleyPosition.add(xValue, ctr.getTrolleyPosition());
        }

        addSeries(zeroLine);
        addSeries(hoistPosition);
        addSeries(trolleyPosition);
    }

    public CycleTimeBlock getCTB()
    {
        return ctb;
    }

}
