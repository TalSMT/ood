
public class FilterByPhoneId implements Condition {
	private String fhoneIdFilter;
	
	/**
	 *
	 * @param phoneIdFilter
	 */
	public FilterByPhoneId(String phoneIdFilter) {
		super();
		fhoneIdFilter = phoneIdFilter;
	}


	@Override
	public boolean test(SampleOfWifi s) {
		// TODO Auto-generated method stub
		return s.getPhoneId().equals(this.fhoneIdFilter);
	}

}
