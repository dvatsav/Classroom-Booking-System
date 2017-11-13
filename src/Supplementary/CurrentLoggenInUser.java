package Supplementary;

/**
 * Created by Anubhav on 14-Nov-17.
 */
public class CurrentLoggenInUser {
	private static String currentUserEmail = null;

	public static String getCurrentUserEmail() {
		return currentUserEmail;
	}

	public static void setCurrentUserEmail(String currentUserEmail) {
		CurrentLoggenInUser.currentUserEmail = currentUserEmail;
	}
}
