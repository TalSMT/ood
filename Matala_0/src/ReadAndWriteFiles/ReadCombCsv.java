package ReadAndWriteFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Algoritems.Constant;
import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;

public class ReadCombCsv {
//קורא את הקובץ ללא כותרות
	
	public static ArrayList <SampleOfWifi> readCsvComb(){
		String sCurrentLine = "";
		BufferedReader br = null;
		ArrayList <SampleOfWifi> listOfWifi = new ArrayList<SampleOfWifi>();
		ArrayList <WifiPoint> listOfWifiPoint = null;
		try {
			br = new BufferedReader(new FileReader(Constant.getCsvCombPath()));
			System.out.println("try");
			
			while ((sCurrentLine = br.readLine()) != null) {
				int t = 6; //A parameter that passes the position in a row within CSV file from the columns of the values set for each time sample

				
				String[] Lines = sCurrentLine.split(",");
				SampleOfWifi oneObjectWifi;
				WifiPoint oneObject;


					String time = Lines[0];

					if (Lines[0].isEmpty()==false)
					{
						//System.out.println(time+"time");
						String phoneId = Lines[1];
						double lat = Double.parseDouble(Lines[2]);
						double lon = Double.parseDouble(Lines[3]);
						double alt = Double.parseDouble(Lines[4]);
						int wifi_networks = Integer.parseInt(Lines[5]);
						oneObjectWifi = new SampleOfWifi( time,  phoneId,  lat,  lon, alt, wifi_networks);
						listOfWifiPoint= new ArrayList<WifiPoint>();

						while ((t<=42)&&((t-6)/4)< wifi_networks) { // As long as we did not reach the end of the line and until the end of the networks that were absorbed in the same sample time
							//System.out.println("t= "+t+"count"+counter);
							String ssid = Lines[t];
							String mac = Lines[t+1];
							int frequncy = Integer.parseInt(Lines[t+2]);
							double signal = Double.parseDouble(Lines[t+3]);
							//oneWifiPoint = new WifiPoint(ssid,mac, frequncy,signal);
							//tempTenNet.add(oneWifiPoint);
							//	oneObjectWifi.addWifiSpot(ssid, mac, frequncy, signal);
							oneObject= new WifiPoint (ssid, mac, frequncy, signal);
							
							//System.out.println(oneObject.getSsid());

							listOfWifiPoint.add(oneObject);
							
							t=t+4;

							

						}

						oneObjectWifi.addWifiSpot(listOfWifiPoint);
						listOfWifi.add(oneObjectWifi);

					
					
					for (int i = 0; i < listOfWifi.size(); i++) {
						//System.out.println(listOfWifi.get(i).toString());
						for (int j = 0; j < listOfWifi.get(i).getwifiSpotList().size(); j++) {
							//System.out.println( listOfWifi.get(i).getwifiSpotList().get(j).getSsid());

						}
					}
					//System.out.println("khgggggggggggg"+listOfWifi.get(3).getwifiSpotList().get(0).getSsid());

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
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
