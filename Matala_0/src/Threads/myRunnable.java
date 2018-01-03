package Threads;

import java.awt.event.ActionEvent;
import java.io.File;

import Gui_Designer.Gui_Data;

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
				
			}
			try{
				Thread.sleep((long)(Math.random()*1000));
			}catch (InterruptedException e){}
		}
	}
}


