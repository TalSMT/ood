package ReadAndWriteFiles;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

public class WriteMacLocationCSV {

	
	public static void  writeCsvFile(ArrayList<macSamlpe> csvMacSample) throws FileNotFoundException { //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
		BufferedWriter bw = null; // https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/?utm_source=mkyong&utm_medium=author&utm_campaign=related-post&utm_content=6
		FileWriter networksCSV = null;
		try {

			//String headline = " time, id, lat,  lon,  alt, wifi_network, ssid1, mac1, frequncy1, signal1, ssid2, mac2, frequncy2, signal2, ssid3, mac3, frequncy3, signal3, ssid4, mac4, frequncy4, signal4, ssid5, mac5, frequncy5, signal5, ssid6, mac6, frequncy6, signal6, ssid7, mac7, frequncy7, signal7, ssid8, mac8, frequncy8, signal8, ssid9, mac9, frequncy9, signal9, ssid10, mac10, frequncy10, signal10\n";
			networksCSV = new FileWriter("C:\\matala2\\OUTPUT_ALGO1\\MacAndLocationCsv.csv"); 
			String line, lineTenNetworks="";
			bw = new BufferedWriter(networksCSV);
			//bw.write(headline);
			for (int i = 0; i < csvMacSample.size(); i++) {
				line= csvMacSample.get(i).getMac()+","+csvMacSample.get(i).getLat()+","+csvMacSample.get(i).getLon()+","+csvMacSample.get(i).getAlt()+","+csvMacSample.get(i).getSignal();
				
				
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
}
