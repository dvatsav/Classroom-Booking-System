package Actors;
import Utils.Course;
import java.util.ArrayList;

public class Faculty extends Users{
    private ArrayList<Course> coursesTaught;
    //can also check whether a course can be selected by viewing a faculty profile and selecting course
    @Override
    public String getEmail() {
        return email;
    }
}
