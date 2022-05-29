package entities;

import java.util.Date;

public class FIR {

    public int FIR_id;
    public int Police_id;
    public int Criminal_id;
    public Date date;
    public String Location;
    public int Severity;
    public String crime;

    public FIR(int _FIR_id,int _Police_id,int _Criminal_id,Date _date,String _Location,int _Severity, String crime){
        FIR_id=_FIR_id;
        Police_id=_Police_id;
        Criminal_id=_Criminal_id;
        date=_date;
        Location=_Location;
        Severity=_Severity;
        this.crime = crime;
    }
}
