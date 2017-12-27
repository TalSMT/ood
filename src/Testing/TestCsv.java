package Testing;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ReadAndWriteFiles.ReadWigleWifiFiles;
import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;
/**
 * 
 * @authors Tal and Shaked
 * @description - this class checks the propriety of existing functions in the class csvToNewcsv
 */
public class TestCsv {
	File file;
	File folderOfCsvFiles;
	String fileCsvString , fileNewCsv="";
	SampleOfWifi sampCheck;
	WifiPoint wifiPointCheck;
	ArrayList<SampleOfWifi> arrListCheck=new ArrayList<SampleOfWifi>();
	String[] arr = new String[2];
	ArrayList<String[]> csvLines = new ArrayList<String[]>();
	private ArrayList<SampleOfWifi> listfiltered;
	ReadWigleWifiFiles check;

	@Before
	public void setUp() throws Exception {
		file= new File ("C:\\matala\\chek\\WigleWifi_20171028203300.csv");
		folderOfCsvFiles = new File("C:\\matala\\chek");
		wifiPointCheck = new WifiPoint("a", "b", 1, 2);
		sampCheck = new SampleOfWifi("28/10/2017 20:10", "model=ONEPLUS A3003", 32.0909486, 34.8786141, 0, 9);
		arrListCheck.add(sampCheck);
		arr[0]="shaked&tal";
		arr[1]="matala_1";
		csvLines.add(arr);
		fileCsvString = "C:\\matala\\chek";
		fileNewCsv = "C:\\matala\\DataNetWorks.csv";
		check = new ReadWigleWifiFiles(fileCsvString);
	}
	@Test
	public void test_isValidFormat() {
		assertTrue(ReadWigleWifiFiles.isValidFormat(file));
	}
	@Test
	public void test_readCsvFile() throws Exception{
		if (folderOfCsvFiles.canRead()) {

			assertTrue(ReadWigleWifiFiles.readCsvFile(folderOfCsvFiles).get(1).getTime().equals("28/10/2017 20:11"));
		}

	}
	@Test
	public void test_csvFileToArrayList() throws Exception{
		assertTrue(ReadWigleWifiFiles.csvFileToArrayList(file).get(4)[5].equals("-79"));
	}
	/*@Test
	public void testwriteCsvFile(){  
		csvToNewCsv.writeCsvFile(csvToNewCsv.);
		assertTrue(csvToKML.test1.get(2).getLon()!=csvToKML.test1.get(2).getLat());
	}*/
	

}