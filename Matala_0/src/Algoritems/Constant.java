package Algoritems;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Sample_Object.macSamlpe;
/**
 * 
 * @authors Tal And Shaked
 * In this class, the constant variables are defined 
 * for the first algorithm for finding the MAC address location 
 * and for the second algorithm for finding the position of the sample.
 *  This class runs both algorithms together or separately for user selection.
 *
 */

public class Constant {

	//Set up constants

	// for the first algo
	static String CsvCombPath="C:\\matala2\\_comb_all_BM2_.csv";		//csv path -all samples
	static String macFilterString= "42501_10131_891006";		// mac id for filter
	static int numOfFilteredMacSamples=3;		// number of wanted highest signal sample of the mac
	static String outputPathAlgo1="C:\\matala2\\OUTPUT_ALGO\\ALGO1_MacAndLocationCsv.csv";
	// for the second algo
	static int power= 2; 
	static double norm= 10000;
	static double sigDiff=0.4;
	static double minDiff=3;
	static double noSignal=-120;
	static double diffNoSig=100;
	static int numOfSimillarSamples=50;		// A number of samples are desirable for finding similarities
	static String CsvNoGPSPath="C:\\matala2\\_comb_no_gps_ts1.csv";		//csv path- test file to fill the location
	static String outputPathAlgo2="C:\\matala2\\OUTPUT_ALGO\\ALGO2_SamplerLocationCsv.csv";





	public static String getCsvCombPath() {
		return CsvCombPath;
	}


	public static void main(String[] args) throws FileNotFoundException {
		//Database algo1Check= new Database(CsvCombPath, macFilterString, numOfFilteredMacSamples); // call algo 1
		ArrayList<macSamlpe> inputcheckList= new ArrayList<>();
		macSamlpe smp1 = new macSamlpe(-50, 0, 0, 0);
		macSamlpe smp2 = new macSamlpe(-70, 0, 0, 0);
		macSamlpe smp3 = new macSamlpe(-90, 0, 0, 0);
		inputcheckList.add(smp1);
		inputcheckList.add(smp2);
		inputcheckList.add(smp3);


		//-------------------------------------------Tests for the first algorithm---------------------------------------------------
		macSamlpe che1 = new macSamlpe(-62, 32.103, 35.208, 650);
		che1.setPiWeight(0.476988545);
		macSamlpe che2 = new macSamlpe(-82, 32.105, 35.205, 660);
		che2.setPiWeight(0.17381326);
		macSamlpe che3 = new macSamlpe(-120, 32.103, 35.307, 680);
		che3.setPiWeight(0.158379105);
		ArrayList<macSamlpe> datacheckList= new ArrayList<>();
		datacheckList.add(che1);
		datacheckList.add(che2);
		datacheckList.add(che3);
		//System.out.println("lat "+thelocation.getLat()+" lon "+thelocation.getLon()+" alt "+thelocation.getAlt()+" signal "+thelocation.getSignal());
		//-------------------------------------------Tests for the second algorithm---------------------------------------------------




		Database algo2Check= new Database(); // call algo 2



	}





}