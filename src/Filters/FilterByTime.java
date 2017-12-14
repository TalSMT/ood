package Filters;

import Sample_Object.SampleOfWifi;

public class FilterByTime implements Condition {
	private String timeFilter;
	
	/**
	 *
	 * @param phoneIdFilter
	 */
	public FilterByTime(String timeFilter) {
		super();
		this.timeFilter = timeFilter;
	}


	@Override
	public boolean test(SampleOfWifi s) {
		// TODO Auto-generated method stub
		return s.getTime().equals(this.timeFilter);
	}

}
