package Algoritems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.print.DocFlavor.INPUT_STREAM;

import org.junit.runner.manipulation.Sortable;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;
import Sample_Object.macSamlpe;

/**
 * 
 * @authors Tal And Shaked
 * This department is responsible for finding the location of the sample according to the number of MAC addresses that are similar to the desired input
 *
 */
public class SamplerLocation {
	/**
	 * 
	 * @param s -ArrayList of SampleOfWifi
	 * @param input - ArrayList of macSamlpe
	 * @return Weighted position of one object
	 * The function calls functions: for calculating the weighted average,IP,
	 *  and converting a list of all the samples to the list according to MAC addresses, and return one object.
	 */
	public static macSamlpe thesamplerLocation(ArrayList<SampleOfWifi> s, ArrayList<macSamlpe> input)
	{
		ArrayList<SampleOfWifi> theSortmacSampleList = sort(s, input);
		for (int i = 0; i < theSortmacSampleList.size(); i++) {
			//	System.out.println(" pi "+theSortmacSampleList.get(2).getPi()+"lat "+theSortmacSampleList.get(2).getLat()+" lon "+theSortmacSampleList.get(2).getLon()+" alt "+theSortmacSampleList.get(2).getAlt());
		}
		macSamlpe output=Calcul_weight_aver(theSortmacSampleList, input);

		//System.out.println("lat "+output.getLat()+" lon "+output.getLon()+" alt "+output.getAlt());
		//System.out.println("theSortmacSampleList "+theSortmacSampleList.get(6).getPi());
		return output;
	}

	/**
	 * 
	 * @param samp
	 * @param input
	 * @return object type macSample
	 * This function calculates a weighted average for a second algorithm
	 */
	public static macSamlpe Calcul_weight_aver(ArrayList<SampleOfWifi> samp, ArrayList<macSamlpe> input){

		double [] wLat = new double[Constant.numOfSimillarSamples];
		double [] wLon = new double[Constant.numOfSimillarSamples];
		double [] wAlt = new double [Constant.numOfSimillarSamples];
		double sumWeight = 0,sumwLat=0,sumwLon=0,sumwAlt=0;
		double weightLat,weightLon,weightAlt;
		macSamlpe weight_aver;
		//System.out.println("samp.size()"+samp.size());
		if(samp.size()==1)
		{
			wLat[0]= samp.get(0).getLat()*samp.get(0).getPi();
			wLon[0]= samp.get(0).getLon()*samp.get(0).getPi();
			wAlt[0]= samp.get(0).getAlt()*samp.get(0).getPi();

		}

		for (int i = 1; i <=Constant.numOfSimillarSamples; i++) {
			if(i<=samp.size()-1)
			{
				wLat[i-1]= samp.get(i).getLat()*samp.get(i).getPi();
				wLon[i-1]= samp.get(i).getLon()*samp.get(i).getPi();
				wAlt[i-1]= samp.get(i).getAlt()*samp.get(i).getPi();
				//System.out.println("Lat "+samp.get(i).getLat()+"Lon "+samp.get(i).getLon()+"alt "+samp.get(i).getAlt()+" first mac "+samp.get(i).getwifiSpotList().get(0).getMac()+" pi= "+samp.get(i).getPi());	

				//System.out.println("wLat[i-1] "+wLat[i-1]+" wLon[i-1] "+wLon[i-1]+"  wAlt[i-1] "+ wAlt[i-1]);	
			}

		}
		for (int i = 0; i <Constant.numOfSimillarSamples; i++) {
			sumWeight=sumWeight+samp.get(i).getPi();	
			sumwLat=sumwLat+wLat[i];
			sumwLon=sumwLon+wLon[i];
			sumwAlt=sumwAlt+wAlt[i];
		}
		weightLat=sumwLat/sumWeight;
		weightLon=sumwLon/sumWeight;
		weightAlt=sumwAlt/sumWeight;
		//System.out.println("sumWeight"+ sumWeight+ " sumwLat"+sumwLat+"weightLat"+weightLat);

		weight_aver= new macSamlpe(sumWeight, weightLat, weightLon,  weightAlt);
		//System.out.println("weight_aver.getLat() "+weight_aver.getLat()+" weight_aver.getLon()+ "+weight_aver.getLon()+"  weight_aver.getAlt() "+ weight_aver.getAlt());	

		//System.out.println("sumWeight"+sumWeight);
		return weight_aver;

	}

	/**
	 * 
	 * @param s - ArrayList of SampleOfWifi
	 * @param input
	 * @return List sorted by the highest PI
	 * This function calls a function that calculates PI
	 *  and adds all the samples with the same MAC address to one list
	 */

	public static ArrayList<SampleOfWifi> sort(ArrayList<SampleOfWifi> s, ArrayList<macSamlpe> input) {
		ArrayList<SampleOfWifi> SortListWithPI=picalc(s, input);
		//System.out.println("SortAllListOfmac.size()"+SortListWithPI.size());
		Collections.sort(SortListWithPI, SameList);
		//System.out.println("SortAllListOfmac mesunan.size()"+SortListWithPI.size());
		return SortListWithPI;
	}



	/**
	 * Using the comparator to sort the list according to the highest PI
	 */

	private static final Comparator <SampleOfWifi> SameList = new Comparator<SampleOfWifi>() {
		@Override
		public int compare(SampleOfWifi Obj1, SampleOfWifi Obj2) {
			double piObj1 =Obj1.getPi();
			double piObj2 = Obj2.getPi();
			if (piObj1 < piObj2)
				return 1;
			if (piObj1 > piObj2)
				return -1;
			return 0;
		}
	};


	/**
	 * 
	 * @param allWifiSmp
	 * @param input
	 * @return ArrayList of SampleOfWifi With an object of PI calculated
	 * This function is responsible for calculating PI
	 */

	public static ArrayList<SampleOfWifi> picalc (ArrayList<SampleOfWifi> allWifiSmp, ArrayList<macSamlpe> input)
	{
		//ArrayList<macSamlpe> theMacSamples= SampleOfWifiToMacSample.castList(allWifiSmp); //casting the sample of wifi arrayList to macSample ArrayList
		double pi=1;
		double w;
		double mehane=0;
		boolean yeshbashura= false;
		for (int i = 0; i<allWifiSmp.size(); i++) { //Move through all the lines in the file
			for (int j = 0; j < input.size(); j++) {//Move through all the inputs
				for (int j2 = 0; j2 < allWifiSmp.get(i).getwifiSpotList().size(); j2++) {//We go through the whole line
					if( allWifiSmp.get(i).getwifiSpotList().get(j2).getMac().equals(input.get(j).getMac()))//Check whether this line contains one of the MAC addresses from the input
						yeshbashura= true;
				}
				for (int j2 = 0; j2 < allWifiSmp.get(i).getwifiSpotList().size(); j2++) {//Go through the whole line again
					if( yeshbashura)//If you have one of our input in one row, Calculate the weighted average and add it to PI
					{
						w= weightcalc(allWifiSmp.get(i).getwifiSpotList().get(j2), input.get(j));//Calculate the weighted average
						pi=  (pi*w);
						//						pi=(pi*10000);
						//						pi=(int)pi;
						//						pi=(double)(pi/10000);
						//	System.out.println("w= "+w+" pi= "+pi);	
					}

					else // If the MAC address was not found in the row, The weighted average is calculated according to a constant variable
					{	

						mehane=((Math.pow(Constant.diffNoSig, Constant.sigDiff))*(Math.pow(input.get(j).getSignal(), Constant.power)));
						mehane= (int)mehane;
						pi=pi*(Constant.norm/mehane);
						//	System.out.println("(Constant.norm/mehane)   "+(Constant.norm/mehane));
						//						pi=pi*1000000;
						//						pi=(int)pi;
						//						pi=(double)(pi/1000000);
						//						System.out.println("mehane"+mehane);
						//						System.out.println("mehane"+((Math.pow(Constant.diffNoSig, Constant.sigDiff))*(Math.pow(input.get(j).getSignal(), Constant.power))));
						//						System.out.println("not good pi"+ pi+ "");
					}

				}

			}		

			yeshbashura= false;

			//	System.out.println("finallllll pi"+ pi);
			if(pi<0.0001){
				pi=-1;
			}
			allWifiSmp.get(i).setPi(pi);
			pi=1;
		}
		return allWifiSmp;
	}


	/**
	 * 
	 * @param smp
	 * @param input
	 * @return One object of weighted average
	 * This function is responsible for calculating the weighted average for a second algorithm
	 */

	public static double weightcalc (WifiPoint smp, macSamlpe input ){
		//System.out.println("w"+(double)Constant.norm/((Math.pow(diffcalc(smp, input), Constant.sigDiff))*(Math.pow(input.getSignal(), Constant.power))));
		double w=(double)Constant.norm/((Math.pow(diffcalc(smp, input), Constant.sigDiff))*(Math.pow(input.getSignal(), Constant.power)));

		return w;
		//return (double)((Math.pow(diffcalc(smp, input), Constant.sigDiff))*(Math.pow(input.getSignal(), Constant.power)));
	}


	/**
	 * 
	 * @param shura
	 * @param input
	 * @return The difference between the given Signal and the Signal of the data
	 * 
	 */

	public static double diffcalc (WifiPoint  shura, macSamlpe input ){
		double diff=0;
		diff=Math.max(Math.abs(input.getSignal()-shura.getSignal()),Constant.minDiff);
		return diff;	
	}

	//---------------------------------------------------------main--------------------------------------------
	public static void main(String[] args) {
		macSamlpe smp = new macSamlpe(-62, 32.103, 35.208, 650);
		macSamlpe input = new macSamlpe(-50, 32.103, 35.208, 650);

		ArrayList<SampleOfWifi> s= new ArrayList<>();
		//ArrayList<SampleOfWifi> input= new ArrayList<>();
		//macSamlpe list = thesamplerLocation( s, ArrayList<macSamlpe> input)





	}


}
