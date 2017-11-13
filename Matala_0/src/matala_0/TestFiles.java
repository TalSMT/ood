package matala_0;

//https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class TestFiles {

	public static void main(String[] args) {


		String csvFile = "C:\\WigleWifi_20171028203300.csv";
		BufferedReader br = null;
		String sCurrentLine = "";
		String cvsSplitBy = ",";
		NetWorks network;

		//���� ����� �� ����� ���� ������
		ArrayList<NetWorks> maaShelNetwork=new ArrayList<>();
		String id="a";
		try {

			br = new BufferedReader(new FileReader(csvFile));

			//����� ��� ������ �� �� ���� �������
			int counter=0;
			while ((sCurrentLine = br.readLine()) != null) {
				counter++;
				// use comma as separator
				String[] Lines = sCurrentLine.split(cvsSplitBy);
				//	String wifi_networks=(String) Lines[2];
				// ����� �� ������ ����
				if(counter<2){
					id=Lines[2];

					System.out.println(id);
				}
				//���� �� ��� ������ �������� ������� �� ������� ����
				else if(counter>2)
				{
					String date=Lines[3].substring(0, 10);
					System.out.println(date);
					String hour=Lines[3].substring(11);
					String time= date+" "+hour;
					double lat=Double.parseDouble(Lines[6]);
					double lon=Double.parseDouble(Lines[7]);
					System.out.println(time);
					int alt = Integer.parseInt(Lines[8]);
					String wifi_networks=Lines[2]; 
					String ssid= Lines[1];
					String mac=Lines[0];
					double frequncy=Double.parseDouble(Lines[4]);
					int signal=Integer.parseInt(Lines[5]);
					//System.out.println("  "+Lines[0]+" "+Lines[1]+"  "+Lines[2]+"  "+Lines[3]);

					//���� ��� ���� ������- ���� ���� ������ ����� �� ������ ��������
					network= new NetWorks(time, lat, lon, alt, wifi_networks, ssid, mac, frequncy, signal);
					maaShelNetwork.add(network);
					//System.out.println(maaShelNetwork);

				}
				// System.out.println(lines[0]);

				//����� ������ ���������� ����
				for (int i = 0; i < maaShelNetwork.size(); i++) {
					maaShelNetwork.get(i).setId(id);
				}


			}




			//����� �� ���� ������ ��� ��� �������
			/*		<NetWorks> mesunanLefiZman=new ArrayList<>();
			mesunanLefiZman=FilterByTime(maaShelNetwork,"28/10/2017 20:31");
			for (int i=0; i<mesunanLefiZman.size();i++)
			{
				System.out.println(mesunanLefiZman.get(i).getTime());
			}
			//����� �� ���� ������ ��� ����� �������
			ArrayList<NetWorks> mesunanLefiMikum=new ArrayList<>();
			mesunanLefiMikum=FilterByLocation(maaShelNetwork, maaShelNetwork.get(5).getLat(), maaShelNetwork.get(5).getLon());
			for (int i=0; i<mesunanLefiMikum.size();i++)
			{
				System.out.println(mesunanLefiMikum.get(i).getLat());
			}*/


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}



		//����� �����
		BufferedWriter bw = null;    //https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/?utm_source=mkyong&utm_medium=author&utm_campaign=related-post&utm_content=6
		FileWriter networksCSV = null;

		//���� �������� ����� ���� �����
		Set<String> hashDifferentTimes = new HashSet<>();//���� ����� ����� �� ������ ������

		ArrayList<String> differentTimes = new ArrayList<>();//����� �� �������� ����� ����� �� ������ �� ���� ������
		//���� �� ����� ������� ���� ������ �� �� �������� - ���� ����� ����� �� ������ ����� ������ ����� ��� ������� ���
		for (int i=0; i<maaShelNetwork.size();i++)
		{
			hashDifferentTimes.add(maaShelNetwork.get(i).getTime());
		}
		//����� ����� �������� �� �� �������� ������ �� ���
		differentTimes.addAll(hashDifferentTimes);



		//����� ����� ����� - �����
		for (int i=0; i<differentTimes.size();i++)
		{
			System.out.println("aasdda"+differentTimes.get(i));
		}


		//����� ����� ���� �� ������� ����� ���� ��� ������ ����� �� ����� ��� ����� ���� ��� 10 ������ ������ ���
		//ArrayList<NetWorks> maaShelNetworkMesudar=new ArrayList<>();



		System.out.println("tttt");



		//����� �� ���� ������ ��� 10 ����� �� ������ ��� ��� �������
		/*	ArrayList<NetWorks> mesunanLefiTop10Signals=new ArrayList<>();
		mesunanLefiTop10Signals=FilterByTop10WiFiSignal(maaShelNetwork);
		for (int i=0; i<mesunanLefiTop10Signals.size();i++)
		{
			System.out.print(mesunanLefiTop10Signals.get(i).getSignal()+" ");
		}*/

		try {


			String headline = " time, id, lat,  lon,  alt, wifi_network, ssid1, mac1, frequncy1, signal1, ssid2, mac2, frequncy2, signal2, ssid3, mac3, frequncy3, signal3, ssid4, mac4, frequncy4, signal4, ssid5, mac5, frequncy5, signal5, ssid6, mac6, frequncy6, signal6, ssid7, mac7, frequncy7, signal7, ssid8, mac8, frequncy8, signal8, ssid9, mac9, frequncy9, signal9, ssid10, mac10, frequncy10, signal10\n";
			//      NetWorks contain= network;
			networksCSV = new FileWriter("C:\\matala\\NetWorks.csv"); //����� ������ ��� ����� ���� 
			bw = new BufferedWriter(networksCSV);
			bw.write(headline);
			//		bw.write();
			String shura;

			//����� ����� ���� �� ������� ����� ���� ��� ������ ����� �� ����� ��� ����� ���� ��� 10 ������ ������ ���
			ArrayList<NetWorks> maaShelNetworkMesudar=new ArrayList<>();
			System.out.println("rrrr"+differentTimes.size());
			//����� �������� ���� ����� �� 10 ������
			String [] info=new String [10];
			//����� ����� ����� �� �� ������ ������ ����� �� ���
			int reshatotbeotozman=0;
			for (int i=0;i<differentTimes.size();i++)
			{
				maaShelNetworkMesudar=FilterByTime(maaShelNetwork, differentTimes.get(i));	
				reshatotbeotozman=maaShelNetworkMesudar.size();
				System.out.println("aaaaaakkkkakakakakak"+reshatotbeotozman);
				maaShelNetworkMesudar=FilterByTop10WiFiSignal(maaShelNetworkMesudar);


				for (int k=0;k<maaShelNetworkMesudar.size();k++)
					System.out.println(maaShelNetworkMesudar.get(k));

				for (int s=0;s<maaShelNetworkMesudar.size();s++)
				{
					info[s]=maaShelNetworkMesudar.get(s).getSsid()+", "+maaShelNetworkMesudar.get(s).getMac()+", "+maaShelNetworkMesudar.get(s).getFrequncy()+", "+maaShelNetworkMesudar.get(s).getSignal();
					System.out.println("bb"+info[s]);
				}



				shura= differentTimes.get(i)+", "+maaShelNetworkMesudar.get(0).getId()+", "+maaShelNetworkMesudar.get(0).getLat()+", "+maaShelNetworkMesudar.get(0).getLon()+", "+maaShelNetworkMesudar.get(0).getAlt()+", "+reshatotbeotozman+", "+info[0]+", "+info[1]+", "+info[2]+", "+info[3]+", "+info[4]+", "+info[5]+", "+info[6]+", "+info[7]+", "+info[8]+", "+info[9]+"\n";
				//shura= mesunanLefiTop10Signals.get(i).getTime()+", "+mesunanLefiTop10Signals.get(i).getId()+", "+mesunanLefiTop10Signals.get(i).getLat()+", "+mesunanLefiTop10Signals.get(i).getLon()+", "+mesunanLefiTop10Signals.get(i).getAlt()+", "+mesunanLefiTop10Signals.get(i).getWifi_networks()+", "+mesunanLefiTop10Signals.get(i).getSsid()+", "+mesunanLefiTop10Signals.get(i).getMac()+", "+mesunanLefiTop10Signals.get(i).getFrequncy()+", "+mesunanLefiTop10Signals.get(i).getSignal()+"\n";
				//System.out.println(shura);
				bw.write(shura);
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

		/*
		 * <?xml version="1.0" encoding="utf-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2">
  <Placemark>
    <name>����� �� ����</name>
    <description>�� ����� ������ ���� �����������.</description>
    <Point>
      <coordinates>35.21092111,32.10437109</coordinates>
    </Point>
  </Placemark>
</kml>
		 */




		//���� KML

		//����� � �� �� ��
		String NEWcsvFile = "C:\\matala\\NetWorks.csv";
		BufferedReader brNEW = null;
		//String sCurrentLine = "";
		//	String cvsSplitBy = ",";
		//���� �� ��
		BufferedWriter bw2 = null;    //https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/?utm_source=mkyong&utm_medium=author&utm_campaign=related-post&utm_content=6
		FileWriter networksKML = null;


		try {

			brNEW = new BufferedReader(new FileReader(NEWcsvFile));

			//����� ��� ������ �� �� ���� �������
			int counterNEW=0;
			networksKML = new FileWriter("C:\\matala\\NetWorksKML.kml");  
			bw2 = new BufferedWriter(networksKML);
			bw2.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");// https://stackoverflow.com/questions/18725039/java-create-a-kml-file-and-insert-elements-in-existing-file
			String kmlelement;//����� �� �����
			int bstSignalID=0,max=0;

			while ((sCurrentLine = brNEW.readLine()) != null) {

				counterNEW++;
				// use comma as separator
				String[] LinesNEW = sCurrentLine.split(cvsSplitBy);
				/*
						for (int i = 9; i < 45; i++) {
							{
								if ((i>8)&&(i%4==1))

							}
						}
				 */
				if(counterNEW>1)
				{
					//System.out.println("vvvvvaaaaaa"+LinesNEW[9]);
					/*
					int [] arrsig = new int [10];
					arrsig[0]=Integer.parseInt(LinesNEW[9]);
					arrsig[1]=Integer.parseInt(LinesNEW[13]);
					arrsig[2]=Integer.parseInt(LinesNEW[17]);
					arrsig[3]=Integer.parseInt(LinesNEW[21]);
					arrsig[4]=Integer.parseInt(LinesNEW[25]);
					arrsig[5]=Integer.parseInt(LinesNEW[29]);
					arrsig[6]=Integer.parseInt(LinesNEW[33]);
					arrsig[7]=Integer.parseInt(LinesNEW[37]);
					arrsig[8]=Integer.parseInt(LinesNEW[41]);
					arrsig[9]=Integer.parseInt(LinesNEW[45]);

					for (int i = 0; i < arrsig.length; i++) {
						if(arrsig[i]>max)
						{
							max=arrsig[i];
							bstSignalID=i;
						}
					}
					
					*/
				//	System.out.println("wwwwwww"+(bstSignalID-3)+9);

					//Arrays.sort(arrsig);
					//bstSignal=arrsig[9];
					//	bstSignal=Math.max(LinesNEW[9], LinesNEW[13],LinesNEW[17],LinesNEW[21],LinesNEW[25],LinesNEW[29],LinesNEW[33],LinesNEW[37],LinesNEW[41],LinesNEW[45]);
				}

				kmlelement ="\t<Placemark>\n" +
	                            "\t<name>Simple placemark</name>\n" +
	                            "\t<description>"+LinesNEW[9]+"</description>\n" +
	                            "\t<Point>\n" +
	                            "\t\t<coordinates>"+LinesNEW[3]+","+LinesNEW[2]+","+LinesNEW[4]+ "</coordinates>\n" +
	                            "\t</Point>\n" +
	                            "\t</Placemark>\n";

				bw2.write(kmlelement);
				

				
				
					}
				 
			String kmlend = "</kml>";
			bw2.write(kmlend);
				//		bw.write();


				System.out.println("Done");
			
			
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw2 != null)
					bw2.close();

				if (networksKML != null)
					networksKML.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}





	}



	//���� ���� ������ �� ���� ������� ����� ���� ������� ���� ��� �� ��� ����� 10 ������ ����� ��� �����
	public static ArrayList<NetWorks> FilterByTop10WiFiSignal(ArrayList<NetWorks> mekori){
		//���� ���� ����� ����� ����� �� ������ ��������
		ArrayList<NetWorks> mesunan=new ArrayList<>();
		//���� ���� �� ������ ������ ����� �� �� ������ 
		int[] signals;
		if (mekori.size()>10)
			signals=new int[mekori.size()];
		else 
			signals=new int[11];


		for (int i=0; i<mekori.size();i++){
			signals[i]=mekori.get(i).getSignal();
			System.out.println();
		}
		for (int i=0; i<signals.length;i++)
			System.out.print("signals"+signals[i]+",");

		//����� ����
		Arrays.sort(signals);
		//System.out.println("sig"+signals);
		for (int i=0; i<signals.length;i++)
			System.out.println(signals[i]);

		for( int i=0;i<mekori.size();i++)
		{
			if (mekori.get(i).getSignal()>signals[signals.length-11])
				mesunan.add(mekori.get(i));
		}
		return mesunan;

	}

	//���� ���� ����� ������ �� ��� ��� ���� �������� ���� ���� ���� �� ����� �������� ������ ������ ���� ������� �����
	public static ArrayList<NetWorks> FilterByTime(ArrayList<NetWorks> mekori, String time){
		//���� ���� ����� ����� ����� �� ������ ��������
		ArrayList<NetWorks> mesunan=new ArrayList<>();
		//����� ������ ��� �� ����� ������ ����� ����� ����� �� ��� �� ���� ��� ��� �����, �� ������ �� ��� �� ����� ����� ����
		for (int i=0; i<mekori.size();i++)
		{
			if (mekori.get(i).getTime().equals(time))
			{
				System.out.println("��� ���");
				mesunan.add(mekori.get(i));
			}
		}

		return mesunan;
	}



	//���� ���� ����� ������ �� ����� ��� ���� �������� ���� ���� ���� �� ������ ��.� ������ ���� ������� �����

	/*public static ArrayList<NetWorks> FilterByLocation(ArrayList<NetWorks> mekori, double lat, double lon){
		//���� ���� ����� ����� ����� �� ������ ��������
		ArrayList<NetWorks> mesunan=new ArrayList<>();
		//����� ������ ��� �� ����� ������ ����� ����� ����� �� ��� �� ���� ���� ����� ��� �����, �� ������ �� �.� �� ����� ����� ����

		for (int i=0; i<mekori.size();i++)
		{
			if ((mekori.get(i).getLon()==lon)&&(mekori.get(i).getLat()==lat))

			{
				System.out.println("����� ���");
				mesunan.add(mekori.get(i));
			}
		}

		return mesunan;
	}*/

	//���� ���� ����� ������ �� ����� ��� ����� ���� ���� ���� �� ����� ����� ������ ���� ������� �����
	/*	public static ArrayList<NetWorks> FilterByID(ArrayList<NetWorks> mekori, String id){
		//���� ���� ����� ����� ����� �� ������ ��������
		ArrayList<NetWorks> mesunan=new ArrayList<>();
		//����� ������ ��� �� ����� ������ ����� ����� ����� �� ��� �� ���� ����� ��� �����, �� ������ �� ����� �� ����� ����� ����
		for (int i=0; i<mekori.size();i++)
		{
			if (mekori.get(i).getId().equals(id))
			{
				System.out.println("����� ���");
				mesunan.add(mekori.get(i));
			}
		}

		return mesunan;
	}*/



	/*static void testNCopies() {
		Collection<String> tenTimesAaa = new NCopies<String>(10, "aaa");
		for (String s: tenTimesAaa)
			System.out.print(s+" ");
	}
	 */

	/*
		static void testNCopies() {
			Condition<NetWorks> tenTimesAaa = new my<String>(10, "aaa");
			for (String s: tenTimesAaa)
				System.out.print(s+" ");
		}

	 */










	//���� ������� KML
	static void writeFileKML(Path theFile) {
		try {
			PrintWriter writer = new PrintWriter(Files.newBufferedWriter(theFile));
			writer.println("<kml>Hello world</kml>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
