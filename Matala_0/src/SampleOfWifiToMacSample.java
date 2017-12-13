import java.util.ArrayList;

public class SampleOfWifiToMacSample {

	//המרת רשימה של סמפל אוף וייפי 
		public static ArrayList<macSamlpe> castList(ArrayList<SampleOfWifi> list) {
			ArrayList<macSamlpe> newList=new ArrayList<>();
			ArrayList<macSamlpe> allListOfmac=new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				newList=cast(list.get(i));	
				System.out.println("newList.size()"+newList.size());
				for (int j = 0; j < newList.size(); j++) {
					System.out.println("listOfmac.get(i)"+newList.get(j).getSignal());
					allListOfmac.add(newList.get(j));
					
				}
			
		}
			
			
			return allListOfmac;

			
		}
		
	
	//המרה של סמפל אוף וייפי אחד
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

	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
