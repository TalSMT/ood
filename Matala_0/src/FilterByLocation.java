
public class FilterByLocation implements Condition {
	private double longitudeFilter;
	private double latitudeFilter;
	
	
	
	public FilterByLocation(double longitudeFilter, double latitudeFilter) {
		super();
		this.longitudeFilter = longitudeFilter;
		this.latitudeFilter = latitudeFilter;
	}


	@Override
	public boolean test(SampleOfWifi s) {
		// TODO Auto-generated method stub
		return ((s.getLon()==this.longitudeFilter)&&(s.getLat()==this.latitudeFilter));
	}
	

}
