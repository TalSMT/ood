package ReadAndWriteFiles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.Style;

import Filters.Condition;
import Filters.FilterByLocation;
import Filters.FilterByPhoneId;
import Filters.FilterByTime;
import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
/**
 * 
 * @authors Tal And Shaked
 * @description - The Class receives CSV File according to the 10 samples with the strongest signal according to the time sample, and creates KML file with filtering option.
 */
public class csvToKML {
	private String csvPath;

	public static ArrayList <SampleOfWifi> test1;
	FilterByTime timeFilter;
	FilterByLocation locationFilter;
	FilterByPhoneId phoneId;
	/**
	 * Constructor - Receives a folder path and extends the reading and creation functions
	 * @param csvPath
	 */
	public csvToKML(String csvPath) {
		super();
		this.csvPath = csvPath;

		ArrayList <SampleOfWifi> readFile = readCsvFile(csvPath);
		writeKMLFile(readFile);
	}

	/**Constructor2 - Receives a folder path and filter parametes extends the reading and creation functions
	 * 
	 * @param csvPath
	 * @param time
	 * @param phoneModel
	 * @param longitude
	 * @param latitude
	 */

	public csvToKML(String csvPath, String timeMIN,String timeMAX,String phoneid, double longitude, double latitude,double altitude,double radios ) {
		super();
		
		this.csvPath = csvPath;
		this.timeFilter= new FilterByTime(timeMIN, timeMAX);
		this.phoneId= new FilterByPhoneId(phoneid);
		this.locationFilter= new FilterByLocation(longitude,latitude,altitude,radios);
		ArrayList <SampleOfWifi> readFile = readCsvFile(csvPath);
		System.out.println("aaaaa"+readFile.get(0).getTime());
		
		if((timeMIN!=null)&&(timeMAX!=null)&&(timeMIN!="")&&(timeMAX!="") )
			readFile=filter(readFile, this.timeFilter);
		if((phoneid!=null)&&(phoneid!="") )
			readFile=filter(readFile, this.phoneId);
		if((longitude!=0)&&(latitude!=0) )
			readFile=filter(readFile, this.locationFilter);
		writeKMLFile(readFile);
	}



	/**
	 * filter - A function that receives ArrayList of SampleOfWifi objects and a value to filter and returns ArrayList of filtered objects according to the desired value
	 * @param list
	 * @param condition
	 * @return
	 */
	public static ArrayList<SampleOfWifi> filter(ArrayList<SampleOfWifi> listAll, Condition condition){
		ArrayList<SampleOfWifi> filtered = new ArrayList<>();
		for (int i = 0; i < listAll.size(); i++) {
			if(condition.test(listAll.get(i)))
				filtered.add(listAll.get(i));
		} 

		return filtered;
	}

	
	/**
	 * readCsvFile - A function that receives a file path of CSV, reads it, Disassembles the file to a list of objects and returns it
	 * @param csvFile
	 * @return
	 * @see https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
	 */
	public static ArrayList <SampleOfWifi> readCsvFile(String csvFile){
		String sCurrentLine = "";
		BufferedReader br = null;
		ArrayList <SampleOfWifi> listOfWifi = new ArrayList<SampleOfWifi>();
		ArrayList <WifiPoint> listOfWifiPoint = null;
		ArrayList <SampleOfWifi> filterList = new ArrayList<SampleOfWifi>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			System.out.println("try");
			int counter = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				int t = 6; //A parameter that passes the position in a row within CSV file from the columns of the values set for each time sample

				counter++;
				String[] Lines = sCurrentLine.split(",");
				SampleOfWifi oneObjectWifi;
				WifiPoint oneObject;


				if (counter > 1 ) {
					String time = Lines[0];
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
						
						listOfWifiPoint.add(oneObject);
						
						t=t+4;

						

					}

					oneObjectWifi.addWifiSpot(listOfWifiPoint);
					listOfWifi.add(oneObjectWifi);

				
				}
				for (int i = 0; i < listOfWifi.size(); i++) {
					//System.out.println(listOfWifi.get(i).toString());
					for (int j = 0; j < listOfWifi.get(i).getwifiSpotList().size(); j++) {
						//System.out.println( listOfWifi.get(i).getwifiSpotList().get(j).getSsid());

					}
				}
				//System.out.println("khgggggggggggg"+listOfWifi.get(3).getwifiSpotList().get(0).getSsid());


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
		
		
		
		
		
		
		test1=listOfWifi;
		return listOfWifi;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * writeKMLFile - A function that receives ArrayList of SampleOfWifi and Through the KML library produces KML file with a timeline
	 * @param listfiltered
	 * @see : https://developers.google.com/kml/ - KML library-We used this library because it helped us with the ultimate goal of creating a timeline in Google Maps in the simplest wayõ
	 */

	public static void  writeKMLFile(ArrayList<SampleOfWifi> listfiltered){

		try {

			final Kml kml = new Kml();
			Document document= kml.createAndSetDocument().withName("MyMarkers").withOpen(true);
			Folder folder = document.createAndAddFolder();
			folder.withName("Wifi newtworks").withOpen(true);
			for (int i = 0; i < listfiltered.size(); i++) {
				double longitude = listfiltered.get(i).getLon();
				double latitude = listfiltered.get(i).getLat();
				String PhoneId = listfiltered.get(i).getPhoneId();
				String time = fixtime(listfiltered.get(i).getTime());
				for (int j = 0; j <listfiltered.get(i).getwifiSpotList().size() ; j++) {
					String ssid = listfiltered.get(i).getwifiSpotList().get(j).getSsid();
					String mac = listfiltered.get(i).getwifiSpotList().get(j).getMac();
					int frequncy = listfiltered.get(i).getwifiSpotList().get(j).getFrequncy();
					double signal = listfiltered.get(i).getwifiSpotList().get(j).getSignal();
					createPlacemarkWithChart(document, folder, longitude, latitude, ssid, 1, time);
				}
			}
			kml.marshal(new File("C:\\matala\\kmlFile.kml"));
			System.out.println("Done");
		} catch (IOException ex) {
		}
	}
	/**
	 * 
	 * @param time
	 * @return Time with "T"  So the format of the date will look like this: "28/10/2017T20:10"
	 */
	public static String fixtime(String time)
	{
		String[]timeToSplit =time.split(" ");
		String start =timeToSplit[0]+"T";
		return start+timeToSplit[1];
	}	
	/**
	 * @see - Sample function given in the library
	 * @param document
	 * @param folder
	 * @param longitude
	 * @param latitude
	 * @param continentName
	 * @param coveredLandmass
	 * @param timestmp
	 */
	public static void createPlacemarkWithChart(Document document, Folder folder, double longitude, double latitude, 
			String continentName, int coveredLandmass,String timestmp) {
		de.micromata.opengis.kml.v_2_2_0.Style style = document.createAndAddStyle();
		style.withId("style_" + continentName) // set the stylename to use this style from the placemark
		.createAndSetIconStyle().withScale(1.0); // set size and icon
		style.createAndSetLabelStyle().withColor("ff43b3ff").withScale(1.0); // set color and size of the continent name

		Placemark placemark = folder.createAndAddPlacemark();
		// use the style for each continent
		placemark.withName(continentName)
		.withStyleUrl("#style_" + continentName)
		// 3D chart imgae
		.withDescription(
				"<![CDATA[BSSID: <b>"+continentName+"</b><br/>Lat: <b>"+latitude+"</b><br/>Lot: <b>"+longitude+"</b><br/>Name: <b>"+continentName+"</b>")
		// coordinates and distance (zoom level) of the viewer
		.createAndSetLookAt().withLongitude(longitude).withLatitude(latitude).withAltitude(0).withRange(12000000);

		placemark.createAndSetPoint().addToCoordinates(longitude, latitude); // set coordinates

		placemark.createAndSetTimeStamp().setWhen(timestmp);
	}







	/**
	 * main
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		//csvToKML chek = new csvToKML("C:\\matala\\DataNetWorks.csv","28/10/2017 20:15","",0,0,0,0);
	//	csvToKML chek = new csvToKML("C:\\matala\\DataNetWorks.csv","","",34.8600917,32.09218707,0,0.005);
		//csvToKML chek = new csvToKML("C:\\matala\\DataNetWorks.csv","","","",0,0,0,0);
		//csvToKML  ch = new csvToKML("C:\\matala\\DataNetWorks.csv");
		csvToKML  ch = new csvToKML("C:\\matala\\DataNetWorks.csv", "09/11/2017 20:41","09/11/2017 20:45", "", 0, 0, 0, 0);
		//	ArrayList <SampleOfWifi> list = new ArrayList();

		/*	Condition<SampleOfWifi> condition1 = new Condition<String>() {
			public boolean test(String s) {
				if (list.get(0).getTime().equals("28/10/2017 20:10")) return true;
				else return false;
			}
		};

		ArrayList<SampleOfWifi> filteredStrings = filter(list, condition1);
	}*/
		ArrayList <SampleOfWifi> filterList = new ArrayList<SampleOfWifi>();
		//	filterList=filter(listOfWifi,  s -> s.getTime().equals("28/10/2017  20:10:00"));

	}

	/**
	 * geters and seters
	 * @return
	 */
	public String getCsvPath() {
		return csvPath;
	}

	public FilterByTime getTimeFilter() {
		return timeFilter;
	}


	public FilterByLocation getLocationFilter() {
		return locationFilter;
	}


	public FilterByPhoneId getPhoneId() {
		return phoneId;
	}





}

