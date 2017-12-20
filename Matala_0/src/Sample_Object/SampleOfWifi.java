package Sample_Object;

import java.util.ArrayList;

/**
 * 
 * @authors Tal And Shaked
 * @ description- The class contains an object for each point with MAC address and signal strength
 *
 */

public class SampleOfWifi {
	private String time;
	private String phoneId;
	private double lat;
	private double lon;
	private double alt;
	private int wifi_network;
	private ArrayList<WifiPoint> wifiSpotList= new  ArrayList<WifiPoint>();
//matala 2 algo 2
	private double pi=0;
	
	
	//matala 2 algo 2

	public double getPi() {
		return pi;
	}

	public void setPi(double pi) {
		this.pi = pi;
	}
	
	//constructor
	public SampleOfWifi(String time, String phoneId, double lat, double lon, double alt, int wifi_networks) {
		super();
		this.time = time;
		this.phoneId = phoneId;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.wifi_network = wifi_networks;
		
	}

	public void addWifiSpot(String ssid, String mac, int frequncy, int signal) {
		WifiPoint tempWifiSpot = new WifiPoint(ssid, mac, frequncy, signal);
		this.wifiSpotList.add(tempWifiSpot);
	}
	
	public void addWifiSpot(ArrayList<WifiPoint>wifiSpotList) {
	
		this.wifiSpotList=wifiSpotList;
	}
	
	public ArrayList<WifiPoint> getwifiSpotList() {
		return wifiSpotList;
	}

	public String getTime() {
		return time;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public double getAlt() {
		return alt;
	}

	public int getWifi_network() {
		return wifi_network;
	}

	@Override
	public String toString() {
		return "WifiSample [time = " + time + ", phoneId = " + phoneId + ", lat=" + lat + ", lon = " + lon + ", alt = "
				+ alt + ", wifi_network = " + wifi_network + ", wifiSpotList = " + wifiSpotList + "]";
	}
}
