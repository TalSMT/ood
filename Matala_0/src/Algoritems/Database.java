package Algoritems;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Filters.FilterByMac;
import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.WriteMacLocationCSV;
import ReadAndWriteFiles.csvToKML;
import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

import java.util.*;
public class Database {
	
	//äâãøú îùúðéí
	ArrayList<SampleOfWifi> samples;

	FilterByMac macFilter;
	ArrayList<macSamlpe> filteredMacSamples;
	macSamlpe averg;
	macSamlpe thelocation;
	ArrayList<macSamlpe> weightAverMacSamples=new ArrayList<>();
	ArrayList<macSamlpe> location=new ArrayList<>();
	ArrayList<macSamlpe> input=new ArrayList<>();
	macSamlpe temp;
	ArrayList<SampleOfWifi> outputalgo2;


	//First constructor for the first algo
	public Database(String CsvPath, String macFilterString, int numOfFilteredMacSamples) throws FileNotFoundException {
		 System.out.println("----------Database-----------");

		 samples= ReadCombCsv.readCsvComb(Constant.getCsvCombPath()); //call the csv withoutreader function 
		 //macFilter=new FilterByMac(macFilterString); //במקרה ורוצים לסנן מראש אז להפעיל
		 for (int i = 0; i < samples.size(); i++) {
			 for (int j = 0; j < samples.get(i).getwifiSpotList().size(); j++) {//öøéê ìäñéø ëôéìåéåú åìéöà ñéàñåé- àåìé ÷åãí ìòùåú àú ääîøä ìøùéîä ùì ëåìí åàæ
				 macFilter=new FilterByMac(samples.get(i).getwifiSpotList().get(j).getMac());//áæéîåï äæä àðçðå ÷åøàåú ìîà÷ ùì ëì àçã îäòöîéí

				 filteredMacSamples= macFilter.topSamplesOfTheMac(samples, numOfFilteredMacSamples); 
				 
				 averg=MacLocation.Calcul_weight_aver(filteredMacSamples);
				 averg.setMac(samples.get(i).getwifiSpotList().get(j).getMac());
				 weightAverMacSamples.add(averg);
				// System.out.println(averg.getSignal());
				//System.out.println("lat "+averg.getLat()+" lon "+averg.getLon()+" alt "+averg.getAlt()+" signal "+averg.getSignal());	
			}
			
			
		}
		 //System.out.println("weightAverMacSamples.size()"+weightAverMacSamples.size());

		 for (int i = 0; i < weightAverMacSamples.size(); i++) {
				//System.out.println("mac"+weightAverMacSamples.get(i).getMac()+"lat "+weightAverMacSamples.get(i).getLat()+" lon "+weightAverMacSamples.get(i).getLon()+" alt "+weightAverMacSamples.get(i).getAlt()+" signal "+weightAverMacSamples.get(i).getSignal());	
			
		}
		 WriteMacLocationCSV.writeCsvFile( WriteMacLocationCSV.removeDuplicates(weightAverMacSamples),Constant.outputPathAlgo1);
		 
		 
		 
		 
		 
	}
	
		//Second constructor for the second algo
		public Database() throws FileNotFoundException {
			 System.out.println("----------SecondDatabase-----------");
			 samples= ReadCombCsv.readCsvComb(Constant.getCsvCombPath()); //call the csv withoutreader function 
			 outputalgo2= ReadCombCsv.readCsvComb(Constant.CsvNoGPSPath); //call the csv withoutreader function 

			 for (int i = 0; i < outputalgo2.size(); i++) {
				 for (int j = 0; j < outputalgo2.get(i).getwifiSpotList().size(); j++) {//öøéê ìäñéø ëôéìåéåú åìéöà ñéàñåé- àåìé ÷åãí ìòùåú àú ääîøä ìøùéîä ùì ëåìí åàæ
					 
						temp= new macSamlpe(outputalgo2.get(i).getwifiSpotList().get(j).getSignal(), 0, 0,0) ;
						temp.setMac(outputalgo2.get(i).getwifiSpotList().get(j).getMac());
						 input.add(temp);

					 
				 }
					 thelocation=SamplerLocation.thesamplerLocation(samples, input);
						System.out.println("i= "+i);	
						//System.out.println("lat "+thelocation.getLat()+" lon "+thelocation.getLon()+" alt "+thelocation.getAlt()+" signal "+thelocation.getSignal());	

					 //averg.setMac(samples.get(i).getwifiSpotList().get(j).getMac());
					 location.add(thelocation);
					// System.out.println(averg.getSignal());
					//System.out.println("lat "+averg.getLat()+" lon "+averg.getLon()+" alt "+averg.getAlt()+" signal "+averg.getSignal());	
					  
				}
				
			 WriteMacLocationCSV.writeCsvFile( location, Constant.outputPathAlgo2);

			
		
			 
			 
			 
			 
		}
	
	//Map<String,ArrayList<macSamlpe> > map;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		

	}
	
	
	

	 
	 }