import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
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
	csvToNewCsv check;

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
		check = new csvToNewCsv(fileCsvString);
	}
	@Test
	public void test_isValidFormat() {
		assertTrue(csvToNewCsv.isValidFormat(file));
	}
	@Test
	public void test_readCsvFile() throws Exception{
		if (folderOfCsvFiles.canRead()) {

			assertTrue(csvToNewCsv.readCsvFile(folderOfCsvFiles).get(1).getTime().equals("28/10/2017 20:11"));
		}

	}
	@Test
	public void test_csvFileToArrayList() throws Exception{
		assertTrue(csvToNewCsv.csvFileToArrayList(file).get(4)[5].equals("-79"));
	}

	//we used the method above
/*	@Test
	public void test_fixedArrayListToSample() throws Exception{

		//System.out.println(csvToNewCsv.fixedArrayListToSample(csvToNewCsv.csvFileToArrayList(file), "model=ONEPLUS A3003").getLat());
		assertTrue(csvToNewCsv.fixedArrayListToSample(csvToNewCsv.csvFileToArrayList(file), "model=ONEPLUS A3003").equals("model=ONEPLUS A3003"));
	}*/
	@Test
	public void testprocessedCsvFile(){
		assertTrue(!csvToNewCsv.processedCsvFile.isEmpty());
	}
	@Test
	public void testwriteCsvFile (){
		check.getFolderOfCsvFiles().equals(fileNewCsv);
	}
	
}