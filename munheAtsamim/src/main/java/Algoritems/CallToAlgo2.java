package Algoritems;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Filters.FilterByMac;
import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.WriteMacLocationCSV;
import ReadAndWriteFiles.ExportKML;
import ReadAndWriteFiles.ReadWigleWifiFiles;
import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;
import Sample_Object.macSamlpe;

import java.util.*;

/**
 * 
 * @authors Tal And Shaked
 * This class contains calls to the functions of the first and second algorithms,
 * And exports output files with processed data after using algorithms.
 */

public class CallToAlgo2 {

	//Setting Variables
	ArrayList<SampleOfWifi> samples;
	public static macSamlpe thelocation;
	static ArrayList<macSamlpe> location=new ArrayList<>();
	ArrayList<macSamlpe> input=new ArrayList<>();
	macSamlpe temp;
	ArrayList<SampleOfWifi> outputalgo2;

	
	/**
	 * 
	 * @throws FileNotFoundException
	 * This is the first constructor for the second algorithm.
	 */
	public CallToAlgo2() throws FileNotFoundException {
		System.out.println("----------SecondDatabase-----------");
		samples= ReadCombCsv.readCsvComb(Constant.getCsvCombPath()); // List of A file containing all samples by sample date
		outputalgo2= ReadCombCsv.readCsvComb(Constant.CsvNoGPSPath); // List of A file containing the mac address you want to find the sample location
		
		for (int i = 0; i < outputalgo2.size(); i++) {
			for (int j = 0; j < outputalgo2.get(i).getwifiSpotList().size(); j++) {	 
				temp= new macSamlpe(outputalgo2.get(i).getwifiSpotList().get(j).getSignal(), 0, 0,0) ;
				temp.setMac(outputalgo2.get(i).getwifiSpotList().get(j).getMac());
				input.add(temp);

			}
			thelocation=SamplerLocation.thesamplerLocation(samples, input);
			//System.out.println("i= "+i);	
			//System.out.println("lat "+thelocation.getLat()+" lon "+thelocation.getLon()+" alt "+thelocation.getAlt()+" signal "+thelocation.getSignal());	

			//averg.setMac(samples.get(i).getwifiSpotList().get(j).getMac());
			location.add(thelocation);
			// System.out.println(averg.getSignal());
			//System.out.println("lat "+averg.getLat()+" lon "+averg.getLon()+" alt "+averg.getAlt()+" signal "+averg.getSignal());	 
		}
		System.out.println("ttttiiiimmmmmmeeeeee"+samples.get(5).getTime());
		WriteMacLocationCSV.writeCsvFile( location, Constant.outputPathAlgo2); 
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

	}
	
	/**
	 * read 3 mac and signal and return the location of the sampler
	 * @throws FileNotFoundException
	 * This is the second constructor for the second algorithm.
	 */
	public CallToAlgo2(macSamlpe mac1, macSamlpe mac2, macSamlpe mac3) throws FileNotFoundException {
		System.out.println("----------SecondDatabase-----------");
		samples= ReadCombCsv.readCsvCombwithHeaders(Constant.getCsvCombPath()); // List of A file containing all samples by sample date		
		input.add(mac1);
		input.add(mac2);
		input.add(mac3);
			thelocation=SamplerLocation.thesamplerLocation(samples, input);
			
		

	}
	/**
	 * This is the third constructor for the second algorithm.
	 * read one SampleOfWifi object and return the location of the sampler
	 * @param oneSample
	 * @throws FileNotFoundException
	 */
	public CallToAlgo2(SampleOfWifi oneSample) throws FileNotFoundException {
		
		samples= ReadCombCsv.readCsvCombwithHeaders(Constant.getCsvCombPath());
		for (int j = 0; j < oneSample.getwifiSpotList().size(); j++) {	 
			temp= new macSamlpe(oneSample.getwifiSpotList().get(j).getSignal(), 0, 0,0) ;
			temp.setMac(oneSample.getwifiSpotList().get(j).getMac());
			input.add(temp);

		}
		
			thelocation=SamplerLocation.thesamplerLocation(samples, input);
			
		

	}
	/**
	 * ____________________fuction that convert combCSV line (String) to SampleOfWifi object__________
	 * @param lineString
	 * @return
	 */
	public static SampleOfWifi lineToSampleOfWifi(String lineString)
	{
		
		int t = 6; //A parameter that passes the position in a row within CSV file from the columns of the values set for each time sample
		ArrayList <WifiPoint> listOfWifiPoint = null;

		String[] Lines = lineString.split(",");
		SampleOfWifi oneObjectWifi;
		WifiPoint oneObject;

			String time = Lines[0];
			String phoneId = Lines[1];
			double lat = Double.parseDouble(Lines[2]);
			double lon = Double.parseDouble(Lines[3]);
			double alt = Double.parseDouble(Lines[4]);
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

		return oneObjectWifi;
		
	}
	
	
}

