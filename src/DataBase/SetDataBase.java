package DataBase;

import java.util.ArrayList;

import Sample_Object.SampleOfWifi;

public class SetDataBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static ArrayList <SampleOfWifi> deleteCombData ( ArrayList <SampleOfWifi> combData)
	{
		combData.removeAll(combData);
		
		
		System.out.println("deleted");
		return combData;
		
	}
	
	
	
	
	
}
