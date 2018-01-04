package DataBase;

import java.util.ArrayList;

import Algoritems.SampleOfWifiToMacSample;
import Filters.Condition;
import Gui_Designer.Gui_Data;
import ReadAndWriteFiles.WriteMacLocationCSV;
import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

public class SetDataBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * --------------------delete the dataBase--------------------
	 * @param combData
	 * @return
	 */
	public static ArrayList <SampleOfWifi> deleteCombData ( ArrayList <SampleOfWifi> combData)
	{
		combData.removeAll(combData);
		
		
		System.out.println("deleted");
		return combData;
		
	}
	
	/**
	 * -----------get arraylist of sampleofwifi, convert it to arraylist of macSample and return the number of the different Macs
	 * @param combData
	 * @return
	 */
	public static int numOfDifferentMacSamples ( ArrayList <SampleOfWifi> combData)
	{
		 ArrayList <macSamlpe> combDataMacSample;
		 combDataMacSample=SampleOfWifiToMacSample.castList(combData);
		 combDataMacSample=WriteMacLocationCSV.removeDuplicates(combDataMacSample);
		return combDataMacSample.size();
		
		
	}
	
	//---------------------------------------------ôéìèøéí------------
	/**
	 * filter - A function that receives ArrayList of SampleOfWifi objects and a value to filter and returns ArrayList of filtered objects according to the desired value
	 * @param list
	 * @param condition
	 * @return
	 */
	//call to filters classes
	public static ArrayList<SampleOfWifi> filter(ArrayList<SampleOfWifi> listAll, Condition condition){
		ArrayList<SampleOfWifi> filtered = new ArrayList<>();
		for (int i = 0; i < listAll.size(); i++) {
			if(condition.test(listAll.get(i)))
				filtered.add(listAll.get(i));
		} 

		return filtered;
	}

	//filter that check if the oprand not is on
	public static ArrayList<SampleOfWifi> filterNOT(ArrayList<SampleOfWifi> combData,ArrayList<SampleOfWifi> filtered){
		boolean flag= true;
		ArrayList <SampleOfWifi> filteredNOT=new ArrayList<>();
		if (Gui_Data.Operand.equals("NOT")){
			for (int i = 0; i <combData.size(); i++) {
				for (int j = 0; j < filtered.size(); j++) {
					if(combData.get(i).equals(filtered.get(j)))
					{
					flag=false;	
					}
					
				}
				
				if (flag) {
				filteredNOT.add(combData.get(i));
				flag=true;
				}
			}
			filtered=filteredNOT;
		}
		
		return filtered;
		
	}
	
	
}