package Filters;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import Sample_Object.SampleOfWifi;



import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * This class filters by Time
 * @authors Tal And Shaked
 *
 */
public class FilterByTime implements Condition {
//	private LocalTime timeMIN;
//	private LocalTime timeMAX;
//	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);//לשנות לפורמט שלנו
//	private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.US);//לשנות לפורמט שלנו
    private String startTime;
    private String endTime;

	/**
	 *
	 * @param phoneIdFilter
	 */
//	public FilterByTime(String timeMINstr, String timeMAXstr) {//https://stackoverflow.com/questions/29927362/how-to-convert-date-to-localtime
//		super();
//		System.out.println(timeMINstr);
//        LocalDateTime localDateTimeMIN = LocalDateTime.parse(timeMINstr, formatter);
//        this.timeMIN= localDateTimeMIN.toLocalTime();
//        
//        LocalDateTime localDateTimeMAX = LocalDateTime.parse(timeMAXstr, formatter);
//        this.timeMAX= localDateTimeMAX.toLocalTime();

//	}

	public FilterByTime(String startTime, String endTime){
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	

	@Override
//	public boolean test(SampleOfWifi s) {
//		  LocalDateTime localDateTimeS = LocalDateTime.parse(s.getTime(), formatter);
//		  LocalTime stime= localDateTimeS.toLocalTime();
//		  
//		return (stime.isAfter(this.timeMIN)&&stime.isBefore(this.timeMAX));
//	}

	public boolean test(SampleOfWifi s) {
		String time= s.getTime();
		int sTime=startTime.compareTo(time);
		int eTime=endTime.compareTo(time);
		boolean ans=false;
		if(s!=null){
			if(sTime<=0 && eTime>0){
				ans =true;
			}
			
		}
		return ans;
	}
	
	
	
}
