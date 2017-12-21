package Algoritems;
import java.util.ArrayList;

import Sample_Object.macSamlpe;

/**
 * 
 * @authors Tal And Shaked
 * This class calculates the location of a given MAC address.
 *  The class contains a function to calculate a weighted average that can be used to estimate the location of the desired MAC address.
 *
 */
public class MacLocation {

	/**
	 * 
	 * @param samp
	 * @return Weighted average for Object type macSample
	 */
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
		return weight_aver;
	}
}

