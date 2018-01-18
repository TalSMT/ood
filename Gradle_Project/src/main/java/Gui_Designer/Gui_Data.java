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
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
public class Gui_Data {

	public String getInputFoldePath() {
		return InputfoldePath;
	}
	//-----------------call the other classes---------------
	ReadWigleWifiFiles newCsv;// read the wigle files and write comb CSV
	
	
	
	
	
	//-----------------parameters-------------------------------
	String combName = "\\DataNetWorks.csv";
	static String combFilterdName = "\\FilterdDataNetWorks.csv";

	String completeCombPath="";

	public JFrame frame;
	private ImageIcon image1;
	private JTextField folderPathInput_textField;
	public static String InputfoldePath="";
	public static String OutputfoldePath="";
	public static String OutputfoldePathForKML="";
	public static String combPath="";
	public static JTextField folderPathOutput_textField;
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
	static FilterByPhoneId filterID;
	static FilterByTime filterTIME;
	static FilterByLocation filterLocation;
	public static String Operand="";
	public static CallToAlgo1 algo1;
	public static CallToAlgo2 algo2;
	public static macSamlpe mac1;
	public static macSamlpe mac2;
	public static macSamlpe mac3;
	public static SampleOfWifi smp;
	//-----Filters-------------------------------
	public static String filter_Properties= " ";
	public static FilterObject filterp;
	public static JLabel label_Filter;
	private JButton btnSql;
	private JButton btnFilter;
	private JButton btnAlgorithms;
	private JDesktopPane desktopPane_1;
	private JLayeredPane layeredPane;
	private JLabel lblNewLabel;
		
	
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tal\\git\\oop\\Matala_0\\img\\gui_back4.png"));
		//image1=new ImageIcon(getClass().getResource("/black_background1.png"));
		
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		//frame.imageUpdate(image1, 0, 0, 0, 0, 0);
		frame.setBounds(100, 100, 1225, 765);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//if((OutputfoldePath!="")&&(combPath==""))
		System.out.println("kffffffffffffffffffffffffffffffffffff");
		
		desktopPane_1 = new JDesktopPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 1209, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(192, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 734, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(579, Short.MAX_VALUE))
		);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(45, 564, 963, -543);
		desktopPane_1.add(layeredPane);
		layeredPane.setLayout(null);
		
		btnAlgorithms = new JButton("Algorithms");
		btnAlgorithms.setBounds(88, 457, 146, 35);
		desktopPane_1.add(btnAlgorithms);
		btnAlgorithms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Algo_Window algw= new Algo_Window();
				algw.createAlgoWindow();
			}
		});
		btnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		
		label_Filter = new JLabel("__________________________________________________");
		label_Filter.setBounds(308, 468, 459, 64);
		desktopPane_1.add(label_Filter);
		label_Filter.setForeground(new Color(0, 255, 255));
		label_Filter.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		btnSql = new JButton("SQL");
		btnSql.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		btnSql.setBounds(88, 529, 146, 29);
		desktopPane_1.add(btnSql);
		
		AnsNumOfNetworks = new JLabel("0");
		AnsNumOfNetworks.setBounds(1039, 486, 33, 18);
		desktopPane_1.add(AnsNumOfNetworks);
		AnsNumOfNetworks.setForeground(new Color(0, 255, 255));
		AnsNumOfNetworks.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		AnsNumberOfRecords = new JLabel("0");
		AnsNumberOfRecords.setBounds(1039, 432, 51, 18);
		desktopPane_1.add(AnsNumberOfRecords);
		AnsNumberOfRecords.setForeground(new Color(0, 255, 255));
		AnsNumberOfRecords.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		//----------------------------Undo Filter-------------------------
		JButton btnUndoFilter = new JButton("Undo Filter ");
		btnUndoFilter.setBounds(423, 428, 149, 29);
		desktopPane_1.add(btnUndoFilter);
		btnUndoFilter.setForeground(new Color(0, 255, 255));
		btnUndoFilter.setBackground(new Color(0, 0, 0));
		
		btnFilter = new JButton("Filter");
		btnFilter.setBounds(308, 421, 92, 35);
		desktopPane_1.add(btnFilter);
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filter_Window fltw= new Filter_Window();
				fltw.createFilterWindow();
			}
		});
		btnFilter.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		
		//----------------------------------------------folder path input text field------------------------------------
		folderPathInput_textField = new JTextField();
		folderPathInput_textField.setBounds(76, 99, 673, 26);
		desktopPane_1.add(folderPathInput_textField);
		folderPathInput_textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				InputfoldePath=folderPathInput_textField.getText();
				System.out.println("meeeeeeeeeeeeeee11111111111111111111");

			}
		});
		
		//----------------------------------------------ok button for read folder with wigle csv files---------------------
		folderPathInput_textField.setColumns(10);
		
		folderPathOutput_textField = new JTextField();
		folderPathOutput_textField.setBounds(76, 210, 673, 26);
		desktopPane_1.add(folderPathOutput_textField);
		folderPathOutput_textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				OutputfoldePath=folderPathOutput_textField.getText();
				OutputfoldePathForKML=folderPathOutput_textField.getText();
			}
		});
		folderPathOutput_textField.setColumns(10);
		JButton btnReadFiles = new JButton("read files");
		btnReadFiles.setBounds(882, 84, 151, 29);
		desktopPane_1.add(btnReadFiles);
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
		
		//----------------------------export comb csv--------------------------------------------------------------
		JButton btnbtnOkInputPath = new JButton("create comb");
		btnbtnOkInputPath.setBounds(882, 192, 151, 29);
		desktopPane_1.add(btnbtnOkInputPath);
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
		
		combPath_textField = new JTextField();
		combPath_textField.setBounds(76, 313, 673, 26);
		desktopPane_1.add(combPath_textField);
		
				
				combPath_textField.addCaretListener(new CaretListener() {
					
					//------------------------------------------------------------------------------------
					public void caretUpdate(CaretEvent e) {
						combPath=combPath_textField.getText();
						
					}
				});
				combPath_textField.setColumns(10);
				
				
				//----------------------------------------------ok button for read comb---------------------
				JButton btnOkcsvCOMB = new JButton("read comb");
				btnOkcsvCOMB.setBounds(882, 312, 151, 29);
				desktopPane_1.add(btnOkcsvCOMB);
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
				btnDeleteDatabase.setBounds(813, 545, 141, 29);
				desktopPane_1.add(btnDeleteDatabase);
				btnDeleteDatabase.setForeground(new Color(0, 255, 255));
				btnDeleteDatabase.setBackground(new Color(0, 0, 0));
				
				JButton buttonExportKML = new JButton("export KML");
				buttonExportKML.setBounds(91, 387, 183, 29);
				desktopPane_1.add(buttonExportKML);
				buttonExportKML.setBackground(new Color(0, 0, 0));
				buttonExportKML.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//if(!combData.isEmpty())
							ExportKML.writeKMLFile(combData);
					}
				});
				buttonExportKML.setForeground(new Color(0, 255, 255));
				
				lblNewLabel = new JLabel("New label");
				lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Tal\\git\\oop\\Matala_0\\img\\gui_back4.png"));
				lblNewLabel.setBounds(0, -5, 1209, 739);
				desktopPane_1.add(lblNewLabel);
				btnDeleteDatabase.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deletedHeader=true;
						combData=SetDataBase.deleteCombData(combData);
						AnsNumberOfRecords.setText(""+combData.size());
						AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(combData));

					}
				});
		btnUndoFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				combData= processedCsvFile;
				Gui_Data.AnsNumberOfRecords.setText(""+Gui_Data.combData.size());
				Gui_Data.AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(Gui_Data.combData));
			}
		});
		btnSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				SQL_window sqWind= new SQL_window();
				sqWind.createSQL_Window();
			}
		});
		frame.getContentPane().setLayout(groupLayout);
	}
}