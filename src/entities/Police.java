package entities;

import java.sql.Date;

public class Police {
    private int Police_id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String post;
    private Date DOB;
    private String contactNo;
    private String address;

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
