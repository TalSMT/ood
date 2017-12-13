import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * 
 * @authors Tal And Shaked
 *
 */
public class FilterByMac  {
	private String macFilter;
	
	/**
	 *
	 * @param phoneIdFilter
	 */
	public FilterByMac(String macFilter) {
		this.macFilter = macFilter;
	}	
	
	public ArrayList<macSamlpe> topSamplesOfTheMac(ArrayList<SampleOfWifi> s,int numOfTopSamples) {
		//פונקציה שמקבלת מהמשתמש את מס' הדגימות הכי חזקות שהוא מעוניין ומחזירה רשימה עם דגימות אלו
		ArrayList<macSamlpe> sortAllListOfmac=sort(s);
		ArrayList<macSamlpe> topNumOfSortAllListOfmac= new ArrayList<macSamlpe>();
		
		for (int i = 0; i < numOfTopSamples; i++) {
			if (numOfTopSamples<=sortAllListOfmac.size())
			topNumOfSortAllListOfmac.add(sortAllListOfmac.get(i));
			
		}
		System.out.println("ssss"+topNumOfSortAllListOfmac.size());
			
		return topNumOfSortAllListOfmac;

	}
	
	
	public ArrayList<macSamlpe> sort(ArrayList<SampleOfWifi> s) {
		//קרחאה לפונקמיה שמסההת מהקובץ ומחברת לאריי ליסט אחד את כל הדגימות עם אותו מאק
		ArrayList<macSamlpe> SortAllListOfmac=filter(s);
		System.out.println("SortAllListOfmac.size()"+SortAllListOfmac.size());
		Collections.sort(SortAllListOfmac, SameList);
		System.out.println("SortAllListOfmac mesunan.size()"+SortAllListOfmac.size());

			
		return SortAllListOfmac;

	}
//פונקציית עזר לסורט - שימוש בקומפרטור	
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
	
	
	
	
	//פונק שמקבלת רשימה של סמפל אוף וויפי ואת מסר הדגימות הכי חזקות שהמשתמש יבחר ומחזירה רשימה בגודל מס' זה של הדגימות הכי חזקות עבור מאק מסוים
	public ArrayList<macSamlpe> filter(ArrayList<SampleOfWifi> s) {
		macSamlpe macFilter;
		//System.out.println("ssssssssssssssssssss"+s.get(5).getwifiSpotList().size());
		
		
		ArrayList<macSamlpe> allListOfmac=new ArrayList<>();
		ArrayList<macSamlpe> listOfmac=new ArrayList<>();

		for (int i = 0; i < s.size(); i++) {
			listOfmac=test(s.get(i));	
			for (int j = 0; j < listOfmac.size(); j++) {
				System.out.println("listOfmac.get(i)"+listOfmac.get(j).getSignal());
				allListOfmac.add(listOfmac.get(j));
				
			}
		
	}		
		return allListOfmac;

	}

	
	
	
	public ArrayList<macSamlpe> test(SampleOfWifi s) {
		macSamlpe macFilter;
		//System.out.println("SampleOfWifi s mac "+ s.getwifiSpotList().get(3).getMac());

		ArrayList<macSamlpe> listOfmac=new ArrayList<>();
		for (int i = 0; i < s.getwifiSpotList().size(); i++) {
			if (this.macFilter.equals(s.getwifiSpotList().get(i).getMac()))
			{
				
				System.out.println("equals(s.getwifiSpotList().get(i).getMac()) "+ s.getwifiSpotList().get(i).getMac());

				macFilter=new macSamlpe(s.getwifiSpotList().get(i).getSignal(), s.getLat(), s.getLon(), s.getAlt());
				listOfmac.add(macFilter);
		}
		
	}		
		System.out.println("listOfmac "+ listOfmac.size());

		return listOfmac;

	}

}
