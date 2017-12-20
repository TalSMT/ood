package Algoritems;
import java.util.ArrayList;

import Sample_Object.macSamlpe;

public class MacLocation {
	
	
	
	 //פונקציה שמחשבת ממוצע משוקלל
		public static macSamlpe Calcul_weight_aver(ArrayList<macSamlpe> samp){
			int Quantsample = samp.size();
			double [] weight = new double[Quantsample];
			double [] wLat = new double[Quantsample];
			double [] wLon = new double[Quantsample];
			double [] wAlt = new double [Quantsample];
			double sumWeight = 0,sumwLat=0,sumwLon=0,sumwAlt=0;
			double weightLat,weightLon,weightAlt;
			macSamlpe weight_aver;
			for (int i = 0; i <Quantsample; i++) {
				 weight[i] =(double)1/(samp.get(i).getSignal()*samp.get(i).getSignal());
				 //System.out.println("weight[i]"+weight[i]);
				 wLat[i]= samp.get(i).getLat()*weight[i];
				 wLon[i]= samp.get(i).getLon()*weight[i];
				 wAlt[i]= samp.get(i).getAlt()*weight[i];
			}
			for (int i = 0; i < Quantsample; i++) {
				sumWeight=sumWeight+weight[i];	
				sumwLat=sumwLat+wLat[i];
				sumwLon=sumwLon+wLon[i];
				sumwAlt=sumwAlt+wAlt[i];
			}
				weightLat=sumwLat/sumWeight;
				weightLon=sumwLon/sumWeight;
				weightAlt=sumwAlt/sumWeight;
			
				
				weight_aver= new macSamlpe(sumWeight, weightLat, weightLon, weightAlt);
				//weight_aver.setMac(samp.get(0).getMac());
				 //System.out.println("mac "+samp.get(0).getMac());

			//System.out.println("sumWeight"+sumWeight);
			return weight_aver;
			
		}
		
		
	
	
	
	public MacLocation() {
		// TODO Auto-generated constructor stub
	}

}
