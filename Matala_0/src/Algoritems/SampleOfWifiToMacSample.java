package Algoritems;
import java.util.ArrayList;

import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;
/**
 * 
 * @authors Tal And Shaked
 * This class converts the list of all samples by sampling date into a list of samples by MAC address.
 *  For the first algorithm
 *
 */
public class SampleOfWifiToMacSample {
	/**
	 * 
	 * @param list
	 * @return list of all samples by mac address
	 */
	public static ArrayList<macSamlpe> castList(ArrayList<SampleOfWifi> list) {
		ArrayList<macSamlpe> newList=new ArrayList<>();
		ArrayList<macSamlpe> allListOfmac=new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			newList=cast(list.get(i));	
			for (int j = 0; j < newList.size(); j++) {
				allListOfmac.add(newList.get(j));
			}
		}

		return allListOfmac;


	}

	/**
	 * 
	 * @param s
	 * @return Row-level conversion
	 */
	public static ArrayList<macSamlpe> cast(SampleOfWifi s) {
		macSamlpe oneSample;

		ArrayList<macSamlpe> newList=new ArrayList<>();
		for (int i = 0; i < s.getwifiSpotList().size(); i++) {
			{
				oneSample=new macSamlpe(s.getwifiSpotList().get(i).getSignal(), s.getLat(), s.getLon(), s.getAlt());
				newList.add(oneSample);
			}
		}		
		return newList;

	}
}
