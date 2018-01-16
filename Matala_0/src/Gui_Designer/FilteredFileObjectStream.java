package Gui_Designer;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JTextField;
import Filters.Condition;

import javax.swing.JFrame;

import Filters.FilterObject;

public class FilteredFileObjectStream {

	private JFrame frame;

	public static void ExportObject(String filepath) throws ClassNotFoundException, IOException{
        
	     //   FilterObject f = new FilterObject(Gui_Data.filterp );

	        // save the object to file
	        FileOutputStream fos = null;
	        ObjectOutputStream out = null;
	        try {
	            fos = new FileOutputStream(filepath);
	            out = new ObjectOutputStream(fos);
	            out.writeObject(Gui_Data.filterp);

	            out.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        // read the object from file
	        // save the object to file
	        FileInputStream fis = null;
	        ObjectInputStream in = null;
	       
	           
					fis = new FileInputStream(filepath);
					in = new ObjectInputStream(fis);
		            Gui_Data.filterp = (FilterObject) in.readObject();
		            in.close();
	            
	            System.out.println("Done");
	       
	        //System.out.println(Gui_Data.filterp.toString());
	        System.out.println("aaaa");

	    }
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilteredFileObjectStream window = new FilteredFileObjectStream();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FilteredFileObjectStream() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
