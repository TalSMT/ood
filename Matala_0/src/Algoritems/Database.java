package Algoritems;
import java.io.File;
import java.util.ArrayList;

import Filters.FilterByMac;
import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.csvToKML;
import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

import java.util.*;
public class Database {
	
	//הגדרת משתנים
	ArrayList<SampleOfWifi> samples;
	FilterByMac macFilter;
	ArrayList<macSamlpe> filteredMacSamples;
	macSamlpe averg;
	macSamlpe thelocation;

	//First constructor for the first algo
	public Database(String CsvPath, String macFilterString, int numOfFilteredMacSamples) {
		 System.out.println("----------Database-----------");

		 samples= ReadCombCsv.readCsvComb();
		 //System.out.println(samples.get(3).getwifiSpotList().get(0).getMac());
		 macFilter=new FilterByMac(macFilterString); //נשנה את זה לפי המחלקה של הקבועים
		 for (int i = 0; i < samples.size(); i++) {
				//if (samples.get(i).getwifiSpotList().size()>3)
				//	System.out.println("one e"+samples.get(i).getwifiSpotList().get(2).getMac());
				//System.out.println();
		 }
		 filteredMacSamples= macFilter.topSamplesOfTheMac(samples, numOfFilteredMacSamples); //נשנה את זה לפי המחלקה של הקבועים
		 //print check
		 for (int i = 0; i < filteredMacSamples.size(); i++) {
			System.out.println("lat "+filteredMacSamples.get(i).getLat()+" lon "+filteredMacSamples.get(i).getLon()+" alt "+filteredMacSamples.get(i).getAlt()+" signal "+filteredMacSamples.get(i).getSignal());
		}

		 averg=MacLocation.Calcul_weight_aver(filteredMacSamples);
		 System.out.println(averg.getSignal());
		System.out.println("lat "+averg.getLat()+" lon "+averg.getLon()+" alt "+averg.getAlt()+" signal "+averg.getSignal());

	}
	
		//Second constructor for the second algo
		public Database(String CsvPath, ArrayList<macSamlpe> input) {
			 System.out.println("----------SecondDatabase-----------");
			 samples= csvToKML.readCsvFile(CsvPath);
			 
			 
			 thelocation=SamplerLocation.thesamplerLocation(samples, input);
			System.out.println("lat "+thelocation.getLat()+" lon "+thelocation.getLon()+" alt "+thelocation.getAlt()+" signal "+thelocation.getSignal());
			 
			 
		}
	
	//Map<String,ArrayList<macSamlpe> > map;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		

	}
	
	
	

	 
	 }
