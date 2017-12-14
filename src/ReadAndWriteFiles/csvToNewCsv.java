package ReadAndWriteFiles;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.omg.Messaging.SyncScopeHelper;

import Sample_Object.SampleOfWifi;
/**
 * 
 * @author Tal and Shaked
 * @description - This class accepts the path of a folder of CSV files, reads it and creates a  CSV file arranged by sample time
 */
public class csvToNewCsv {
	private String pathOfCsvFolder;
	private File folderOfCsvFiles;
	private static final int INDEX_OF_TIME_IN_RAW_CSV_FILE = 3;
	private static final int INDEX_OF_SSID_IN_RAW_CSV_FILE = 1;
	private static final int INDEX_OF_MAC_IN_RAW_CSV_FILE = 0;
	private static final int INDEX_OF_PHONE_ID_IN_RAW_CSV_FILE = 2;
	private static final int INDEX_OF_SIGNAL_IN_RAW_CSV_FILE = 5;
	private static final int INDEX_OF_ALT_IN_RAW_CSV_FILE = 8;
	private static final int INDEX_OF_LON_IN_RAW_CSV_FILE = 7;
	private static final int INDEX_OF_LAT_IN_RAW_CSV_FILE = 6;
	private static final int INDEX_OF_FREQUNCY_IN_RAW_CSV_FILE = 4;
	
	/**
	 * Constructor -Receives a folder path and extends the reading and creation functions
	 * @param pathOfCsvFolder
	 * @throws Exception
	 */
	public csvToNewCsv(String pathOfCsvFolder) throws Exception {
		this.pathOfCsvFolder = pathOfCsvFolder;
		this.folderOfCsvFiles = new File(this.pathOfCsvFolder);
		if (folderOfCsvFiles.canRead()) {
			ArrayList<SampleOfWifi> processedCsvFile = readCsvFile(folderOfCsvFiles);
			writeCsvFile(processedCsvFile);

		}
	}
	
	/**
	 * readCsvFile- A function that accepts a folder path and returns a list of up to ten strongest networks for each time point
	 * @param folderOfCsvFiles
	 * @return csvSampleList
	 * @throws Exception
	 */
	public static ArrayList<SampleOfWifi> readCsvFile(File folderOfCsvFiles) throws Exception {

		File[] arrayOfCsvFiles = folderOfCsvFiles.listFiles();
		System.out.println(arrayOfCsvFiles[0]);
		ArrayList<SampleOfWifi> csvSampleList = new ArrayList<SampleOfWifi>();
		for (int i = 0; i < arrayOfCsvFiles.length; i++) {
			ArrayList<String[]> csvLines = csvFileToArrayList(arrayOfCsvFiles[i]);
			String phoneId = csvLines.get(0)[INDEX_OF_PHONE_ID_IN_RAW_CSV_FILE];
			csvLines.remove(0);
			csvLines.remove(0);

			while (!csvLines.isEmpty()) {
				ArrayList<String[]> linesToSample = new ArrayList<String[]>();
				linesToSample.add(csvLines.get(0));
				csvLines.remove(0);
				for (int j = 0; j < csvLines.size(); j++) {
					if (linesToSample.get(0)[INDEX_OF_TIME_IN_RAW_CSV_FILE]
							.equals(csvLines.get(j)[INDEX_OF_TIME_IN_RAW_CSV_FILE])) {
						linesToSample.add(csvLines.get(j));
						csvLines.remove(j);
						j--;
					}
				}

				linesToSample.sort((String[] row1, String[] row2) -> {
					int signal1 = Integer.parseInt(row1[INDEX_OF_SIGNAL_IN_RAW_CSV_FILE]);
					int signal2 = Integer.parseInt(row2[INDEX_OF_SIGNAL_IN_RAW_CSV_FILE]);
					if (signal1 < signal2)
						return 1;
					if (signal1 > signal2)
						return -1;
					return 0;
				});

				while (linesToSample.size() > 10) {
					linesToSample.remove(9);
				}

				csvSampleList.add(fixedArrayListToSample(linesToSample, phoneId));

			}
		}
//System.out.println(csvSampleList.get(8));
		return csvSampleList;
	}
	/**
	 * fixedArrayListToSample - The function receives an ArrayList of array of Strings and  PhoneId, and return object of SampleOfWifi
	 * @param linesToSample
	 * @param phoneId
	 * @return tempSample
	 */
	public static SampleOfWifi fixedArrayListToSample(ArrayList<String[]> linesToSample, String phoneId) {
		SampleOfWifi tempSample = new SampleOfWifi(linesToSample.get(0)[INDEX_OF_TIME_IN_RAW_CSV_FILE], phoneId,
				Double.parseDouble(linesToSample.get(0)[INDEX_OF_LAT_IN_RAW_CSV_FILE]),
				Double.parseDouble(linesToSample.get(0)[INDEX_OF_LON_IN_RAW_CSV_FILE]),
				Integer.parseInt(linesToSample.get(0)[INDEX_OF_ALT_IN_RAW_CSV_FILE]), linesToSample.size());

		for (int i = 0; i < linesToSample.size(); i++) {
			tempSample.addWifiSpot(linesToSample.get(i)[INDEX_OF_SSID_IN_RAW_CSV_FILE],
					linesToSample.get(i)[INDEX_OF_MAC_IN_RAW_CSV_FILE],
					Integer.parseInt(linesToSample.get(i)[INDEX_OF_FREQUNCY_IN_RAW_CSV_FILE]),
					Integer.parseInt(linesToSample.get(i)[INDEX_OF_SIGNAL_IN_RAW_CSV_FILE]));
		}
		return tempSample;
	}
	/**
	 * csvFileToArrayList - A function that receives a file ,sends to a function that checks for the correct format, separates it, and returns an ArrayList of Arr of Strings
	 * @param csvFile
	 * @return csvLines
	 * @throws Exception
	 */
	public static ArrayList<String[]> csvFileToArrayList(File csvFile) throws Exception {
		ArrayList<String[]> csvLines = new ArrayList<String[]>();

		try {
			if (isValidFormat(csvFile)) {
				FileReader fr = new FileReader(csvFile);
				BufferedReader br = new BufferedReader(fr);
				String row = br.readLine();
				while (row != null) {
					csvLines.add(row.split(","));
					row = br.readLine();
				}
				br.close();
				fr.close();
			}
		} catch (Exception e) {
			System.out.println("Error in csvFileToArrayList" + e);
		}

		return csvLines;
	}
	/**
	 * isValidFormat-A function that accepts a file and returns a true one if it is in CSV Format
	 * @param file
	 * @return 
	 */
	public static boolean isValidFormat(File file) {
		if (file.getPath().contains(".csv")) {
			return true;
		}

		return false;
	}
	/**
	 * writeCsvFile- A function that receives an ArrayList and generates a new CSV file organized by the 10 strongest networks for each time sample
	 * @param csvSampleList
	 * @throws FileNotFoundException
	 */
	public static void  writeCsvFile(ArrayList<SampleOfWifi> csvSampleList) throws FileNotFoundException { //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
		BufferedWriter bw = null; // https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/?utm_source=mkyong&utm_medium=author&utm_campaign=related-post&utm_content=6
		FileWriter networksCSV = null;
		try {

			String headline = " time, id, lat,  lon,  alt, wifi_network, ssid1, mac1, frequncy1, signal1, ssid2, mac2, frequncy2, signal2, ssid3, mac3, frequncy3, signal3, ssid4, mac4, frequncy4, signal4, ssid5, mac5, frequncy5, signal5, ssid6, mac6, frequncy6, signal6, ssid7, mac7, frequncy7, signal7, ssid8, mac8, frequncy8, signal8, ssid9, mac9, frequncy9, signal9, ssid10, mac10, frequncy10, signal10\n";
			networksCSV = new FileWriter("C:\\matala\\DataNetWorks.csv"); 
			String line, lineTenNetworks="";
			bw = new BufferedWriter(networksCSV);
			bw.write(headline);
			for (int i = 0; i < csvSampleList.size(); i++) {
				line= csvSampleList.get(i).getTime()+","+csvSampleList.get(i).getPhoneId()+","+csvSampleList.get(i).getLat()+","+csvSampleList.get(i).getLon()+","+csvSampleList.get(i).getAlt()+","+csvSampleList.get(i).getWifi_network();
				
				for (int j = 0; j < csvSampleList.get(i).getwifiSpotList().size(); j++) {
					System.out.println(csvSampleList.get(i).getwifiSpotList().size());
					lineTenNetworks=lineTenNetworks+ ","+csvSampleList.get(i).getwifiSpotList().get(j).getSsid()+","+csvSampleList.get(i).getwifiSpotList().get(j).getMac()+","+csvSampleList.get(i).getwifiSpotList().get(j).getFrequncy()+","+csvSampleList.get(i).getwifiSpotList().get(j).getSignal();
					
				}	
				bw.write(line);
				bw.write(lineTenNetworks);
				lineTenNetworks="";
				bw.write("\n");

				}

			
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (networksCSV != null)
					networksCSV.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	/**
	 * getters
	 * @return
	 */
	public File getFolderOfCsvFiles() {
		return folderOfCsvFiles;
	}
	
	
	
	
	/**
	 * main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		csvToNewCsv chek = new csvToNewCsv("C:\\matala\\chek");
		

	}

}
