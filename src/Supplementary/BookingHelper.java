package Supplementary;

/**
 * Created by Anubhav on 15-Nov-17.
 */
public class BookingHelper {
	private String day;
	private String roomNumber;
	private String startTime;
	private String endTime;
	private String purpose;
	private String requestedBy;

	public String getDay() {
		return day;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public BookingHelper(String day, String roomNumber, String startTime, String endTime, String purpose, String requestedBy) {
		this.day = day;
		this.roomNumber = roomNumber;
		this.startTime = startTime;

		this.endTime = endTime;
		this.purpose = purpose;
		this.requestedBy = requestedBy;
	}

	@Override
	public String toString() {
		return "BookingHelper{" +
				"day='" + day + '\'' +
				", roomNumber='" + roomNumber + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", purpose='" + purpose + '\'' +
				", requestedBy='" + requestedBy + '\'' +
				'}';
	}
//	temp.put("Room Number", (String)class_number.getValue());
//		temp.put("Start Time", startTime);
//		temp.put("End Time", endTime);
//		temp.put("Purpose", purpose.getText());
//		temp.put("Requested by", entryPageController.userEmail);
}
