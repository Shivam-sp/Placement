package info.devloper.Placement.admin;

/**
 * Created by Shivam Patel on 30-03-2017.
 */

public class Griditem
{
    String firstname;
    String middlename;
    String candidateid;

    public Griditem() {
    }

    public Griditem(String candidateid, String firstname, String middlename) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.candidateid = candidateid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(String candidateid) {
        this.candidateid = candidateid;
    }
}
