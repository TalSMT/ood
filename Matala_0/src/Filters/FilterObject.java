package Filters;

import java.io.Serializable;

import javax.swing.JTextField;
/**
 * This class contains the implementation of the interface, where all relevant variables for export to a text file are changed
 * @authors Tal and Shaked 
 *
 */
public class FilterObject implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String latitudeFilter;
	private String longitudeFilter;
	private String radiosFilter;
	private String PhoneIdFilter;
	private String startTime;
    private String endTime;
    
    public FilterObject (String radiosFilter,String latitudeFilter,String longitudeFilter,String PhoneIdFilter,String startTime,String endTime){
    	this.radiosFilter=radiosFilter;
    	this.latitudeFilter=latitudeFilter;
    	this.longitudeFilter=longitudeFilter;
    	this.PhoneIdFilter=PhoneIdFilter;
    	this.startTime=startTime;
    	this.endTime=endTime;
    
    }
    
     

	public String getLongitudeFilter() {
		return longitudeFilter;
	}

	public String getLatitudeFilter() {
		return latitudeFilter;
	}

	public String getRadiosFilter() {
		return radiosFilter;
	}

	public String getPhoneIdFilter() {
		return PhoneIdFilter;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return "FilterObject [latitudeFilter=" + latitudeFilter + ", longitudeFilter=" + longitudeFilter
				+ ", radiosFilter=" + radiosFilter + ", PhoneIdFilter=" + PhoneIdFilter + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
