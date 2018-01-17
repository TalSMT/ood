package Threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class Modifications {

	public Modifications() {
		// TODO Auto-generated constructor stub
	}

	public static int numOfFilesInInputFolder;
	public static long lastModifiedNUM;
	public static File file1=new File ("C:\\matala\\INPUT\\AVAVAV.csv");
	
	/**
	 * This function return if there was a change in a given file
	 * @see https://www.mkyong.com/java/how-to-get-the-file-last-modified-date-in-java/
	 * @param file
	 * @return true if there has been a change in file
	 */
	public static boolean lastModifiedFileUpdate (File file)
	{
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		long tempNum= file.lastModified();
		System.out.println("tempNum "+tempNum);
		if (lastModifiedNUM==tempNum)
			return false;
		lastModifiedNUM=tempNum;
		return true;
	}
	
	/**
	 * This function return if there has been a change in the number of files in a given folder
	 * @param folderOfCsvFiles
	 * @return false if there was no change ,and true if there was.
	 */
	public static boolean numOfFilesInFolderChanged (File folderOfCsvFiles)
	{
		int num=numOfFilesInFolder(folderOfCsvFiles);
		if (numOfFilesInInputFolder==num)
			return false;
		numOfFilesInInputFolder=num;
		return true;
		
	}	
	
	/**
	 * This function return the number of the files in the input folder
	 * @param folderOfCsvFiles
	 * @return number of files in a given folder.
	 */
	public static int numOfFilesInFolder (File folderOfCsvFiles)
	{
		File[] arrayOfCsvFiles = folderOfCsvFiles.listFiles();
		System.out.println(arrayOfCsvFiles.length);
		return arrayOfCsvFiles.length;
	}
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		File folderOfCsvFiles = new File("C:\\matala\\INPUT");
		numOfFilesInFolder (folderOfCsvFiles);
		lastModifiedFileUpdate(file1);
	}
	
	
	
	
}
