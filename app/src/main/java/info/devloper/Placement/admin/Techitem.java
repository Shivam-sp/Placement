package info.devloper.Placement.admin;

/**
 * Created by Shivam Patel on 14-04-2017.
 */

public class Techitem {
    int Techid;
    String Tname;
    String Ttype;

    public Techitem(int techid, String tname, String ttype) {
        Techid = techid;
        Tname = tname;
        Ttype = ttype;
    }

    public int getTechid() {
        return Techid;
    }

    public void setTechid(int techid) {
        Techid = techid;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTtype() {
        return Ttype;
    }

    public void setTtype(String ttype) {
        Ttype = ttype;
    }
}
