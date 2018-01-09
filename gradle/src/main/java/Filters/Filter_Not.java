package Filters;

import Sample_Object.SampleOfWifi;


	public class Filter_Not implements Condition {
		private Condition filter;
		
		public Filter_Not(Condition filter) {
			this.filter=filter;	
			
		}

		@Override
		public boolean test(SampleOfWifi s) {
			return !filter.test(s);
			
			
		}

	}
