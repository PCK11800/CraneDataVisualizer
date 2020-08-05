package dataextraction.datacomponents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CycleTimeRow {

    private double relativeNumber;
    private String Date;
    private String Time;
    private double CraneMasterStatus;
    private double MIActive;
    private double RCOSIntervention;
    private double RCSControl;
    private double JobActive;
    private double SpreaderLock, SpreaderUnlock;
    private double TrolleyPosition, TrolleySpeed;
    private double HoistPosition, HoistSpeed;
    private double GantryPosition, GantrySpeed;
    private double FLSActivate, LCPSActivate, SPMSActivate, TPSColdRestart, TPSPositioning, CTDSActivate;
    private double ATIDSState;

    /*
     * Getters and setters
     */

    public double getRelativeNumber() {
        return relativeNumber;
    }

    public void setRelativeNumber(double relativeNumber) {
        this.relativeNumber = relativeNumber;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public double getCraneMasterStatus() {
        return CraneMasterStatus;
    }

    public void setCraneMasterStatus(double craneMasterStatus) {
        CraneMasterStatus = craneMasterStatus;
    }

    public double getMIActive() {
        return MIActive;
    }

    public void setMIActive(double MIActive) {
        this.MIActive = MIActive;
    }

    public double getRCOSIntervention() {
        return RCOSIntervention;
    }

    public void setRCOSIntervention(double RCOSIntervention) {
        this.RCOSIntervention = RCOSIntervention;
    }

    public double getRCSControl() {
        return RCSControl;
    }

    public void setRCSControl(double RCSControl) {
        this.RCSControl = RCSControl;
    }

    public double getJobActive() {
        return JobActive;
    }

    public void setJobActive(double jobActive) {
        JobActive = jobActive;
    }

    public double getSpreaderLock() {
        return SpreaderLock;
    }

    public void setSpreaderLock(double spreaderLock) {
        SpreaderLock = spreaderLock;
    }

    public double getSpreaderUnlock() {
        return SpreaderUnlock;
    }

    public void setSpreaderUnlock(double spreaderUnlock) {
        SpreaderUnlock = spreaderUnlock;
    }

    public double getTrolleyPosition() {
        return TrolleyPosition;
    }

    public void setTrolleyPosition(double trolleyPosition) {
        TrolleyPosition = trolleyPosition;
    }

    public double getTrolleySpeed() {
        return TrolleySpeed;
    }

    public void setTrolleySpeed(double trolleySpeed) {
        TrolleySpeed = trolleySpeed;
    }

    public double getHoistPosition() {
        return HoistPosition;
    }

    public void setHoistPosition(double hoistPosition) {
        HoistPosition = hoistPosition;
    }

    public double getHoistSpeed() {
        return HoistSpeed;
    }

    public void setHoistSpeed(double hoistSpeed) {
        HoistSpeed = hoistSpeed;
    }

    public double getGantryPosition() {
        return GantryPosition;
    }

    public void setGantryPosition(double gantryPosition) {
        GantryPosition = gantryPosition;
    }

    public double getGantrySpeed() {
        return GantrySpeed;
    }

    public void setGantrySpeed(double gantrySpeed) {
        GantrySpeed = gantrySpeed;
    }

    public double getFLSActivate() {
        return FLSActivate;
    }

    public void setFLSActivate(double FLSActivate) {
        this.FLSActivate = FLSActivate;
    }

    public double getLCPSActivate() {
        return LCPSActivate;
    }

    public void setLCPSActivate(double LCPSActivate) {
        this.LCPSActivate = LCPSActivate;
    }

    public double getSPMSActivate() {
        return SPMSActivate;
    }

    public void setSPMSActivate(double SPMSActivate) {
        this.SPMSActivate = SPMSActivate;
    }

    public double getTPSColdRestart() {
        return TPSColdRestart;
    }

    public void setTPSColdRestart(double TPSColdRestart) {
        this.TPSColdRestart = TPSColdRestart;
    }

    public double getTPSPositioning() {
        return TPSPositioning;
    }

    public void setTPSPositioning(double TPSPositioning) {
        this.TPSPositioning = TPSPositioning;
    }

    public double getCTDSActivate() {
        return CTDSActivate;
    }

    public void setCTDSActivate(double CTDSActivate) {
        this.CTDSActivate = CTDSActivate;
    }

    public double getATIDSState() {
        return ATIDSState;
    }

    public void setATIDSState(double ATIDSState) {
        this.ATIDSState = ATIDSState;
    }
}
