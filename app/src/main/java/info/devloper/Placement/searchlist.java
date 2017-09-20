package info.devloper.Placement;

/**
 * Created by Shivam Patel on 10-04-2017.
 */

public class searchlist
{
    String canid;
    String canname;
    String canlast;
    String cangen;
    String candob;
    String cancity;

    public searchlist(String canid, String canname, String canlast, String cangen, String candob, String cancity) {
        this.canid = canid;
        this.canname = canname;
        this.canlast = canlast;
        this.cangen = cangen;
        this.candob = candob;
        this.cancity = cancity;
    }

    public String getCanid() {
        return canid;
    }

    public void setCanid(String canid) {
        this.canid = canid;
    }

    public String getCanname() {
        return canname;
    }

    public void setCanname(String canname) {
        this.canname = canname;
    }

    public String getCanlast() {
        return canlast;
    }

    public void setCanlast(String canlast) {
        this.canlast = canlast;
    }

    public String getCangen() {
        return cangen;
    }

    public void setCangen(String cangen) {
        this.cangen = cangen;
    }

    public String getCandob() {
        return candob;
    }

    public void setCandob(String candob) {
        this.candob = candob;
    }

    public String getCancity() {
        return cancity;
    }

    public void setCancity(String cancity) {
        this.cancity = cancity;
    }
}
