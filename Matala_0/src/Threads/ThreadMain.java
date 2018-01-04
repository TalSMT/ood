package Threads;


import java.awt.EventQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DataBase.SetDataBase;
import Gui_Designer.Gui_Data;

public class ThreadMain {

    public static void main(String[] args) {
        
    	MyRunnable t1 = new MyRunnable ("t1");

		Thread t11 = new Thread(t1);
		Modifications.numOfFilesInInputFolder=3;
		Modifications.lastModifiedNUM=2;
		
		t11.start();
     
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Gui_Data window = new Gui_Data();
					//change the Database info window
					Gui_Data.AnsNumberOfRecords.setText(""+Gui_Data.combData.size());
					Gui_Data.AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(Gui_Data.combData));

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
		
		
        System.out.println("Finished all threads");
    }
    
    
    
  
    
    
    
    
}