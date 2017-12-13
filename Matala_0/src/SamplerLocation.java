import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.runner.manipulation.Sortable;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class SamplerLocation {

	//		פונקצית מעטפת שקוראת לסורט (שקורא לפונק של חישוב הפיאיי והדברליו וההמרה) ואז לחישוב ממוצע משוקלל ומחזירה את העצם הרצוי
			public static macSamlpe thesamplerLocation(ArrayList<SampleOfWifi> s, ArrayList<macSamlpe> input)
			{
				ArrayList<macSamlpe> theSortmacSampleList = sort(s, input);
				macSamlpe output=Calcul_weight_aver(theSortmacSampleList, input);
				
				
				
				
				return output;
			}
	
	
	
	
	
	//פונקציה שמחשבת ממוצע משוקלל
			public static macSamlpe Calcul_weight_aver(ArrayList<macSamlpe> samp, ArrayList<macSamlpe> input){
				
				double [] wLat = new double[Constant.numOfSimillarSamples];
				double [] wLon = new double[Constant.numOfSimillarSamples];
				double [] wAlt = new double [Constant.numOfSimillarSamples];
				double sumWeight = 0,sumwLat=0,sumwLon=0,sumwAlt=0;
				double weightLat,weightLon,weightAlt;
				macSamlpe weight_aver;
				System.out.println("samp.size()"+samp.size());
				 for (int i = 0; i < Constant.numOfSimillarSamples; i++) {
					 wLat[i]= samp.get(i).getLat()*samp.get(i).getPiWeight();
					 wLon[i]= samp.get(i).getLon()*samp.get(i).getPiWeight();
					 wAlt[i]= samp.get(i).getAlt()*samp.get(i).getPiWeight();
					
				}
				for (int i = 0; i <Constant.numOfSimillarSamples; i++) {
					sumWeight=sumWeight+samp.get(i).getPiWeight();	
					sumwLat=sumwLat+wLat[i];
					sumwLon=sumwLon+wLon[i];
					sumwAlt=sumwAlt+wAlt[i];
				}
					weightLat=(double)sumwLat/sumWeight;
					weightLon=(double)sumwLon/sumWeight;
					weightAlt=(double)sumwAlt/sumWeight;
				
				
				
				
					weight_aver= new macSamlpe(sumWeight, weightLat, weightLon,  weightAlt);
				System.out.println("sumWeight"+sumWeight);
				return weight_aver;
				
			}
			
			
	
	public static ArrayList<macSamlpe> sort(ArrayList<SampleOfWifi> s, ArrayList<macSamlpe> input) {
		//קרחאה לפונקמיה שמסההת מהקובץ ומחברת לאריי ליסט אחד את כל הדגימות עם אותו מאק
		ArrayList<macSamlpe> SortListWithPI=picalc(s, input);
		System.out.println("SortAllListOfmac.size()"+SortListWithPI.size());
		Collections.sort(SortListWithPI, SameList);
		System.out.println("SortAllListOfmac mesunan.size()"+SortListWithPI.size());

			
		return SortListWithPI;

	}
//פונקציית עזר לסורט - שימוש בקומפרטור	
private static final Comparator <macSamlpe> SameList = new Comparator<macSamlpe>() {
	@Override
	public int compare(macSamlpe Obj1, macSamlpe Obj2) {
		double piObj1 =Obj1.getSignal();
		double piObj2 = Obj2.getSignal();
		if (piObj1 < piObj2)
			return 1;
		if (piObj1 > piObj2)
			return -1;
		return 0;
	}
};
	
	
	
	
	// pi calculate
	// מחזיר רשימה חדשה של מאק סמפל עם הפי איי בתוך כל עצם
	public static ArrayList<macSamlpe> picalc (ArrayList<SampleOfWifi> allWifiSmp, ArrayList<macSamlpe> input)
	{
		ArrayList<macSamlpe> theMacSamples= SampleOfWifiToMacSample.castList(allWifiSmp); //casting the sample of wifi arrayList to macSample ArrayList
		double pi=1;
		for (int i = 0; i < theMacSamples.size(); i++) {
			for (int j = 0; j < input.size(); j++) {
				pi= pi*weightcalc(theMacSamples.get(i), input.get(j));
			}
		
			theMacSamples.get(i).setPiWeight(pi);
			
		}


		
		
		
		return theMacSamples;
	}
	
	
	
	
	//weight calculate
	public static double weightcalc (macSamlpe smp, macSamlpe input ){
		
		
		return (double)Constant.norm/((Math.pow(diffcalc(smp, input), Constant.sigDiff))*(Math.pow(input.getSignal(), Constant.power)));

	}
	
	
	
	
	//diff calculate
	public static double diffcalc (macSamlpe smp, macSamlpe input ){
			double diff=0;
		if (smp.getSignal()==Constant.noSignal)
		{
			diff= Constant.diffNoSig;
		}
		else {
			diff=Math.max(Math.abs(input.getSignal()-smp.getSignal()),Constant.minDiff);
		}
		
	return diff;	
	}
	
	//---------------------------------------------------------main--------------------------------------------
	public static void main(String[] args) {
		macSamlpe smp = new macSamlpe(-62, 32.103, 35.208, 650);
		macSamlpe input = new macSamlpe(-50, 32.103, 35.208, 650);
		double difcheck= diffcalc(smp, input);
		System.out.println(difcheck);
		double weightcheck= weightcalc(smp, input);
		System.out.println(weightcheck);
		ArrayList<SampleOfWifi> s= new ArrayList<>();
		//ArrayList<SampleOfWifi> input= new ArrayList<>();
		//macSamlpe list = thesamplerLocation( s, ArrayList<macSamlpe> input)

		
		
		

	}
	
	
}
