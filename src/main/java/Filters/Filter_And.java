package Filters;

import Sample_Object.SampleOfWifi;

public class Filter_And implements Condition{
	private Condition filter1;
	private Condition filter2;

	public Filter_And(Condition filter1,Condition filter2) {
		this.filter1=filter1;
		this.filter2=filter2;
				
		
	}
	@Override
	public boolean test(SampleOfWifi s) {
		return filter1.test(s)&& filter2.test(s);
		
		
	}
	
}
