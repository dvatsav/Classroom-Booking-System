package Actors;
import Utils.Course;
import java.util.ArrayList;

public class Faculty extends Users{
    private ArrayList<Course> coursesTaught;

    @Override
    public String getEmail() {
        return email;
    }
}
