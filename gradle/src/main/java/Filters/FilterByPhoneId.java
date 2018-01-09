package Filters;

import Sample_Object.SampleOfWifi;
/**
 * 
 * @authors Tal And Shaked
 * This class filters by Phone Id
 *
 */
public class FilterByPhoneId implements Condition {
	private String PhoneIdFilter;

	/**
	 *
	 * @param phoneIdFilter
	 */
	public FilterByPhoneId(String phoneIdFilter) {
		super();
		PhoneIdFilter = phoneIdFilter;
	}


	@Override
	public boolean test(SampleOfWifi s) {
		return s.getPhoneId().equals(this.PhoneIdFilter);
	}

}
