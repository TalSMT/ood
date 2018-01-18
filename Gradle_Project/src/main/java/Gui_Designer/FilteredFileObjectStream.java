package Gui_Designer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextField;

import Filters.Condition;
import Filters.FilterObject;
/**
 * This class exports a filtered text file of an object received by the user
 * @authors Tal and Shaked
 *
 */
public class FilteredFileObjectStream {
	
	public static void ExportObject(String filepath){
		        
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
		        try {
		            fis = new FileInputStream(filepath);
		            in = new ObjectInputStream(fis);
		            Gui_Data.filterp = (FilterObject) in.readObject();
		            in.close();
		            System.out.println("Done");
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		        System.out.println(Gui_Data.filterp.toString());
		    }
}
