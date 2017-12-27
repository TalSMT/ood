package DataBase;

import java.util.ArrayList;

import Algoritems.SampleOfWifiToMacSample;
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
	
	public static int numOfDifferentMacSamples ( ArrayList <SampleOfWifi> combData)
	{
		 ArrayList <macSamlpe> combDataMacSample;
		 combDataMacSample=SampleOfWifiToMacSample.castList(combData);
		 combDataMacSample=WriteMacLocationCSV.removeDuplicates(combDataMacSample);
		return combDataMacSample.size();
		
	}
	
	
	
	
	
	
	
}
