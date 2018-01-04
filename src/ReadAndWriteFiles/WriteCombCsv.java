package ReadAndWriteFiles;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Gui_Designer.Gui_Data;
import Sample_Object.SampleOfWifi;

public class WriteCombCsv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * writeCsvFile- A function that receives an ArrayList and generates a new CSV file organized by the 10 strongest networks for each time sample
	 * @param csvSampleList
	 * @throws FileNotFoundException
	 */
	public static void  writeCsvFile(ArrayList<SampleOfWifi> csvSampleList) throws FileNotFoundException { //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
		BufferedWriter bw = null; // https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/?utm_source=mkyong&utm_medium=author&utm_campaign=related-post&utm_content=6
		FileWriter networksCSV = null;
		try {
			
			String headline = " time, id, lat,  lon,  alt, wifi_network, ssid1, mac1, frequncy1, signal1, ssid2, mac2, frequncy2, signal2, ssid3, mac3, frequncy3, signal3, ssid4, mac4, frequncy4, signal4, ssid5, mac5, frequncy5, signal5, ssid6, mac6, frequncy6, signal6, ssid7, mac7, frequncy7, signal7, ssid8, mac8, frequncy8, signal8, ssid9, mac9, frequncy9, signal9, ssid10, mac10, frequncy10, signal10\n";
			networksCSV = new FileWriter(Gui_Designer.Gui_Data.OutputfoldePath); 
			String line, lineTenNetworks="";
			bw = new BufferedWriter(networksCSV);
			if (Gui_Data.deletedHeader=true)
				bw.write(headline);
			for (int i = 0; i < csvSampleList.size(); i++) {
				line= csvSampleList.get(i).getTime()+","+csvSampleList.get(i).getPhoneId()+","+csvSampleList.get(i).getLat()+","+csvSampleList.get(i).getLon()+","+csvSampleList.get(i).getAlt()+","+csvSampleList.get(i).getWifi_network();
				
				for (int j = 0; j < csvSampleList.get(i).getwifiSpotList().size(); j++) {
					System.out.println(csvSampleList.get(i).getwifiSpotList().size());
					lineTenNetworks=lineTenNetworks+ ","+csvSampleList.get(i).getwifiSpotList().get(j).getSsid()+","+csvSampleList.get(i).getwifiSpotList().get(j).getMac()+","+csvSampleList.get(i).getwifiSpotList().get(j).getFrequncy()+","+csvSampleList.get(i).getwifiSpotList().get(j).getSignal();
					
				}	
				bw.write(line);
				bw.write(lineTenNetworks);
				lineTenNetworks="";
				bw.write("\n");

				}

			
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (networksCSV != null)
					networksCSV.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
}