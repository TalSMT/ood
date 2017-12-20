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

public class SamplerLocation {

	//		פונקצית מעטפת שקוראת לסורט (שקורא לפונק של חישוב הפיאיי והדברליו וההמרה) ואז לחישוב ממוצע משוקלל ומחזירה את העצם הרצוי
	public static macSamlpe thesamplerLocation(ArrayList<SampleOfWifi> s, ArrayList<macSamlpe> input)
	{
		ArrayList<SampleOfWifi> theSortmacSampleList = sort(s, input);
		for (int i = 0; i < theSortmacSampleList.size(); i++) {
			System.out.println(" pi "+theSortmacSampleList.get(i).getPi()+"lat "+theSortmacSampleList.get(i).getLat()+" lon "+theSortmacSampleList.get(i).getLon()+" alt "+theSortmacSampleList.get(i).getAlt());

		}
		macSamlpe output=Calcul_weight_aver(theSortmacSampleList, input);

		//System.out.println("lat "+output.getLat()+" lon "+output.getLon()+" alt "+output.getAlt());
		//System.out.println("theSortmacSampleList "+theSortmacSampleList.get(6).getPi());




		return output;
	}





	//פונקציה שמחשבת ממוצע משוקלל
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



	public static ArrayList<SampleOfWifi> sort(ArrayList<SampleOfWifi> s, ArrayList<macSamlpe> input) {
		//קרחאה לפונקמיה שמסההת מהקובץ ומחברת לאריי ליסט אחד את כל הדגימות עם אותו מאק
		ArrayList<SampleOfWifi> SortListWithPI=picalc(s, input);
		//System.out.println("SortAllListOfmac.size()"+SortListWithPI.size());
		Collections.sort(SortListWithPI, SameList);
		//System.out.println("SortAllListOfmac mesunan.size()"+SortListWithPI.size());


		return SortListWithPI;

	}
	//פונקציית עזר לסורט - שימוש בקומפרטור	
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




	// pi calculate
	// מחזיר רשימה חדשה של מאק סמפל עם הפי איי בתוך כל עצם
	public static ArrayList<SampleOfWifi> picalc (ArrayList<SampleOfWifi> allWifiSmp, ArrayList<macSamlpe> input)
	{
		//ArrayList<macSamlpe> theMacSamples= SampleOfWifiToMacSample.castList(allWifiSmp); //casting the sample of wifi arrayList to macSample ArrayList
		double pi=1;
		int pi_abs=0;
		double w;
		double mehane=0;
		boolean yeshbashura= false;
		for (int i = 0; i<allWifiSmp.size(); i++) { //עוברים על כל השורות בקובץ
			for (int j = 0; j < input.size(); j++) {//עוברים על כל האינפוטים
				for (int j2 = 0; j2 < allWifiSmp.get(i).getwifiSpotList().size(); j2++) {//עוברים על כל השורה
					if( allWifiSmp.get(i).getwifiSpotList().get(j2).getMac().equals(input.get(j).getMac()))//בודקים אם יש בשורה זו אחד מהמקים מאינפוטים
						yeshbashura= true;
				}
				for (int j2 = 0; j2 < allWifiSmp.get(i).getwifiSpotList().size(); j2++) {//עוברים שוב על כל השורה
					if( yeshbashura)//אם יש בשורה את אחד מהאינפוטים שלנו
					{
						w= weightcalc(allWifiSmp.get(i).getwifiSpotList().get(j2), input.get(j));//מחשבים את דבליו
						
						pi=  (pi*w);//מוסיפים אותו לפי איי
						pi=(pi*10000);
						System.out.println("pi*10000 "+pi);
						pi=(int)pi;
						System.out.println("pi*int "+pi);

						pi=(double)(pi/10000);
						System.out.println("pi*new "+pi);

						System.out.println("w= "+w+" pi= "+pi);	
					}

					else // אם לא קיים בשורה אחד האינפוטים שלנו
					{	

						mehane=((Math.pow(Constant.diffNoSig, Constant.sigDiff))*(Math.pow(input.get(j).getSignal(), Constant.power)));// מחשבים את הדבליו הדיפולטיבי
						mehane= (int)mehane;
//						pi=pi*Constant.norm/mehane;//מוסיפים אותו לפי איי
						pi=pi*(Constant.norm/mehane);//מוסיפים אותו לפי איי
						System.out.println("(Constant.norm/mehane)   "+(Constant.norm/mehane));
//						pi=pi*1000000;
//						System.out.println("baddddpi*1000000 "+pi);
//
//						pi=(int)pi;
//						System.out.println("pi*int "+pi);
//
//						pi=(double)(pi/1000000);
//						System.out.println("badddpi*new "+pi);

						System.out.println("mehane"+mehane);
						System.out.println("mehane"+((Math.pow(Constant.diffNoSig, Constant.sigDiff))*(Math.pow(input.get(j).getSignal(), Constant.power))));
						System.out.println("not good pi"+ pi+ "");
					}


				}

			}		
		
		yeshbashura= false;


		System.out.println("finallllll pi"+ pi);
		allWifiSmp.get(i).setPi(pi);
		pi=1;
	}



	return allWifiSmp;
}




//weight calculate
public static double weightcalc (WifiPoint smp, macSamlpe input ){
	//System.out.println("w"+(double)Constant.norm/((Math.pow(diffcalc(smp, input), Constant.sigDiff))*(Math.pow(input.getSignal(), Constant.power))));
	double w=(double)Constant.norm/((Math.pow(diffcalc(smp, input), Constant.sigDiff))*(Math.pow(input.getSignal(), Constant.power)));
	
	return w;
	//return (double)((Math.pow(diffcalc(smp, input), Constant.sigDiff))*(Math.pow(input.getSignal(), Constant.power)));

}




//diff calculate
public static double diffcalc (WifiPoint  shura, macSamlpe input ){
	double diff=0;
	//				if (!shura.getMac().equals(input.getMac()))
	//				{
	//
	//					diff= Constant.diffNoSig;//100
	//				}
	//				else {
	diff=Math.max(Math.abs(input.getSignal()-shura.getSignal()),Constant.minDiff);



	//System.out.println("diff"+diff);


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
