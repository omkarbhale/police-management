package entities;

import java.sql.Date;

public class FIR {

    private int FIR_id;
    private int Police_id;
    private int Criminal_id;
    private Date date;
    private String Location;
    private int Severity;

    public FIR(int _FIR_id,int _Police_id,int _Criminal_id,Date _date,String _Location,int _Severity){
        FIR_id=_FIR_id;
        Police_id=_Police_id;
        Criminal_id=_Criminal_id;
        date=_date;
        Location=_Location;
        Severity=_Severity;
    }
}
