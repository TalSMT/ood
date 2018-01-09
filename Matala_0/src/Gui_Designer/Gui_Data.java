package Gui_Designer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ReadAndWriteFiles.ExportKML;
import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.WriteCombCsv;
import ReadAndWriteFiles.ReadWigleWifiFiles;
import Sample_Object.SampleOfWifi;
import Sample_Object.macSamlpe;

import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
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
import Filters.FilterObject;

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
import java.awt.SystemColor;
import javax.swing.SwingConstants;
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
	private ImageIcon image1;
	private JTextField folderPathInput_textField;
	public static String InputfoldePath="";
	public static String OutputfoldePath="";
	public static String OutputfoldePathForKML="";
	public static String combPath="";
	private JTextField folderPathOutput_textField;
	private JTextField combPath_textField;
	
	
	//read folder with wigle csv files
	public static ArrayList<SampleOfWifi> processedCsvFile= new ArrayList<>();;//will use us also for the undo filter
	//------read comb----
	public static ArrayList <SampleOfWifi> combData= new ArrayList<>();
	//--------write kml file--------
	public static String kmlName= "\\KmlFile.kml";
	public static boolean deletedHeader=false;
	//--------data info--------------
	public static JLabel AnsNumberOfRecords;
	public static JLabel AnsNumOfNetworks;
	public static JTextField textFieldPhoneID;
	public static  JTextField txtInsertStartTime;
	public static  JTextField txtInsertEndTime;
	public static JTextField txtInsertLat;
	public static JTextField txtInsertLon;
	public static JTextField txtInsertRadios;
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
	//-----Filters-------------------------------
	public static String filter_Properties= " ";
	public static FilterObject filterp;
	private JButton btnFilterProperties;
	private JLabel lblFilter;
	public static JLabel label_Filter;
	private JButton btnSql;
		
	
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
		//image1=new ImageIcon(getClass().getResource("/black_background1.png"));
		
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		//frame.imageUpdate(image1, 0, 0, 0, 0, 0);
		frame.setBounds(100, 100, 1225, 1072);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblInsertPackagePath = new JLabel("Insert Input package path");
		lblInsertPackagePath.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblInsertPackagePath.setForeground(new Color(0, 255, 255));
		
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
		btnReadFiles.setBackground(new Color(0, 0, 0));
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
		
		btnReadFiles.setForeground(new Color(0, 255, 255));
		//----------------------------------------------folder output path text field------------------------------------
		JLabel lblInsertOutputPackage = new JLabel("Insert output package path");
		lblInsertOutputPackage.setForeground(new Color(0, 255, 255));
		lblInsertOutputPackage.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
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
		lblInsertCombCsv.setForeground(new Color(0, 255, 255));
		lblInsertCombCsv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
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
		btnOkcsvCOMB.setBackground(new Color(0, 0, 0));
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
		btnOkcsvCOMB.setForeground(new Color(0, 255, 255));
		
		//---------------------------------delete database button --------------------------------------------
		JButton btnDeleteDatabase = new JButton("delete database");
		btnDeleteDatabase.setForeground(new Color(0, 255, 255));
		btnDeleteDatabase.setBackground(new Color(0, 0, 0));
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
		btnUpdateDatabase.setForeground(new Color(0, 255, 255));
		btnUpdateDatabase.setBackground(new Color(0, 0, 0));
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
		btnbtnOkInputPath.setBackground(new Color(0, 0, 0));
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
		btnbtnOkInputPath.setForeground(new Color(0, 255, 255));
		
		JButton buttonExportKML = new JButton("export KML");
		buttonExportKML.setBackground(new Color(0, 0, 0));
		buttonExportKML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if(!combData.isEmpty())
					ExportKML.writeKMLFile(combData);
			}
		});
		buttonExportKML.setForeground(new Color(0, 255, 255));
		
		JLabel lblNumberOfRecords = new JLabel("Number of records");
		lblNumberOfRecords.setForeground(new Color(0, 255, 255));
		lblNumberOfRecords.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		AnsNumberOfRecords = new JLabel("0");
		AnsNumberOfRecords.setForeground(new Color(0, 255, 255));
		AnsNumberOfRecords.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		JLabel lblNumberOfDiff = new JLabel("number of different \r\nwifi networks");
		lblNumberOfDiff.setForeground(new Color(0, 255, 255));
		lblNumberOfDiff.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		AnsNumOfNetworks = new JLabel("0");
		AnsNumOfNetworks.setForeground(new Color(0, 255, 255));
		AnsNumOfNetworks.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		 CheckBoxByPhoneID = new JCheckBox("by Phone ID");
		
		 CheckBoxByTime = new JCheckBox("by Time");
		
		textFieldPhoneID = new JTextField();
		textFieldPhoneID.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPhoneID.setText("insert ID");
		textFieldPhoneID.setColumns(10);
		
		txtInsertStartTime = new JTextField();
		txtInsertStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertStartTime.setText("insert start time");
		txtInsertStartTime.setColumns(10);
		
		txtInsertEndTime = new JTextField();
		txtInsertEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertEndTime.setText("insert end time");
		txtInsertEndTime.setColumns(10);
		
		 CheckboxByLocation = new JCheckBox("by Location");
		
		txtInsertLat = new JTextField();
		txtInsertLat.setText("insert lat");
		txtInsertLat.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertLat.setColumns(10);
		
		txtInsertLon = new JTextField();
		txtInsertLon.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertLon.setText("insert lon");
		txtInsertLon.setColumns(10);
		
		txtInsertRadios = new JTextField();
		txtInsertRadios.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertRadios.setText("insert radios");
		txtInsertRadios.setColumns(10);
		
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
		buttonExportFilterComb.setForeground(new Color(0, 255, 255));
		buttonExportFilterComb.setBackground(new Color(0, 0, 0));
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
		btn_CallAlgo1.setForeground(new Color(0, 255, 255));
		btn_CallAlgo1.setBackground(new Color(0, 0, 0));
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
		label_macLocation.setForeground(new Color(0, 255, 255));
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
		btnSubmit.setForeground(new Color(0, 255, 255));
		btnSubmit.setBackground(new Color(0, 0, 0));
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
		label_SamplerLocation.setBackground(new Color(0, 0, 0));
		label_SamplerLocation.setForeground(new Color(0, 255, 255));
		label_SamplerLocation.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		txtInsrertStringOfCombLine = new JTextField();
		txtInsrertStringOfCombLine.setForeground(new Color(0, 255, 255));
		txtInsrertStringOfCombLine.setBackground(new Color(255, 255, 255));
		txtInsrertStringOfCombLine.setText("insrert String of comb line");
		txtInsrertStringOfCombLine.setColumns(10);
		
		btnFilterProperties = new JButton("Filter properties");
		btnFilterProperties.setForeground(new Color(0, 255, 255));
		btnFilterProperties.setBackground(new Color(0, 0, 0));
		btnFilterProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilteredFileObjectStream.ExportObject(Constant.filename);
				
			}
		});
		
		lblFilter = new JLabel("Filter:");
		lblFilter.setForeground(new Color(0, 255, 255));
		lblFilter.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		label_Filter = new JLabel("_________");
		label_Filter.setForeground(new Color(0, 255, 255));
		label_Filter.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		//----------------------------Undo Filter-------------------------
		JButton btnUndoFilter = new JButton("Undo Filter ");
		btnUndoFilter.setForeground(new Color(0, 255, 255));
		btnUndoFilter.setBackground(new Color(0, 0, 0));
		btnUndoFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				combData= processedCsvFile;
				Gui_Data.AnsNumberOfRecords.setText(""+Gui_Data.combData.size());
				Gui_Data.AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(Gui_Data.combData));
			}
		});
		
		btnSql = new JButton("SQL");
		btnSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				SQL_window sqWind= new SQL_window();
				sqWind.createSQL_Window();
			}
		});
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap(853, Short.MAX_VALUE)
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
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(folderPathOutput_textField)
												.addComponent(folderPathInput_textField, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblInsertCombCsv, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(combPath_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
											.addGap(73)
											.addComponent(btnOkcsvCOMB, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
											.addGap(24))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblNumberOfDiff, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(AnsNumOfNetworks, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblNumberOfRecords, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(AnsNumberOfRecords, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(37)
													.addComponent(btn_CallAlgo1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(txtMac, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(60)
													.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
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
															.addComponent(txtInsrertMac3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
															.addGap(18)
															.addComponent(txtInsrertSignal3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(CheckboxByLocation, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
															.addGap(22))
														.addGroup(groupLayout.createSequentialGroup()
															.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addComponent(buttonExportFilterComb)
																.addComponent(AndNotORcomboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
															.addGap(76)
															.addComponent(CheckBoxByTime, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
															.addGap(14))))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(111)
													.addComponent(lblFilter, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))))))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(label_macLocation, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
							.addGap(149)
							.addComponent(btnUndoFilter, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(195)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtInsertLon, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInsertLat, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInsertEndTime, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInsertStartTime, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInsertRadios, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnReadFiles, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFieldPhoneID, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnbtnOkInputPath, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
					.addGap(1116))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(215)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(131)
					.addComponent(txtInsrertStringOfCombLine, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(label_SamplerLocation, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addComponent(label_Filter, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(1142, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(626)
					.addComponent(btnFilterProperties, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1469, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(752)
					.addComponent(btnSql)
					.addContainerGap(1427, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertPackagePath)
						.addComponent(folderPathInput_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReadFiles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInsertOutputPackage, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(folderPathOutput_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInsertCombCsv, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(combPath_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOkcsvCOMB)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnbtnOkInputPath)))
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
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(CheckBoxByPhoneID)
								.addComponent(textFieldPhoneID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtInsertStartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtInsertEndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(CheckBoxByTime)
										.addComponent(AndNotORcomboBox, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))))))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(37)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btn_CallAlgo1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtMac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(15)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtInsertLat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(CheckboxByLocation))
									.addGap(18)
									.addComponent(txtInsertLon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(buttonExportFilterComb)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addComponent(btnFilterProperties)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtInsertRadios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_macLocation, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnUndoFilter)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(107)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(100)
									.addComponent(txtInsrertStringOfCombLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(89)
									.addComponent(label_SamplerLocation, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_Filter, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtInsrertMac1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtInsrertSignal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtInsrertSignal2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtInsrertMac2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(36)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtInsrertMac3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtInsrertSignal3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(60))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblFilter, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
									.addGap(106)))))
					.addGap(72)
					.addComponent(btnSql)
					.addGap(333)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(778)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
				if ((!txtInsertStartTime.getText().isEmpty())&&(!txtInsertEndTime.getText().isEmpty())){
					filterTIME=new FilterByTime(txtInsertStartTime.getText(),txtInsertEndTime.getText());
					specificFiltered2=SetDataBase.filter(filtered,filterTIME);
					specificFiltered3=SetDataBase.filter(specificFiltered1,filterTIME);

				}
				filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")||(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+")))";
				label_Filter.setText(filter_Properties);
				//щерд
				filterp=new FilterObject("","","",textFieldPhoneID.getText(),txtInsertStartTime.getText(),txtInsertEndTime.getText());
				System.out.println(filter_Properties);
			}
			
			if ((Gui_Data.CheckBoxByPhoneID.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected()))
			{
				if (!textFieldPhoneID.getText().isEmpty()){
					filterID=new FilterByPhoneId(textFieldPhoneID.getText());
					specificFiltered1=SetDataBase.filter(combData,filterID);
				}
				if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
					double lat = Double.parseDouble(txtInsertLat.getText());
					double lon = Double.parseDouble(txtInsertLon.getText());
					double radios = Double.parseDouble(txtInsertRadios.getText());
					if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
						filterLocation=new FilterByLocation(lat,lon,0,radios);
						specificFiltered2=SetDataBase.filter(filtered,filterLocation);
						specificFiltered3=SetDataBase.filter(specificFiltered1,filterLocation);

					}
				}
				filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")||(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				label_Filter.setText(filter_Properties);
				//щерд
				filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText(),textFieldPhoneID.getText(),"","");
				System.out.println(filter_Properties);
			}
			
			if ((Gui_Data.CheckBoxByTime.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected()))
			{
				if ((!txtInsertStartTime.getText().isEmpty())&&(!txtInsertEndTime.getText().isEmpty())){
					filterTIME=new FilterByTime(txtInsertStartTime.getText(),txtInsertEndTime.getText());
					specificFiltered1=SetDataBase.filter(filtered,filterTIME);
				}
				if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
					double lat = Double.parseDouble(txtInsertLat.getText());
					double lon = Double.parseDouble(txtInsertLon.getText());
					double radios = Double.parseDouble(txtInsertRadios.getText());
					if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
						filterLocation=new FilterByLocation(lat,lon,0,radios);
						specificFiltered2=SetDataBase.filter(filtered,filterLocation);
						specificFiltered3=SetDataBase.filter(specificFiltered1,filterLocation);

					}
				}
				filter_Properties="(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+")||(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				label_Filter.setText(filter_Properties);
				//щерд
				filterp=new FilterObject(txtInsertRadios.getText(),txtInsertRadios.getText(),txtInsertLon.getText(),"",txtInsertStartTime.getText(),txtInsertEndTime.getText());
				System.out.println(filter_Properties);
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
			if(Gui_Data.Operand.equals("AND")){
				if ((Gui_Data.CheckBoxByPhoneID.isSelected())&&(Gui_Data.CheckBoxByTime.isSelected())){
					filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")&&(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+")))";
					filterp=new FilterObject("","","",textFieldPhoneID.getText(),txtInsertStartTime.getText(),txtInsertEndTime.getText());
					System.out.println("time+id ");
				}
				else if((Gui_Data.CheckBoxByPhoneID.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected())){
					filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")&&(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
					filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText(),textFieldPhoneID.getText(),"","");
					System.out.println("hhhhhhhhhhhhhhhhhhhh");
				}
				else if((Gui_Data.CheckBoxByTime.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected())){
					filter_Properties="(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+")&&(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
					filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText(),"",txtInsertStartTime.getText(),txtInsertEndTime.getText());
				}
			}
			filtered=join;
			
			

		}///////////////////////////////////////////////////////////////////////////////
		else
		{
			System.out.println("is correct"+ filtered.get(3).getPhoneId());
			//_________________filter by phone ID___________
			if (Gui_Data.CheckBoxByPhoneID.isSelected()){
				if (!textFieldPhoneID.getText().isEmpty()){
					filterID=new FilterByPhoneId(textFieldPhoneID.getText());
					filtered=SetDataBase.filter(combData,filterID);
				}
				filter_Properties="(PhoneId("+textFieldPhoneID.getText()+"))";
				filterp=new FilterObject("", "", "", textFieldPhoneID.getText(), "", "");
				label_Filter.setText(filter_Properties);
				System.out.println(filter_Properties);
			}
			//_________________filter by time___________
					if (Gui_Data.CheckBoxByTime.isSelected()){
						if ((!txtInsertStartTime.getText().isEmpty())&&(!txtInsertEndTime.getText().isEmpty())){
							filterTIME=new FilterByTime(txtInsertStartTime.getText(),txtInsertEndTime.getText());
							filtered=SetDataBase.filter(filtered,filterTIME);
						}
						filter_Properties="(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+"))";
						filterp=new FilterObject("", "", "", "", txtInsertStartTime.getText(),(String)txtInsertEndTime.getText());
						label_Filter.setText(filter_Properties);
						System.out.println(filter_Properties);
					}
			
			//_________________filter by location___________
					
			if (Gui_Data.CheckboxByLocation.isSelected()){
				if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
					double lat = Double.parseDouble(txtInsertLat.getText());
					double lon = Double.parseDouble(txtInsertLon.getText());
					double radios = Double.parseDouble(txtInsertRadios.getText());
					filterLocation=new FilterByLocation(lat,lon,0,radios);
					filtered=SetDataBase.filter(filtered,filterLocation);
				}
				filter_Properties="(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+")))";
				filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(), txtInsertLon.getText(), "", "","");
				label_Filter.setText(filter_Properties);
				System.out.println(filter_Properties);
			}
		}
		
		if(Gui_Data.Operand.equals("NOT")){
			if(Gui_Data.CheckBoxByPhoneID.isSelected()){
				filter_Properties="!"+textFieldPhoneID.getText();
				filterp=new FilterObject("", "", "", "!"+textFieldPhoneID.getText(), "", "");
			}
			else if(Gui_Data.CheckBoxByTime.isSelected()){
				filter_Properties="!(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+"))";
			    filterp=new FilterObject("", "", "", "", txtInsertStartTime.getText(),"!"+txtInsertEndTime.getText());
			}
			else if(Gui_Data.CheckboxByLocation.isSelected()){
				filter_Properties="!(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+")))";
				filterp=new FilterObject("!("+txtInsertRadios.getText(),txtInsertLat.getText(), txtInsertLon.getText()+")", "", "","");
			}
			else if ((Gui_Data.CheckBoxByPhoneID.isSelected())&&(Gui_Data.CheckBoxByTime.isSelected())){ 
				filter_Properties="!(PhoneId("+textFieldPhoneID.getText()+")&&!(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+")))";
				filterp=new FilterObject("","","","!"+textFieldPhoneID.getText(),"!("+txtInsertStartTime.getText(),txtInsertEndTime.getText()+")");
				System.out.println("time+id ");
			}
			else if((Gui_Data.CheckBoxByPhoneID.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected())){
				filter_Properties="(!PhoneId("+textFieldPhoneID.getText()+")&&(!Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				filterp=new FilterObject("!"+txtInsertRadios.getText(),txtInsertLat.getText(),"!"+txtInsertLon.getText(),"!"+textFieldPhoneID.getText(),"","");
			}
			else if((Gui_Data.CheckBoxByTime.isSelected())&&(Gui_Data.CheckboxByLocation.isSelected())){
				filter_Properties="!(Time("+txtInsertStartTime.getText()+"<data<"+txtInsertEndTime.getText()+")&&!(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				filterp=new FilterObject("!("+txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText()+")","","!("+txtInsertStartTime.getText(),txtInsertEndTime.getText()+")");
			}
		}
		
	filtered=SetDataBase.filterNOT(combData, filtered);
	return filtered;
	}
	
	//change the info
	//public static void changeinfo ( ArrayList <SampleOfWifi> combData)

	
	
	
	
	
}