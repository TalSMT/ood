package Filters;

import Sample_Object.SampleOfWifi;
/**
 * 
 * @authors Tal And Shaked
 * This class filters by radius
 *
 */
public class FilterByLocation implements Condition {
	private double longitudeFilter;
	private double latitudeFilter;
	private double altitudeFilter;
	private double radiosFilter;




	public FilterByLocation(double longitudeFilter, double latitudeFilter, double altitudeFilter,double radiosFilter) {
		super();
		this.longitudeFilter = longitudeFilter;
		this.latitudeFilter = latitudeFilter;
		this.altitudeFilter = altitudeFilter;
		this.radiosFilter = radiosFilter;


	}
	//	d=גˆ�((x1-x2)2+(y1-y2)2)
	public double radioscalculate(double x, double y){
		return (Math.sqrt((Math.pow((x-this.latitudeFilter), 2)+(Math.pow((y-this.longitudeFilter), 2)))));
	}


	@Override
	public boolean test(SampleOfWifi s) {
		if(radioscalculate(s.getLat(), s.getLon())>this.radiosFilter)
			return false;
		return true;
	}


}
