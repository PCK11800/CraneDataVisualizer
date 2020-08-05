package datagraphing;

import dataextraction.datacomponents.CycleTimeBlock;
import dataextraction.datacomponents.CycleTimeRow;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DiscreteDataset extends XYSeriesCollection {

    private CycleTimeBlock ctb = null;

    public DiscreteDataset(CycleTimeBlock ctb)
    {
        this.ctb = ctb;
        createDataset();
    }

    XYSeries rcsActive = new XYSeries("RCS Active");
    XYSeries miActive = new XYSeries("MI Active");
    XYSeries spreaderLock = new XYSeries("Spreader Lock");
    XYSeries flsScan = new XYSeries("FLS Scan");
    XYSeries tpsPositioning = new XYSeries("TPS Positioning");
    XYSeries tpsScan = new XYSeries("TPS Scan");
    XYSeries ATIDSActive = new XYSeries("ATIDS Active");
    XYSeries gaMotion = new XYSeries("GA Motion");
    XYSeries hoistMotion = new XYSeries("Hoist Motion");
    XYSeries trolleyMotion = new XYSeries("Trolley Motion");
    XYSeries rcosIntervention = new XYSeries("RCOS Intervention");

    private void createDataset()
    {
        double firstRowNumber = ctb.get(0).getRelativeNumber();
        for(CycleTimeRow ctr : ctb)
        {
            double xValue = (ctr.getRelativeNumber() - firstRowNumber) / 1000;
            if(ctr.getRCOSIntervention() != 0){
                rcosIntervention.add(xValue, 1);
            }
            if(ctr.getMIActive() != 0){
                miActive.add(xValue, 2);
            }
            if(ctr.getSpreaderLock() != 0){
                spreaderLock.add(xValue, 3);
            }
            if(ctr.getFLSActivate() != 0){
                flsScan.add(xValue, 4);
            }
            if(ctr.getTPSPositioning() != 0){
                tpsPositioning.add(xValue, 5);
            }
            if(ctr.getTPSColdRestart() != 0){
                tpsScan.add(xValue, 6);
            }
            if(ctr.getATIDSState() != 0){
                ATIDSActive.add(xValue, 7);
            }
            if(ctr.getGantrySpeed() != 0){
                gaMotion.add(xValue, 8);
            }
            if(ctr.getHoistSpeed() != 0){
                hoistMotion.add(xValue, 9);
            }
            if(ctr.getTrolleySpeed() != 0){
                trolleyMotion.add(xValue, 10);
            }
        }

        addSeries(rcosIntervention);
        addSeries(miActive);
        addSeries(spreaderLock);
        addSeries(flsScan);
        addSeries(tpsPositioning);
        addSeries(tpsScan);
        addSeries(ATIDSActive);
        addSeries(gaMotion);
        addSeries(hoistMotion);
        addSeries(trolleyMotion);
    }

    public CycleTimeBlock getCTB() { return ctb; }

}
