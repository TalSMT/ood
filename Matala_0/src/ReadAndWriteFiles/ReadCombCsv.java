package ReadAndWriteFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Algoritems.Constant;
import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;
/**
 * 
 * @authors Tal And Shaked
 * This class contains a function that reads a CSV file without headings - for task 2 test files
 *
 */
public class ReadCombCsv {
	/**
	 * 
	 * @param path
	 * @return ArrayList of SampleOfWifi
	 */

	public static ArrayList <SampleOfWifi> readCsvComb(String path){
		String sCurrentLine = "";
		BufferedReader br = null;
		ArrayList <SampleOfWifi> listOfWifi = new ArrayList<SampleOfWifi>();
		ArrayList <WifiPoint> listOfWifiPoint = null;
		try {
			br = new BufferedReader(new FileReader(path));
			System.out.println("try");

			while ((sCurrentLine = br.readLine()) != null) {
				int t = 6; //A parameter that passes the position in a row within CSV file from the columns of the values set for each time sample		
				String[] Lines = sCurrentLine.split(",");
				SampleOfWifi oneObjectWifi;
				WifiPoint oneObject;
				String time = Lines[0];

				if (Lines[0].isEmpty()==false)
				{
					String phoneId = Lines[1];
					double lat = 0;
					double lon = 0;
					double alt = 0;
					if (!Lines[2].equals("?"))
					{
						lat = Double.parseDouble(Lines[2]);
						lon = Double.parseDouble(Lines[3]);
						alt = Double.parseDouble(Lines[4]);
					}

					int wifi_networks = Integer.parseInt(Lines[5]);
					oneObjectWifi = new SampleOfWifi( time,  phoneId,  lat,  lon, alt, wifi_networks);
					listOfWifiPoint= new ArrayList<WifiPoint>();

					while ((t<=42)&&((t-6)/4)< wifi_networks) { // As long as we did not reach the end of the line and until the end of the networks that were absorbed in the same sample time
						String ssid = Lines[t];
						String mac = Lines[t+1];
						int frequncy = Integer.parseInt(Lines[t+2]);
						double signal = Double.parseDouble(Lines[t+3]);
						oneObject= new WifiPoint (ssid, mac, frequncy, signal);
						listOfWifiPoint.add(oneObject);
						t=t+4;
					}

					oneObjectWifi.addWifiSpot(listOfWifiPoint);
					listOfWifi.add(oneObjectWifi);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return listOfWifi;
	}

}
