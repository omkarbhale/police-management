package entities;

import db.Database;

import java.util.Date;

public class Criminal {
    private int Criminal_id;
    private String firstname;
    private String middlename;
    private String lastname;
    private Date DOB;
    private String address;
    public Criminal( int _Criminal_id,String _firstname,String _middlename,String _lastname,Date _DOB,String _address){
        Criminal_id=_Criminal_id;
        firstname=_firstname;
        middlename=_middlename;
        lastname=_lastname;
        DOB=_DOB;
        address=_address;
    }
}
