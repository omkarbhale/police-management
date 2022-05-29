package entities;

import java.sql.Date;

public class Police {
    public int Police_id;
    public String firstName;
    public String middleName;
    public String lastName;
    public String post;
    public Date DOB;
    public String contactNo;
    public String address;

    public Police(int _Police_id,String _firstname,String _middlename,String _lastname,String _post,Date _DOB,String _contactNo,String _address) {
            Police_id= _Police_id;
            firstName=_firstname;
            middleName=_middlename;
            lastName=_lastname;
            post=_post;
            DOB=_DOB;
            contactNo=_contactNo;
            address=_address;

    }
}
