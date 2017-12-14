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

		 samples= ReadCombCsv.readCsvComb(); //call the csv withoutreader function 
		 //macFilter=new FilterByMac(macFilterString); //יצירת עצם מהמחלקה פילטרביימאק כדי לבצע עליו את הפעולות 
		 for (int i = 0; i < samples.size(); i++) {
			 for (int j = 0; j < samples.get(i).getwifiSpotList().size(); j++) {//צריך להסיר כפילויות וליצא סיאסוי- אולי קודם לעשות את ההמרה לרשימה של כולם ואז
				 macFilter=new FilterByMac(samples.get(i).getwifiSpotList().get(j).getMac());//בזימון הזה אנחנו קוראות למאק של כל אחד מהעצמים
				 filteredMacSamples= macFilter.topSamplesOfTheMac(samples, numOfFilteredMacSamples); 
				 
				 averg=MacLocation.Calcul_weight_aver(filteredMacSamples);
				 System.out.println(averg.getSignal());
				System.out.println("lat "+averg.getLat()+" lon "+averg.getLon()+" alt "+averg.getAlt()+" signal "+averg.getSignal());	
			}
			
			
		}
		 

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
