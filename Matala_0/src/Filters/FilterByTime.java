package Filters;

import Sample_Object.SampleOfWifi;
/**
 * 
 * @authors Tal And Shaked
 * This class filters by Time
 *
 */
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
		return s.getTime().equals(this.timeFilter);
	}

}
