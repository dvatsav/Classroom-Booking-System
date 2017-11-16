package Supplementary;

/**
 * Created by Anubhav on 14-Nov-17.
 */
public class CurrentLoggenInUser {
	private static String currentUserEmail = null;
	private static String currentUserType = null;
	private static String adminMailPassword = null;
	public static final String adminMailID = "scrypting101@gmail.com";

	public static String getCurrentUserType() {
		return currentUserType;
	}

	public static void setCurrentUserType(String currentUserType) {
		CurrentLoggenInUser.currentUserType = currentUserType;
	}

	public static void setadminMailPassword(String password) {
		adminMailPassword = password;
	}

	public static String getadminMailPassword(){
		return adminMailPassword;
	}

	public static void setNull() {
		adminMailPassword = null;
	}

	public static String getCurrentUserEmail() {
		return currentUserEmail;
	}

	public static void setCurrentUserEmail(String currentUserEmail) {
		CurrentLoggenInUser.currentUserEmail = currentUserEmail;
	}
}
