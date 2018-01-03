package Threads;


import java.awt.EventQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Gui_Designer.Gui_Data;

public class SimpleThreadPool {

    public static void main(String[] args) {
        
    	myRunnable t1 = new myRunnable ("t1");

		Thread t11 = new Thread(t1);
		Modifications.numOfFilesInInputFolder=2;
		Modifications.lastModifiedNUM=2;
		
		t11.start();
     
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Gui_Data window = new Gui_Data();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
		
		
        System.out.println("Finished all threads");
    }
    
    
    
  
    
    
    
    
}