
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import  org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class TestCsvToKml {
	String file, filKML ,time;
	SampleOfWifi sampCheck;
	WifiPoint wifiPointCheck;
	ArrayList<SampleOfWifi> arrListCheck=new ArrayList<SampleOfWifi>();
	csvToKML check, check2;
	@Before
	public void setUp() throws Exception {
		file = "C:\\matala\\DataNetWorks.csv";
		filKML = "C:\\matala\\kmlFile.kml";
		time ="28/10/2017 20:26";
		check =new csvToKML(file);
		check2= new csvToKML("C:\\matala\\DataNetWorks.csv","28/10/2017 20:15","",0,0,0,0);

	}
	@Test
	public void testNotEmptyreadCsvFile(){
		csvToKML.readCsvFile(file);
		assertTrue(!file.isEmpty());
		
	}
	@Test
	public void testreadCsvFile(){
		csvToKML.readCsvFile(file);
		assertTrue(!file.equals(" "));
	}
	
	@Test
	public void testwriteKMLFile(){  
		csvToKML.writeKMLFile(csvToKML.test1);
		assertTrue(csvToKML.test1.get(2).getLon()!=csvToKML.test1.get(2).getLat());
	}
	
	@Test
	public void testNotEmptywriteKMLFile(){
		assertTrue(!filKML.isEmpty());
		
	}
	@Test
	public void testfixtime(){
		assertTrue(csvToKML.fixtime(time).equals("28/10/2017T20:26"));
		
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