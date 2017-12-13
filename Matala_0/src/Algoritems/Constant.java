package Algoritems;
import java.util.ArrayList;

import Sample_Object.macSamlpe;


public class Constant {
	
	//Set up constants
	// for the first algo
	static String CsvCombPath="C:\\matala2\\_comb_all_.csv";		//csv path
	
	static String macFilterString= "42501_10131_891006";		// mac id for filter
	static int numOfFilteredMacSamples=3;		// number of wanted highes signal sample of the mac
	
	// for the second algo
	static int power= 2; // the power of the 
	static double norm= 10000;
	static double sigDiff=0.4;
	static double minDiff=3;
	static double noSignal=-120;
	static double diffNoSig=100;
	static int numOfSimillarSamples=3;		// number 


	
	
	public static String getCsvCombPath() {
		return CsvCombPath;
	}



	public Constant() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database algo1Check= new Database(CsvCombPath, macFilterString, numOfFilteredMacSamples); // call algo 1
		 ArrayList<macSamlpe> inputcheckList= new ArrayList<>();
		 macSamlpe smp1 = new macSamlpe(-50, 0, 0, 0);
		 macSamlpe smp2 = new macSamlpe(-70, 0, 0, 0);
		 macSamlpe smp3 = new macSamlpe(-90, 0, 0, 0);
		inputcheckList.add(smp1);
		inputcheckList.add(smp2);
		inputcheckList.add(smp3);
		
		
		//-------------------------------------------הכנסת ערכים כמו הערכים של בועז לצורך בדיקה---------------------------------------------------
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
		 macSamlpe thelocation=SamplerLocation.Calcul_weight_aver(datacheckList, inputcheckList);
		System.out.println("lat "+thelocation.getLat()+" lon "+thelocation.getLon()+" alt "+thelocation.getAlt()+" signal "+thelocation.getSignal());
		//-------------------------------------------הכנסת ערכים כמו הערכים של בועז לצורך בדיקה---------------------------------------------------

		
		
		
		//Database algo2Check= new Database(CsvPath, inputcheckList); // call algo 2



	}

	
	
	
	
}
