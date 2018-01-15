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
public class ReadWigleWifiFiles {
	
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
	//public csvToNewCsv(String pathOfCsvFolder) throws Exception {
		
		

		
//	}
	
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
	 * main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//csvToNewCsv chek = new csvToNewCsv("C:\\matala\\INPUT");
		//C:\\matala\\INPUT
		//C:\\matala\\OUTPUT
		//C:\\matala\\OUTPUT\\DataNetWorks.csv


	}

}