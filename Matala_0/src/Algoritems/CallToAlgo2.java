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
	 * This is the second constructor for the second algorithm.
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
	 * read 3 mac and signal and return the location
	 * @throws FileNotFoundException
	 * This is the second constructor for the second algorithm.
	 */
	public CallToAlgo2(macSamlpe mac1, macSamlpe mac2, macSamlpe mac3) throws FileNotFoundException {
		System.out.println("----------SecondDatabase-----------");
		samples= ReadCombCsv.readCsvComb(Constant.getCsvCombPath()); // List of A file containing all samples by sample date		
		input.add(mac1);
		input.add(mac2);
		input.add(mac3);
			thelocation=SamplerLocation.thesamplerLocation(samples, input);
			
		

	}
	
	
	
}

