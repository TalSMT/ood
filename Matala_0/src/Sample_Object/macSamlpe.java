package Sample_Object;
/**
 * 
 * @authors Tal And Shaked
 * This class contains objects for sampling by MAC address
 *
 */
public class macSamlpe {
	/***
	 * setting
	 */
	private double signal;
	private double lat;
	private double lon;
	private double alt;
	private String mac;
	//for algo 2
	private double diff;
	private double weight;
	private double piWeight;
	private double Average_diff;

	/**
	 * 
	 * @param signal
	 * @param lat
	 * @param lon
	 * @param alt
	 * First constructor for the first algo
	 */
	public macSamlpe(double signal, double  lat, double lon, double alt) {
		this.signal=signal;
		this.lat= lat;
		this.lon=lon;
		this.alt=alt;

	}			
/**
 * for A function that removes duplicate values using HashSet
 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		macSamlpe other = (macSamlpe) obj;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		return true;
	}
/**
 * 
 * @return Getters And Setters
 */
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public double getSignal() {
		return signal;
	}

	public double getDiff() {
		return diff;
	}



	public void setDiff(double diff) {
		this.diff = diff;
	}



	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}



	public double getPiWeight() {
		return piWeight;
	}



	public void setPiWeight(double piWeight) {
		this.piWeight = piWeight;
	}



	public void setSignal(int signal) {
		this.signal = signal;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
		this.alt = alt;
	}
	public double getAverage_diff() {
		return Average_diff;
	}
	public void setAverage_diff(double average_diff) {
		Average_diff = average_diff;
	}

}