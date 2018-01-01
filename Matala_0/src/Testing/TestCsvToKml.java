package Testing;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ReadAndWriteFiles.ExportKML;
import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;

public class TestCsvToKml {
	String file, filKML ,time;
	SampleOfWifi sampCheck;
	WifiPoint wifiPointCheck;
	ArrayList<SampleOfWifi> arrListCheck=new ArrayList<SampleOfWifi>();
	ExportKML check, check2;
	@Before
	public void setUp() throws Exception {
		file = "C:\\matala\\DataNetWorks.csv";
		filKML = "C:\\matala\\kmlFile.kml";
		time ="28/10/2017 20:26";
		check =new ExportKML(file);
		//check2= new csvToKML("C:\\matala\\DataNetWorks.csv","28/10/2017 20:15","",0,0,0,0);

	}
	@Test
	public void testNotEmptyreadCsvFile(){
		//ExportKML.readCsvFile(file);
		assertTrue(!file.isEmpty());
		
	}
	@Test
	public void testreadCsvFile(){
		//ExportKML.readCsvFile(file);
		assertTrue(!file.equals(" "));
	}
	
	@Test
	public void testwriteKMLFile(){  
		ExportKML.writeKMLFile(ExportKML.test1);
		assertTrue(ExportKML.test1.get(2).getLon()!=ExportKML.test1.get(2).getLat());
	}
	
	@Test
	public void testNotEmptywriteKMLFile(){
		assertTrue(!filKML.isEmpty());
		
	}
	@Test
	public void testfixtime(){
		assertTrue(ExportKML.fixtime(time).equals("28/10/2017T20:26"));
		
	}
	@Test
	public void testmeneger(){
		assertTrue(check.getCsvPath().equals(file));
	}
	
	public void testmeneger_constructor2(){
		assertTrue(check2.getCsvPath().equals(file));
		
	}
	
	
	
	
	//@Test
/*	public void testcreatePlacemarkWithChart(){
		csvToKML placemark;
		Document document = null;
		Folder folder = null;
		double longitude=0;
		double latitude=0;		
		String continentName = null,timestmp = null;
		int coveredLandmass = 0;
		csvToKML.createPlacemarkWithChart(document, folder, longitude, latitude,
				continentName, coveredLandmass, timestmp);
		
}*/

}