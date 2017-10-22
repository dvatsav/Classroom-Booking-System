package Utils;

public class Validations {
    public boolean validateEmail(String emailID) {
        if (emailID.length() > 12) {
            return "@iiitd.ac.in".equals(emailID.substring(emailID.length() - 12));
        }
        return false;
    }
}
