package Supplementary;

/**
 * Created by Anubhav on 14-Nov-17.
 */
public class CurrentLoggenInUser {
	private static String currentUserEmail = null;
	private static String currentUserType = null;

	public static String getCurrentUserType() {
		return currentUserType;
	}

	public static void setCurrentUserType(String currentUserType) {
		CurrentLoggenInUser.currentUserType = currentUserType;
	}

	public static String getCurrentUserEmail() {
		return currentUserEmail;
	}

	public static void setCurrentUserEmail(String currentUserEmail) {
		CurrentLoggenInUser.currentUserEmail = currentUserEmail;
	}
}
