package matala_0;

/**
 * 
 */

import java.util.Date;

public class NetWorks {
	String time;
	String id;
	double lat;
	double lon;
	int alt;
	String wifi_network; 
	String ssid;
	String mac;
	double frequncy;
	int signal;
	
	//bona
	public NetWorks (String id){
		this.id=id;
	}
		public NetWorks(String time, /*String id, */double lat, double lon, int alt,
				String wifi_networks, String ssid, String mac, double frequncy,
				int signal) {
			super();
			this.time = time;
		//	this.id = id;
			this.lat = lat;
			this.lon = lon;
			this.alt = alt;
			this.wifi_network = wifi_networks;
			this.ssid = ssid;
			this.mac = mac;
			this.frequncy = frequncy;
			this.signal = signal;
		}
		

	
	
public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public int getAlt() {
		return alt;
	}


	public void setAlt(int alt) {
		this.alt = alt;
	}


	public String getWifi_network() {
		return wifi_network;
	}


	public void setWifi_network(String wifi_networks) {
		this.wifi_network = wifi_networks;
	}


	public String getSsid() {
		return ssid;
	}


	public void setSsid(String ssid) {
		this.ssid = ssid;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public double getFrequncy() {
		return frequncy;
	}


	public void setFrequncy(double frequncy) {
		this.frequncy = frequncy;
	}


	public int getSignal() {
		return signal;
	}


	public void setSignal(int signal) {
		this.signal = signal;
	}


	
	
	@Override
	public String toString() {
		return "NetWorks [time=" + time + ", id=" + id + ", lat=" + lat
				+ ", lon=" + lon + ", alt=" + alt + ", wifi_networks="
				+ wifi_network + ", ssid=" + ssid + ", mac=" + mac
				+ ", frequncy=" + frequncy + ", signal=" + signal + "]";
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
