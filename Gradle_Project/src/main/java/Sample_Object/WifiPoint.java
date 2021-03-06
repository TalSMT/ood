package Sample_Object;

/**
 * @authors Tal And Shaked
 *@ description - class that Contains an object for each of the ten samples with the strongest signal
 */
public class WifiPoint {
	private String ssid;
	private String mac;
	private int frequncy;
	private double signal;

	public WifiPoint(String ssid, String mac, int frequncy, double signal) {
		this.ssid = ssid;
		this.mac = mac;
		this.frequncy = frequncy;
		this.signal = signal;

	}

	public String getSsid() {
		return ssid;
	}

	public String getMac() {
		return mac;
	}

	public int getFrequncy() {
		return frequncy;
	}

	public double getSignal() {
		return signal;
	}

}
