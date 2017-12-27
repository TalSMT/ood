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

import ReadAndWriteFiles.ReadCombCsv;
import ReadAndWriteFiles.csvToNewCsv;
import Sample_Object.SampleOfWifi;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;

import DataBase.SetDataBase;

import javax.swing.event.CaretEvent;

public class Gui_Data {

	public String getInputFoldePath() {
		return InputfoldePath;
	}
	//-----------------call the other classes---------------
	csvToNewCsv newCsv;// read the wigle files and write comb CSV
	
	
	
	
	
	//-----------------parameters-------------------------------
	String combName = "\\DataNetWorks.csv";
	private JFrame frame;
	private JTextField folderPathInput_textField;
	static String InputfoldePath="";
	public static String OutputfoldePath="";
	public static String combPath="";
	private JTextField folderPathOutput_textField;
	private JTextField combPath_textField;
	
	//------read comb----
	public static ArrayList <SampleOfWifi> combData;
	
	
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
		frame.setBounds(100, 100, 939, 535);
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
		
		//----------------------------------------------ok button for read folder ant write comb---------------------
		folderPathInput_textField.setColumns(10);
		JButton btnOkInputPath = new JButton("OK");
		btnOkInputPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((InputfoldePath!="")&&(OutputfoldePath!=""))
				{
					System.out.println(InputfoldePath);

					try {
						OutputfoldePath=OutputfoldePath+combName;
						frame.getContentPane().setBackground(new Color(0, 0, 0));
						 newCsv = new csvToNewCsv(InputfoldePath);
						combPath_textField.setText(OutputfoldePath);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		
		btnOkInputPath.setForeground(new Color(138, 43, 226));
		//----------------------------------------------folder output path text field------------------------------------
		JLabel lblInsertOutputPackage = new JLabel("Insert Output package path");
		lblInsertOutputPackage.setForeground(Color.MAGENTA);
		lblInsertOutputPackage.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		
		folderPathOutput_textField = new JTextField();
		folderPathOutput_textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				OutputfoldePath=folderPathOutput_textField.getText();
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
		JButton btnOkcsvCOMB = new JButton("OK");
		btnOkcsvCOMB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (combPath!="")
				{
					combData=ReadCombCsv.readCsvCombwithHeaders(combPath);
				}
					
			}
		});
		btnOkcsvCOMB.setForeground(new Color(138, 43, 226));
		
		//---------------------------------delete database button --------------------------------------------
		JButton btnDeleteDatabase = new JButton("delete database");
		btnDeleteDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				combData=SetDataBase.deleteCombData(combData);
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInsertPackagePath, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInsertOutputPackage, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInsertCombCsv, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(folderPathInput_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(folderPathOutput_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
										.addComponent(combPath_textField, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE))
									.addGap(29)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnOkcsvCOMB, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnOkInputPath, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(btnDeleteDatabase)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertPackagePath)
						.addComponent(folderPathInput_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertOutputPackage, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(folderPathOutput_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOkInputPath, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertCombCsv, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(combPath_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOkcsvCOMB))
					.addGap(33)
					.addComponent(btnDeleteDatabase)
					.addGap(319))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
