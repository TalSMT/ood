package Filters;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import Sample_Object.SampleOfWifi;



import java.util.Date;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * 
 * @authors Tal And Shaked
 * This class filters by Time
 *
 */
public class FilterByTime implements Condition {
	private LocalTime timeMIN;
	private LocalTime timeMAX;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);//ìùðåú ìôåøîè ùìðå
	private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.US);//ìùðåú ìôåøîè ùìðå


	/**
	 *
	 * @param phoneIdFilter
	 */
	public FilterByTime(String timeMINstr, String timeMAXstr) {//https://stackoverflow.com/questions/29927362/how-to-convert-date-to-localtime
		super();
		long hthala = Date.parse(timeMINstr);// option 1 - not working
		long sof = Date.parse(timeMAXstr);
		
		System.out.println(timeMINstr);
        LocalDateTime localDateTimeMIN = LocalDateTime.parse(timeMINstr, formatter); // option 2 not working as well
        this.timeMIN= localDateTimeMIN.toLocalTime();
        
        LocalDateTime localDateTimeMAX = LocalDateTime.parse(timeMAXstr, formatter);
        this.timeMAX= localDateTimeMAX.toLocalTime();

	}

	
	
	

	@Override
	public boolean test(SampleOfWifi s) {
		  LocalDateTime localDateTimeS = LocalDateTime.parse(s.getTime(), formatter);
		  LocalTime stime= localDateTimeS.toLocalTime();
		  
		return (stime.isAfter(this.timeMIN)&&stime.isBefore(this.timeMAX));
	}

	
	//
	public static void main(String[] args) throws FileNotFoundException {
		
		FilterByTime filterTIME=new FilterByTime("28/10/2017 20:10:00","28/10/2017 20:30:00");
		long d = Date.parse("28/10/2017 20:10:00");

	}
	
}