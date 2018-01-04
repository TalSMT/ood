package Gui_Designer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ReadAndWriteFiles.ExportKML;
import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.WriteCombCsv;
import ReadAndWriteFiles.ReadWigleWifiFiles;
import Sample_Object.SampleOfWifi;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;

import DataBase.SetDataBase;
import Filters.FilterByPhoneId;

import javax.swing.event.CaretEvent;
import javax.swing.JTextPane;
import java.awt.Checkbox;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;
import java.awt.Canvas;

public class Gui_Data {

	public String getInputFoldePath() {
		return InputfoldePath;
	}
	//-----------------call the other classes---------------
	ReadWigleWifiFiles newCsv;// read the wigle files and write comb CSV
	
	
	
	
	
	//-----------------parameters-------------------------------
	String combName = "\\DataNetWorks.csv";
	String completeCombPath="";

	private JFrame frame;
	private JTextField folderPathInput_textField;
	static String InputfoldePath="";
	public static String OutputfoldePath="";
	public static String OutputfoldePathForKML="";
	public static String combPath="";
	private JTextField folderPathOutput_textField;
	private JTextField combPath_textField;
	
	
	//read folder with wigle csv files
	ArrayList<SampleOfWifi> processedCsvFile;
	//------read comb----
	public static ArrayList <SampleOfWifi> combData;
	//--------write kml file--------
	public static String kmlName= "\\KmlFile.kml";
	public static boolean deletedHeader=false;
	//--------data info--------------
	JLabel AnsNumberOfRecords;
	JLabel AnsNumOfNetworks;
	public static JTextField textFieldPhoneID;
	public static  JTextField textField_StartTime;
	public static  JTextField textField_EndTime;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	//--------filter--------------
	public static JCheckBox CheckBoxByPhoneID;
	static FilterByPhoneId filterID;
	public static JCheckBox CheckBoxByTime;
	public static JCheckBox CheckboxByLocation;
	private static JComboBox AndNotORcomboBox;
	private JButton buttonExportFilterComb;
	public static String Operand="";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public Gui_Data() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 192, 203));
		frame.setBounds(100, 100, 971, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblInsertPackagePath = new JLabel("Insert Input package path");
		lblInsertPackagePath.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		lblInsertPackagePath.setForeground(new Color(255, 0, 255));
		
		//----------------------------------------------folder path input text field------------------------------------
		folderPathInput_textField = new JTextField();
		folderPathInput_textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				InputfoldePath=folderPathInput_textField.getText();
				System.out.println("meeeeeeeeeeeeeee11111111111111111111");

			}
		});
		//
		//----------------------------------------------ok button for read folder with wigle csv files---------------------
		folderPathInput_textField.setColumns(10);
		JButton btnReadFiles = new JButton("read files");
		btnReadFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((InputfoldePath!="")&&(OutputfoldePath!=""))
				{
					System.out.println(InputfoldePath);

					try {
						
						frame.getContentPane().setBackground(new Color(0, 0, 0));
						// newCsv = new csvToNewCsv(InputfoldePath);
						
						File folderOfCsvFiles = new File(InputfoldePath);
						if (folderOfCsvFiles.canRead()) {
							 processedCsvFile = ReadWigleWifiFiles.readCsvFile(folderOfCsvFiles);
							 OutputfoldePath= OutputfoldePath+combName;
						combPath_textField.setText(OutputfoldePath);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		
		btnReadFiles.setForeground(new Color(138, 43, 226));
		//----------------------------------------------folder output path text field------------------------------------
		JLabel lblInsertOutputPackage = new JLabel("Insert output package path");
		lblInsertOutputPackage.setForeground(Color.MAGENTA);
		lblInsertOutputPackage.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		folderPathOutput_textField = new JTextField();
		folderPathOutput_textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				OutputfoldePath=folderPathOutput_textField.getText();
				OutputfoldePathForKML=folderPathOutput_textField.getText();
			}
		});
		folderPathOutput_textField.setColumns(10);
		
		//----------------------------------------------comb csv input path text field------------------------------------

		JLabel lblInsertCombCsv = new JLabel("Insert comb csv path");
		lblInsertCombCsv.setForeground(Color.MAGENTA);
		lblInsertCombCsv.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		combPath_textField = new JTextField();
		//if((OutputfoldePath!="")&&(combPath==""))
		System.out.println("kffffffffffffffffffffffffffffffffffff");

		
		combPath_textField.addCaretListener(new CaretListener() {
			
			//------------------------------------------------------------------------------------
			public void caretUpdate(CaretEvent e) {
				combPath=combPath_textField.getText();
				
			}
		});
		combPath_textField.setColumns(10);
		
		
		//----------------------------------------------ok button for read comb---------------------
		JButton btnOkcsvCOMB = new JButton("read comb");
		btnOkcsvCOMB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (combPath!="")
				{
					combData=ReadCombCsv.readCsvCombwithHeaders(combPath);
					AnsNumberOfRecords.setText(""+combData.size());
					AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(combData));
				}

				
			}
		});
		btnOkcsvCOMB.setForeground(new Color(138, 43, 226));
		
		//---------------------------------delete database button --------------------------------------------
		JButton btnDeleteDatabase = new JButton("delete database");
		btnDeleteDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletedHeader=true;
				combData=SetDataBase.deleteCombData(combData);
				AnsNumberOfRecords.setText(""+combData.size());
				AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(combData));

			}
		});
		
		//-----------------update csvfiles ----------------------------------
		JButton btnUpdateDatabase = new JButton("update database");
		btnUpdateDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					deletedHeader=false;
					WriteCombCsv.writeCsvFile(combData);
					AnsNumberOfRecords.setText(""+combData.size());
					AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(combData));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//----------------------------export comb csv--------------------------------------------------------------
		JButton btnbtnOkInputPath = new JButton("export comb");
		btnbtnOkInputPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deletedHeader=false;
					WriteCombCsv.writeCsvFile(processedCsvFile);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
		});
		btnbtnOkInputPath.setForeground(new Color(138, 43, 226));
		
		JButton buttonExportKML = new JButton("export KML");
		buttonExportKML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if(!combData.isEmpty())
					ExportKML.writeKMLFile(combData);
			}
		});
		buttonExportKML.setForeground(new Color(138, 43, 226));
		
		JLabel lblNumberOfRecords = new JLabel("Number of records");
		lblNumberOfRecords.setForeground(new Color(138, 43, 226));
		lblNumberOfRecords.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		AnsNumberOfRecords = new JLabel("0");
		AnsNumberOfRecords.setForeground(new Color(138, 43, 226));
		AnsNumberOfRecords.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		JLabel lblNumberOfDiff = new JLabel("number of different \r\nwifi networks");
		lblNumberOfDiff.setForeground(new Color(138, 43, 226));
		lblNumberOfDiff.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		AnsNumOfNetworks = new JLabel("0");
		AnsNumOfNetworks.setForeground(new Color(138, 43, 226));
		AnsNumOfNetworks.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		 CheckBoxByPhoneID = new JCheckBox("by Phone ID");
		
		 CheckBoxByTime = new JCheckBox("by Time");
		
		textFieldPhoneID = new JTextField();
		textFieldPhoneID.setColumns(10);
		
		textField_StartTime = new JTextField();
		textField_StartTime.setColumns(10);
		
		textField_EndTime = new JTextField();
		textField_EndTime.setColumns(10);
		
		 CheckboxByLocation = new JCheckBox("by Location");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		AndNotORcomboBox = new JComboBox();
		AndNotORcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Operand=(String)AndNotORcomboBox.getSelectedItem();
				System.out.println(Operand);
			}
		});
		AndNotORcomboBox.setModel(new DefaultComboBoxModel(new String[] {"AND", "NOT", "OR"}));
		AndNotORcomboBox.setToolTipText("");
		//---------------------------------------------export filtered comb database-----------------------------
		buttonExportFilterComb = new JButton("export database filtered");
		buttonExportFilterComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combData= filterID(combData);
				try {
					WriteCombCsv.writeCsvFile(combData);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AnsNumberOfRecords.setText(""+combData.size());
				AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(combData));
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(btnDeleteDatabase)
					.addGap(51)
					.addComponent(btnUpdateDatabase, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(buttonExportKML, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(422, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInsertPackagePath, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInsertOutputPackage, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(folderPathInput_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(folderPathOutput_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnbtnOkInputPath, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInsertCombCsv, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(combPath_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
							.addGap(73)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnOkcsvCOMB, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
									.addGap(24))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnReadFiles, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonExportFilterComb)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNumberOfDiff, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(AnsNumOfNetworks, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNumberOfRecords, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(AnsNumberOfRecords, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
									.addGap(64)
									.addComponent(AndNotORcomboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
									.addComponent(textField_EndTime, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addGap(95))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addComponent(CheckBoxByTime, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(textField_StartTime, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addGap(93))))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(602, Short.MAX_VALUE)
					.addComponent(CheckBoxByPhoneID)
					.addGap(13)
					.addComponent(textFieldPhoneID, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(92))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(600, Short.MAX_VALUE)
					.addComponent(CheckboxByLocation, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(95))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(697, Short.MAX_VALUE)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(95))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(697, Short.MAX_VALUE)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(95))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertPackagePath)
						.addComponent(folderPathInput_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReadFiles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertOutputPackage, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(folderPathOutput_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnbtnOkInputPath))
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertCombCsv, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(combPath_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOkcsvCOMB))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDeleteDatabase)
								.addComponent(btnUpdateDatabase)
								.addComponent(buttonExportKML))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumberOfRecords, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(AnsNumberOfRecords, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumberOfDiff, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(AnsNumOfNetworks, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(CheckBoxByPhoneID)
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(CheckBoxByTime)
								.addComponent(AndNotORcomboBox, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(textFieldPhoneID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(textField_StartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(textField_EndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(15)
									.addComponent(CheckboxByLocation))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(12)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(buttonExportFilterComb)))
					.addGap(80))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
	
	public static ArrayList <SampleOfWifi> filterID ( ArrayList <SampleOfWifi> combData)
	{
		ArrayList <SampleOfWifi> filtered=combData;

		System.out.println("is correct"+ filtered.get(3).getPhoneId());
		//_________________filter by phone ID___________
		if (Gui_Data.CheckBoxByPhoneID.isSelected()){
			if (!textFieldPhoneID.getText().isEmpty()){
				filterID=new FilterByPhoneId(textFieldPhoneID.getText());
				filtered=SetDataBase.filter(combData,filterID);
			}
		}
		//_________________filter by time___________
				if (Gui_Data.CheckBoxByTime.isSelected()){
					if ((!textField_StartTime.getText().isEmpty())&&(!textField_EndTime.getText().isEmpty())){
						//filterTime=new FilterByPhoneId(textFieldPhoneID.getText());
						filtered=SetDataBase.filter(combData,filterID);
					}
				}
		
		//_________________filter by location___________
		if (Gui_Data.CheckboxByLocation.isSelected()){
			if (!textFieldPhoneID.getText().isEmpty()){
				filterID=new FilterByPhoneId(textFieldPhoneID.getText());
				filtered=SetDataBase.filter(combData,filterID);
			}
		}
		
		
	filtered=SetDataBase.filterNOT(combData, filtered);
	return filtered;
	}
}
