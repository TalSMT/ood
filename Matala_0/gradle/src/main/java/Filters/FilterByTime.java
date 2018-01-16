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
 * This class filters time. The filter receives minimal time and maximum time and returns all dates between them
 * @authors Tal And Shaked
 *
 */
public class FilterByTime implements Condition {
   private String startTime;
    private String endTime;

	
	public FilterByTime(String startTime, String endTime){
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	

	@Override
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
