package Threads;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import DataBase.SetDataBase;
import Gui_Designer.Gui_Data;
import ReadAndWriteFiles.ReadWigleWifiFiles;

public class myRunnable implements Runnable{
	ActionEvent arg0;
	String name;
	File folderOfCsvFiles = new File("C:\\matala\\INPUT");

	myRunnable(String n)
	{
		name = n;
	}

	public void run()
	{
		for(;;){
			if( Modifications.numOfFilesInFolderChanged (folderOfCsvFiles)==true||Modifications.lastModifiedFileUpdate(folderOfCsvFiles)==true)
			{

				System.out.println("i love changes2");
				//change the sampleOfWifi list -processedCsvFile
				if ((Gui_Data.InputfoldePath!="")&&(Gui_Data.OutputfoldePath!=""))
				{

					try {

						// newCsv = new csvToNewCsv(InputfoldePath);
						//JOptionPane.showMessageDialog(null, "Your message goes here!","Message", JOptionPane.ERROR_MESSAGE);
						File folderOfCsvFiles = new File(Gui_Data.InputfoldePath);
						if (folderOfCsvFiles.canRead()) {
							// put a notice that here we will change the combData list and not our first list processedCsvFile
							Gui_Data.processedCsvFile=Gui_Data.combData; 
							Gui_Data.combData = ReadWigleWifiFiles.readCsvFile(folderOfCsvFiles);
							Gui_Data.AnsNumberOfRecords.setText(""+Gui_Data.combData.size());
							Gui_Data.AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(Gui_Data.combData));
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


				}
				
				//Gui_Data.updateInfo ();
				//change the Database infomation window
				//Gui_Data.AnsNumberOfRecords.setText(""+Gui_Data.combData.size());
				
				
				
				
				

			}
			
			//set sleep to the modification thread
			try{
				Thread.sleep((long)(Math.random()*1000));
			}catch (InterruptedException e){}
		}
	}
}


