package Algoritems;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Filters.FilterByMac;
import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.WriteMacLocationCSV;
import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

public class CallToAlgo1 {


	//Setting Variables
		ArrayList<SampleOfWifi> samples;
		FilterByMac macFilter;
		ArrayList<macSamlpe> filteredMacSamples;
		public static macSamlpe averg;//the calculate weight result come in macSample
		macSamlpe thelocation;
		public static ArrayList<macSamlpe> weightAverMacSamples=new ArrayList<>();
		
		
		/**
		 * This is the first constructor for the first algorithm.
		 * _______read the all list and find for every mac in every line the specific location________
		 * @param CsvPath
		 * @param macFilterString
		 * @param numOfFilteredMacSamples
		 * @throws FileNotFoundException
		 * 
		 */
		public CallToAlgo1() throws FileNotFoundException {
			System.out.println("----------Database-----------");

			//samples= ReadCombCsv.readCsvComb(Constant.getCsvCombPath()); //call the csv with the reader function without headers
			samples= ReadCombCsv.readCsvCombwithHeaders(Constant.getCsvCombPath()); //call the csv with the reader function 

			//macFilter=new FilterByMac(macFilterString); //  In case you want to filter in advance, run the filter
			for (int i = 0; i < samples.size(); i++) {
				for (int j = 0; j < samples.get(i).getwifiSpotList().size(); j++) {
					macFilter=new FilterByMac(samples.get(i).getwifiSpotList().get(j).getMac());
					filteredMacSamples= macFilter.topSamplesOfTheMac(samples, Constant.numOfFilteredMacSamples); 

					averg=MacLocation.Calcul_weight_aver(filteredMacSamples);//calculate weight
					averg.setMac(samples.get(i).getwifiSpotList().get(j).getMac()); // add the specific mac
					weightAverMacSamples.add(averg);	
				}
			}
			WriteMacLocationCSV.writeCsvFile( WriteMacLocationCSV.removeDuplicates(weightAverMacSamples),Constant.outputPathAlgo1); 
		}
		
		/**
		 * This is the second constructor for the first algorithm.
		 * _______read one MacSample and find his location ________
		 * @param CsvPath
		 * @param macFilterString // the specific mac to get the location
		 * @param numOfFilteredMacSamples
		 * @throws FileNotFoundException
		 * 
		 */
		public CallToAlgo1(String macFilterString) throws FileNotFoundException {

			//samples= ReadCombCsv.readCsvComb(Constant.getCsvCombPath()); //call the csv with the reader function without headers
			samples= ReadCombCsv.readCsvCombwithHeaders(Constant.getCsvCombPath()); //call the csv with the reader function 
			//macFilter=new FilterByMac(macFilterString); //  In case you want to filter in advance, run the filter
			
					macFilter=new FilterByMac(macFilterString);
					filteredMacSamples= macFilter.topSamplesOfTheMac(samples, Constant.numOfFilteredMacSamples); 

					averg=MacLocation.Calcul_weight_aver(filteredMacSamples);//calculate weight
					averg.setMac(macFilterString); // add the specific mac- less relevant in this function
				
		
		}
		
		
		
		
	
	
	
	
	
}
