package date;

import org.joda.time.LocalDate;

public class Date  {
	
	public static LocalDate todaysDate = new LocalDate();
	public static LocalDate mondayDate = todaysDate.withDayOfWeek(org.joda.time.DateTimeConstants.MONDAY);
	public static LocalDate tuesdayDate = todaysDate.withDayOfWeek(org.joda.time.DateTimeConstants.TUESDAY);
	public static LocalDate wednesdayDate = todaysDate.withDayOfWeek(org.joda.time.DateTimeConstants.WEDNESDAY);
	public static LocalDate thursdayDate = todaysDate.withDayOfWeek(org.joda.time.DateTimeConstants.THURSDAY);
	public static LocalDate fridayDate = todaysDate.withDayOfWeek(org.joda.time.DateTimeConstants.FRIDAY);
	public static LocalDate saturdayDate = todaysDate.withDayOfWeek(org.joda.time.DateTimeConstants.SATURDAY);
	public static LocalDate sundayDate = todaysDate.withDayOfWeek(org.joda.time.DateTimeConstants.SUNDAY);
	
}	
	


