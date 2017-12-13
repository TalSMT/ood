
public class macSamlpe {
	private double signal;
	private double lat;
	private double lon;
	private double alt;
	//for algo 2
	private double diff;
	private double weight;
	private double piWeight;


	//First constructor for the first algo
	public macSamlpe(double signal, double  lat, double lon, double alt) {
		this.signal=signal;
		this.lat= lat;
		this.lon=lon;
		this.alt=alt;

	}
	/*
	//Second constructor for the second algo
		public macSamlpe(double signal, double  lat, double lon, double alt,double diff,double weight,double piWeight) {
			this.signal=signal;
			this.lat= lat;
			this.lon=lon;
			this.alt=alt;
			this.diff=diff;
			this.weight=weight;
			this.piWeight=piWeight;

		}
	*/
		
			

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

}
