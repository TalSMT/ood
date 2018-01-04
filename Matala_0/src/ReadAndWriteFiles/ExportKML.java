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

import DataBase.SetDataBase;
import Filters.Condition;
import Filters.FilterByLocation;
import Filters.FilterByPhoneId;
import Filters.FilterByTime;
import Gui_Designer.Gui_Data;
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
public class ExportKML {
	private String csvPath;

	public static ArrayList <SampleOfWifi> test1;
	FilterByTime timeFilter;
	FilterByLocation locationFilter;
	FilterByPhoneId phoneId;
	/**
	 * Constructor - Receives a folder path and extends the reading and creation functions
	 * @param csvPath
	 */
	public ExportKML(String csvPath) {
		super();
		this.csvPath = csvPath;

		//ArrayList <SampleOfWifi> readFile = ReadCombCsv.readCsvCombwithHeaders(csvPath);
	}

	/**Constructor2 - Receives a folder path and filter parametes extends the reading and creation functions
	 * 
	 * @param csvPath
	 * @param time
	 * @param phoneModel
	 * @param longitude
	 * @param latitude
	 */

	public ExportKML(String csvPath, String timeMIN,String timeMAX,String phoneid, double longitude, double latitude,double altitude,double radios ) {
		super();
		
		this.csvPath = csvPath;
		this.timeFilter= new FilterByTime(timeMIN, timeMAX);
		this.phoneId= new FilterByPhoneId(phoneid);
		this.locationFilter= new FilterByLocation(longitude,latitude,altitude,radios);
		ArrayList <SampleOfWifi> readFile = ReadCombCsv.readCsvCombwithHeaders(csvPath);
		System.out.println("aaaaa"+readFile.get(0).getTime());
		
		if((timeMIN!=null)&&(timeMAX!=null)&&(timeMIN!="")&&(timeMAX!="") )
			readFile=SetDataBase.filter(readFile, this.timeFilter);
		if((phoneid!=null)&&(phoneid!="") )
			readFile=SetDataBase.filter(readFile, this.phoneId);
		if((longitude!=0)&&(latitude!=0) )
			readFile=SetDataBase.filter(readFile, this.locationFilter);
		writeKMLFile(readFile);
	}



	
	
	
	
	/**
	 * writeKMLFile - A function that receives ArrayList of SampleOfWifi and Through the KML library produces KML file with a timeline
	 * @param listfiltered
	 * @see : https://developers.google.com/kml/ - KML library-We used this library because it helped us with the ultimate goal of creating a timeline in Google Maps in the simplest wayֳµ
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
			String s=Gui_Data.OutputfoldePathForKML+Gui_Data.kmlName;
			kml.marshal(new File(s));
			System.out.println(s+" Done");
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
		ExportKML  ch = new ExportKML("C:\\matala\\DataNetWorks.csv");
		//csvToKML  ch = new csvToKML("C:\\matala\\DataNetWorks.csv", "09/11/2017 20:41","09/11/2017 20:45", "", 0, 0, 0, 0);
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
