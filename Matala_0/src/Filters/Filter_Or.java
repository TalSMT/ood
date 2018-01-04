package Filters;

import Sample_Object.SampleOfWifi;

public class Filter_Or implements Condition {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Condition filter1;
	private Condition filter2;
	
	public Filter_Or(Condition filter1,Condition filter2) {
		this.filter1=filter1;
		this.filter2=filter2;	
		
	}

	@Override
	public boolean test(SampleOfWifi s) {
		return filter1.test(s)|| filter2.test(s);
		
		
	}

}
