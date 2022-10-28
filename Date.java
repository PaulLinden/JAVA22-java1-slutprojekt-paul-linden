package date;

import org.joda.time.LocalDate;

public class Date  {
	
	public static LocalDate day = new LocalDate();
	public static LocalDate monday = day.withDayOfWeek(org.joda.time.DateTimeConstants.MONDAY);
	public static LocalDate tuesday = day.withDayOfWeek(org.joda.time.DateTimeConstants.TUESDAY);
	public static LocalDate wednesday = day.withDayOfWeek(org.joda.time.DateTimeConstants.WEDNESDAY);
	public static LocalDate thursday = day.withDayOfWeek(org.joda.time.DateTimeConstants.THURSDAY);
	public static LocalDate friday = day.withDayOfWeek(org.joda.time.DateTimeConstants.FRIDAY);
	public static LocalDate saturday = day.withDayOfWeek(org.joda.time.DateTimeConstants.SATURDAY);
	public static LocalDate sunday = day.withDayOfWeek(org.joda.time.DateTimeConstants.SUNDAY);
	
}	
	


