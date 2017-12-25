package Algoritems;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Filters.FilterByMac;
import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.WriteMacLocationCSV;
import ReadAndWriteFiles.csvToKML;
import ReadAndWriteFiles.csvToNewCsv;
import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

import java.util.*;

/**
 * 
 * @authors Tal And Shaked
 * This class contains calls to the functions of the first and second algorithms,
 * And exports output files with processed data after using algorithms.
 */

public class Database {

	//Setting Variables
	ArrayList<SampleOfWifi> samples;
	FilterByMac macFilter;
	ArrayList<macSamlpe> filteredMacSamples;
	macSamlpe averg;
	macSamlpe thelocation;
	ArrayList<macSamlpe> weightAverMacSamples=new ArrayList<>();
	static ArrayList<macSamlpe> location=new ArrayList<>();
	ArrayList<macSamlpe> input=new ArrayList<>();
	macSamlpe temp;
	ArrayList<SampleOfWifi> outputalgo2;

	/**
	 * 
	 * @param CsvPath
	 * @param macFilterString
	 * @param numOfFilteredMacSamples
	 * @throws FileNotFoundException
	 * This is the first constructor for the first algorithm.
	 */
	public Database(String CsvPath, String macFilterString, int numOfFilteredMacSamples) throws FileNotFoundException {
		System.out.println("----------Database-----------");

		samples= ReadCombCsv.readCsvComb(Constant.getCsvCombPath()); //call the csv with the reader function 
		//macFilter=new FilterByMac(macFilterString); //  In case you want to filter in advance, run the filter
		for (int i = 0; i < samples.size(); i++) {
			for (int j = 0; j < samples.get(i).getwifiSpotList().size(); j++) {
				macFilter=new FilterByMac(samples.get(i).getwifiSpotList().get(j).getMac());
				filteredMacSamples= macFilter.topSamplesOfTheMac(samples, numOfFilteredMacSamples); 

				averg=MacLocation.Calcul_weight_aver(filteredMacSamples);
				averg.setMac(samples.get(i).getwifiSpotList().get(j).getMac());
				weightAverMacSamples.add(averg);	
			}
		}
		WriteMacLocationCSV.writeCsvFile( WriteMacLocationCSV.removeDuplicates(weightAverMacSamples),Constant.outputPathAlgo1); 
	}
	/**
	 * 
	 * @throws FileNotFoundException
	 * This is the second constructor for the second algorithm.
	 */
	public Database() throws FileNotFoundException {
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
		System.out.println("aaa"+samples.get(5).getTime());
		WriteMacLocationCSV.writeCsvFile( location, Constant.outputPathAlgo2); 
		System.out.println("aaa"+samples.get(5).getTime());

	}
}
