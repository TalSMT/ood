package Algoritems;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.WriteMacLocationCSV;
import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

public class Compare_Data_For_Testing {
	ArrayList<SampleOfWifi> listTest1=new ArrayList<>(); 
	ArrayList<SampleOfWifi> listWithAvgnew =new ArrayList<>(); 
	static double Average_diff_Of_TestingFiles=0;
	static macSamlpe temp;


	public Compare_Data_For_Testing() throws FileNotFoundException {
	
	//	listTest1=ReadCombCsv.readCsvComb(Constant.outputForTest1);
	//	System.out.println("ds");
		Average_diff_Of_TestingFiles=Average_diff(listTest1,Database.location);
			temp.setAverage_diff(Average_diff_Of_TestingFiles);
			Database.location.add(temp);
		
		WriteMacLocationCSV.writeCsvFile(Database.location, Constant.outputPathAlgo2);
	}
public static double Average_diff (ArrayList<SampleOfWifi> listTest1,ArrayList<macSamlpe> location){
	double diffAlt=0;
	double diffLon=0;
	double diffLat=0;
	double sumAllDiff=0;
	double average=0;
	for (int i = 0; i < listTest1.size(); i++) {
		for (int j = 0; j < location.size(); j++) {
			diffAlt=listTest1.get(i).getAlt()-location.get(j).getAlt();
			diffLon=listTest1.get(i).getLon()-location.get(j).getLon();
			diffLat=listTest1.get(i).getLat()-location.get(j).getLat();
			
		}
		sumAllDiff=diffAlt+diffLon+diffLat;
		average=(sumAllDiff/listTest1.size());
		location.get(i).setAverage_diff(average);
	}
	return average;
	
}
}
