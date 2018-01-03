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
import Sample_Object.macSamlpe;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;

import Algoritems.CallToAlgo1;
import Algoritems.CallToAlgo2;
import Algoritems.Constant;
import DataBase.SetDataBase;
import Filters.FilterByLocation;
import Filters.FilterByPhoneId;
import Filters.FilterByTime;

import javax.swing.event.CaretEvent;
import javax.swing.JTextPane;
import java.awt.Checkbox;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.sql.rowset.Joinable;
import javax.swing.DefaultComboBoxModel;
import java.awt.Canvas;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
public class Gui_Data {

	public String getInputFoldePath() {
		return InputfoldePath;
	}
	//-----------------call the other classes---------------
	ReadWigleWifiFiles newCsv;// read the wigle files and write comb CSV
	
	
	
	
	
	//-----------------parameters-------------------------------
	String combName = "\\DataNetWorks.csv";
	String combFilterdName = "\\FilterdDataNetWorks.csv";

	String completeCombPath="";

	public JFrame frame;
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
	public static JTextField textField_lat;
	public static JTextField textField_lon;
	public static JTextField textField_radios;
	//--------filter--------------
	public static JCheckBox CheckBoxByPhoneID;
	static FilterByPhoneId filterID;
	static FilterByTime filterTIME;
	static FilterByLocation filterLocation;

	public static JCheckBox CheckBoxByTime;
	public static JCheckBox CheckboxByLocation;
	private static JComboBox AndNotORcomboBox;
	private JButton buttonExportFilterComb;
	public static String Operand="";
	//-----Algo 1 - insert mac and get the mac location
	public static JTextField txtMac;
	public static CallToAlgo1 algo1;
	public static JLabel label_macLocation;
	//-----Algo 2 - insert 3 pair of mac and signal and get the Sampler location
	public static JTextField txtInsrertMac1;
	public static JTextField txtInsrertSignal1;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField txtInsrertMac2;
	public static JTextField txtInsrertSignal2;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField txtInsrertMac3;
	public static JTextField txtInsrertSignal3;
	public static JLabel label_SamplerLocation;
	public static CallToAlgo2 algo2;
	public static macSamlpe mac1;
	public static macSamlpe mac2;
	public static macSamlpe mac3;
	public static JTextField txtInsrertStringOfCombLine;
	public static SampleOfWifi smp;
	
		
	
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
		frame.setBounds(100, 100, 1225, 1072);
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
						//JOptionPane.showMessageDialog(null, "Your message goes here!","Message", JOptionPane.ERROR_MESSAGE);
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
					Constant.setCsvCombPath(combPath);
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		
		textField_lat = new JTextField();
		textField_lat.setColumns(10);
		
		textField_lon = new JTextField();
		textField_lon.setColumns(10);
		
		textField_radios = new JTextField();
		textField_radios.setColumns(10);
		
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
					String tempoutOutPut=OutputfoldePath;
					OutputfoldePath=folderPathOutput_textField.getText()+combFilterdName;
					WriteCombCsv.writeCsvFile(combData);
					OutputfoldePath=tempoutOutPut;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AnsNumberOfRecords.setText(""+combData.size());
				AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(combData));
			}
		});
		//-------------------------------------------call algo 1-------------------------------------
		JButton btn_CallAlgo1 = new JButton("Mac");
		btn_CallAlgo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!txtMac.getText().isEmpty()){
					try {
						algo1= new CallToAlgo1(txtMac.getText());
						label_macLocation.setText("LOCTAION: "+algo1.averg.getLat()+" , "+algo1.averg.getLon()+" alt: "+algo1.averg.getAlt());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		});
		
		txtMac = new JTextField();
		txtMac.setText("Insert Mac or comb csv");
		txtMac.setColumns(10);
		
		label_macLocation = new JLabel("___");
		label_macLocation.setForeground(Color.MAGENTA);
		label_macLocation.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		txtInsrertMac1 = new JTextField();
		txtInsrertMac1.setText("insrert mac1");
		txtInsrertMac1.setColumns(10);
		
		txtInsrertSignal1 = new JTextField();
		txtInsrertSignal1.setText("insrert signal1");
		txtInsrertSignal1.setColumns(10);
		
		textField = new JTextField();
		textField.setText("insrert mac1");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("insrert signal1");
		textField_1.setColumns(10);
		
		txtInsrertMac2 = new JTextField();
		txtInsrertMac2.setText("insrert mac2");
		txtInsrertMac2.setColumns(10);
		
		txtInsrertSignal2 = new JTextField();
		txtInsrertSignal2.setText("insrert signal2");
		txtInsrertSignal2.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("insrert mac2");
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("insrert signal2");
		textField_3.setColumns(10);
		
		txtInsrertMac3 = new JTextField();
		txtInsrertMac3.setText("insrert mac3");
		txtInsrertMac3.setColumns(10);
		
		txtInsrertSignal3 = new JTextField();
		txtInsrertSignal3.setText("insrert signal3");
		txtInsrertSignal3.setColumns(10);
		//-------------------------------------------call algo 2-------------------------------------
		JButton btnSubmit = new JButton("submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((!txtInsrertMac1.getText().isEmpty())&&(!txtInsrertMac2.getText().isEmpty())&&(!txtInsrertMac3.getText().isEmpty())&&(!txtInsrertSignal1.getText().isEmpty())&&(!txtInsrertSignal2.getText().isEmpty())&&(!txtInsrertSignal3.getText().isEmpty())){
					
					double signal1D = Double.parseDouble(txtInsrertSignal1.getText());
					mac1= new macSamlpe(signal1D, 0, 0, 0);
					mac1.setMac(txtInsrertMac1.getText());
					double signal2D = Double.parseDouble(txtInsrertSignal2.getText());
					mac2= new macSamlpe(signal2D, 0, 0, 0);
					mac2.setMac(txtInsrertMac2.getText());
					double signal3D = Double.parseDouble(txtInsrertSignal2.getText());
					mac3= new macSamlpe(signal3D, 0, 0, 0);
					mac3.setMac(txtInsrertMac3.getText());
					try {
						algo2= new CallToAlgo2(mac1, mac2, mac3);
						label_SamplerLocation.setText("LOCTAION: "+algo2.thelocation.getLat()+" , "+algo2.thelocation.getLon()+" alt: "+algo2.thelocation.getAlt());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				else if ((!txtInsrertStringOfCombLine.getText().isEmpty()))
				{
					smp=CallToAlgo2.lineToSampleOfWifi(txtInsrertStringOfCombLine.getText());
					try {
						algo2= new CallToAlgo2(smp);
						label_SamplerLocation.setText("LOCTAION: "+algo2.thelocation.getLat()+" , "+algo2.thelocation.getLon()+" alt: "+algo2.thelocation.getAlt());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
			}
		});
		
		label_SamplerLocation = new JLabel("___");
		label_SamplerLocation.setForeground(Color.MAGENTA);
		label_SamplerLocation.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		txtInsrertStringOfCombLine = new JTextField();
		txtInsrertStringOfCombLine.setText("insrert String of comb line");
		txtInsrertStringOfCombLine.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(btnDeleteDatabase)
					.addGap(51)
					.addComponent(btnUpdateDatabase, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(buttonExportKML, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(640, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(textField_lon, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap(841, Short.MAX_VALUE)
										.addComponent(CheckBoxByPhoneID)
										.addGap(13))
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
														.addComponent(btnbtnOkInputPath, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblInsertCombCsv, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(combPath_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
												.addGap(73)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnOkcsvCOMB, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
														.addGap(24))
													.addComponent(btnReadFiles, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
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
												.addPreferredGap(ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(AndNotORcomboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
														.addGap(40)
														.addComponent(CheckBoxByTime, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
														.addGap(35))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(CheckboxByLocation, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
														.addGap(22)))))))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(textField_lat, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldPhoneID, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_StartTime, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_EndTime, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btn_CallAlgo1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(txtMac, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(547)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(buttonExportFilterComb)
								.addComponent(textField_radios, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))))
					.addGap(37))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_macLocation, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(txtInsrertMac2, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtInsrertSignal2, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(txtInsrertMac1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtInsrertSignal1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtInsrertStringOfCombLine, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtInsrertMac3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addComponent(txtInsrertSignal3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(166)
					.addComponent(label_SamplerLocation, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(489, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(CheckBoxByTime)
										.addComponent(AndNotORcomboBox, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(52)
									.addComponent(textFieldPhoneID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_StartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_lat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(CheckboxByLocation))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_lon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(308, Short.MAX_VALUE)
							.addComponent(textField_EndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(100)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_radios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonExportFilterComb)
						.addComponent(btn_CallAlgo1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label_macLocation, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtInsrertMac1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInsrertSignal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtInsrertSignal2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInsrertMac2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addComponent(txtInsrertMac3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(txtInsrertSignal3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(45)
					.addComponent(txtInsrertStringOfCombLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(label_SamplerLocation, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(335)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(778)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(109))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
	
	public static ArrayList <SampleOfWifi> filterID ( ArrayList <SampleOfWifi> combData)
	{
		ArrayList <SampleOfWifi> filtered=combData;
		
		if (Gui_Data.Operand.equals("OR"))
		{
			ArrayList <SampleOfWifi> specificFiltered1=combData;
			ArrayList <SampleOfWifi> specificFiltered2=combData;
			ArrayList <SampleOfWifi> specificFiltered3=combData;// he will contain the and operator
			ArrayList <SampleOfWifi> join=new ArrayList<>();

			if ((Gui_Data.CheckBoxByPhoneID.isSelected())&&(Gui_Data.CheckBoxByTime.isSelected()))
			{
				if (!textFieldPhoneID.getText().isEmpty()){
					filterID=new FilterByPhoneId(textFieldPhoneID.getText());
					specificFiltered1=SetDataBase.filter(combData,filterID);
				}
				if ((!textField_StartTime.getText().isEmpty())&&(!textField_EndTime.getText().isEmpty())){
					filterTIME=new FilterByTime(textField_StartTime.getText(),textField_EndTime.getText());
					specificFiltered2=SetDataBase.filter(filtered,filterTIME);
					specificFiltered3=SetDataBase.filter(specificFiltered1,filterTIME);

				}
				
			}
			
			if ((Gui_Data.CheckBoxByPhoneID.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected()))
			{
				if (!textFieldPhoneID.getText().isEmpty()){
					filterID=new FilterByPhoneId(textFieldPhoneID.getText());
					specificFiltered1=SetDataBase.filter(combData,filterID);
				}
				if ((!textField_lat.getText().isEmpty())&&(!textField_lon.getText().isEmpty())&&(!textField_radios.getText().isEmpty())){
					double lat = Double.parseDouble(textField_lat.getText());
					double lon = Double.parseDouble(textField_lon.getText());
					double radios = Double.parseDouble(textField_radios.getText());
					if ((!textField_lat.getText().isEmpty())&&(!textField_lon.getText().isEmpty())&&(!textField_radios.getText().isEmpty())){
						filterLocation=new FilterByLocation(lat,lon,0,radios);
						specificFiltered2=SetDataBase.filter(filtered,filterLocation);
						specificFiltered3=SetDataBase.filter(specificFiltered1,filterLocation);

					}
				}
			}
			
			if ((Gui_Data.CheckBoxByTime.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected()))
			{
				if ((!textField_StartTime.getText().isEmpty())&&(!textField_EndTime.getText().isEmpty())){
					filterTIME=new FilterByTime(textField_StartTime.getText(),textField_EndTime.getText());
					specificFiltered1=SetDataBase.filter(filtered,filterTIME);
				}
				if ((!textField_lat.getText().isEmpty())&&(!textField_lon.getText().isEmpty())&&(!textField_radios.getText().isEmpty())){
					double lat = Double.parseDouble(textField_lat.getText());
					double lon = Double.parseDouble(textField_lon.getText());
					double radios = Double.parseDouble(textField_radios.getText());
					if ((!textField_lat.getText().isEmpty())&&(!textField_lon.getText().isEmpty())&&(!textField_radios.getText().isEmpty())){
						filterLocation=new FilterByLocation(lat,lon,0,radios);
						specificFiltered2=SetDataBase.filter(filtered,filterLocation);
						specificFiltered3=SetDataBase.filter(specificFiltered1,filterLocation);

					}
				}
			}
			
			//we will add the specificFiltered2 to specificFiltered1
			for (int i = 0; i < specificFiltered2.size(); i++) {
				join.add(specificFiltered2.get(i));
			}
			for (int i = 0; i < specificFiltered1.size(); i++) {
				join.add(specificFiltered1.get(i));
			}
			
			for (int i = 0; i < specificFiltered3.size(); i++) {
				for (int j = 0; j < join.size(); j++) {
					if(specificFiltered3.get(i).equals(join.get(j)))
					{
						join.remove(j);
						j--;
						}
					
					
				}
			}
			filtered=join;
			
			

		}
		else
		{
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
							filterTIME=new FilterByTime(textField_StartTime.getText(),textField_EndTime.getText());
							filtered=SetDataBase.filter(filtered,filterTIME);
						}
					}
			
			//_________________filter by location___________
					
			if (Gui_Data.CheckboxByLocation.isSelected()){
				if ((!textField_lat.getText().isEmpty())&&(!textField_lon.getText().isEmpty())&&(!textField_radios.getText().isEmpty())){
					double lat = Double.parseDouble(textField_lat.getText());
					double lon = Double.parseDouble(textField_lon.getText());
					double radios = Double.parseDouble(textField_radios.getText());
					filterLocation=new FilterByLocation(lat,lon,0,radios);
					filtered=SetDataBase.filter(filtered,filterLocation);
				}
			}
		}
		
		
		
	filtered=SetDataBase.filterNOT(combData, filtered);
	return filtered;
	}
}