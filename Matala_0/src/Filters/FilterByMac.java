package Filters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;
/**
 * 
 * @authors Tal And Shaked
 * This class filters by mac address
 */
public class FilterByMac  {
	//setting
	private String macFilter;

	/**
	 * 
	 * @param macFilter
	 */
	public FilterByMac(String macFilter) {
		this.macFilter = macFilter;
	}	
	/**
	 * 
	 * @param s
	 * @param numOfTopSamples - Number of samples desired
	 * @return List with the strongest mac samples
	 */
	public ArrayList<macSamlpe> topSamplesOfTheMac(ArrayList<SampleOfWifi> s,int numOfTopSamples) {
		ArrayList<macSamlpe> sortAllListOfmac=sort(s);
		ArrayList<macSamlpe> topNumOfSortAllListOfmac= new ArrayList<macSamlpe>();
		for (int i = 0; i < numOfTopSamples; i++) {
			System.out.println("i="+i+"sortsize"+sortAllListOfmac.size());
			if (i<=sortAllListOfmac.size()-1)
				topNumOfSortAllListOfmac.add(sortAllListOfmac.get(i));
		}

		return topNumOfSortAllListOfmac;

	}

	/**
	 * 
	 * @param s
	 * @return A sorted list of the most powerful mac samples
	 */
	public ArrayList<macSamlpe> sort(ArrayList<SampleOfWifi> s) {
		ArrayList<macSamlpe> SortAllListOfmac=filter(s);
		Collections.sort(SortAllListOfmac, SameList);	
		return SortAllListOfmac;

	}
	/**
	 *  Using the comparator to sort the list according to the most powerful mac samples
	 */
	private static final Comparator <macSamlpe> SameList = new Comparator<macSamlpe>() {
		@Override
		public int compare(macSamlpe macObj1, macSamlpe macObj2) {
			double signalOfmacObj1 =macObj1.getSignal();
			double signalOfmacObj2 = macObj2.getSignal();
			if (signalOfmacObj1 < signalOfmacObj2)
				return 1;
			if (signalOfmacObj1 > signalOfmacObj2)
				return -1;
			return 0;
		}
	};


	/**
	 * 
	 * @param s
	 * @return List the size of a given number of the most powerful samples for the MAC address...
	 */

	public ArrayList<macSamlpe> filter(ArrayList<SampleOfWifi> s) {
		ArrayList<macSamlpe> allListOfmac=new ArrayList<>();
		ArrayList<macSamlpe> listOfmac=new ArrayList<>();

		for (int i = 0; i < s.size(); i++) {
			listOfmac=test(s.get(i));	
			for (int j = 0; j < listOfmac.size(); j++) {
				allListOfmac.add(listOfmac.get(j));
			}

		}		
		return allListOfmac;

	}


	/**
	 * 
	 * @param s
	 * @return A list of equal Mac addresses
	 */

	public ArrayList<macSamlpe> test(SampleOfWifi s) {
		macSamlpe macFilter;
		ArrayList<macSamlpe> listOfmac=new ArrayList<>();
		for (int i = 0; i < s.getwifiSpotList().size(); i++) {
			if (this.macFilter.equals(s.getwifiSpotList().get(i).getMac()))
			{
				macFilter=new macSamlpe(s.getwifiSpotList().get(i).getSignal(), s.getLat(), s.getLon(), s.getAlt());
				macFilter.setMac(s.getwifiSpotList().get(i).getMac());
				listOfmac.add(macFilter);
			}

		}		

		return listOfmac;

	}

}
