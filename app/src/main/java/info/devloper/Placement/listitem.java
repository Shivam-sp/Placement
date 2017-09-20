package info.devloper.Placement;

/**
 * Created by Shivam Patel on 09-04-2017.
 */

public class listitem
{
    String Degree;
    String Collage;
    String Year;
    String Grade;

    public listitem() {
    }

    public listitem(String degree, String collage, String year, String grade) {
        Degree = degree;
        Collage = collage;
        Year = year;
        Grade = grade;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getCollage() {
        return Collage;
    }

    public void setCollage(String collage) {
        Collage = collage;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
