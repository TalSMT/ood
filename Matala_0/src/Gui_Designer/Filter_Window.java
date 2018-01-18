package Gui_Designer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.SwingConstants;

import Algoritems.Constant;
import DataBase.SetDataBase;
import Filters.FilterByLocation;
import Filters.FilterByPhoneId;
import Filters.FilterByTime;
import Filters.FilterObject;
import ReadAndWriteFiles.WriteCombCsv;
import Sample_Object.SampleOfWifi;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Filter_Window {

	private JFrame frame;
	public static JTextField textFieldPhoneID;
	public static JTextField textField_insert_start_time;
	public static JTextField txtInsertEndTime;
	public static JTextField txtInsertLat;
	public static JTextField txtInsertLon;
	public static JTextField txtInsertRadios;
	public static JCheckBox CheckboxByLocation;
	public static JCheckBox CheckBoxByTime ;
	public static JCheckBox CheckBoxByPhoneID;
	private JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void createFilterWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Filter_Window window = new Filter_Window();
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
	public Filter_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 870, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 182, 193));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JComboBox AndNotORcomboBox = new JComboBox();
		AndNotORcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gui_Data.Operand=(String)AndNotORcomboBox.getSelectedItem();
				System.out.println(Gui_Data.Operand);
			}
		});
		AndNotORcomboBox.setModel(new DefaultComboBoxModel(new String[] {"AND", "NOT", "OR"}));
		AndNotORcomboBox.setBounds(23, 197, 93, 46);
		desktopPane.add(AndNotORcomboBox);
		
		textFieldPhoneID = new JTextField();
		textFieldPhoneID.setText("insert ID");
		textFieldPhoneID.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPhoneID.setColumns(10);
		textFieldPhoneID.setBounds(276, 108, 480, 26);
		desktopPane.add(textFieldPhoneID);
		
		textField_insert_start_time = new JTextField();
		textField_insert_start_time.setText("insert start time");
		textField_insert_start_time.setHorizontalAlignment(SwingConstants.CENTER);
		textField_insert_start_time.setColumns(10);
		textField_insert_start_time.setBounds(276, 207, 218, 26);
		desktopPane.add(textField_insert_start_time);
		
		txtInsertEndTime = new JTextField();
		txtInsertEndTime.setText("insert end time");
		txtInsertEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertEndTime.setColumns(10);
		txtInsertEndTime.setBounds(519, 207, 237, 26);
		desktopPane.add(txtInsertEndTime);
		
		txtInsertLat = new JTextField();
		txtInsertLat.setText("insert lat");
		txtInsertLat.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertLat.setColumns(10);
		txtInsertLat.setBounds(276, 320, 218, 26);
		desktopPane.add(txtInsertLat);
		
		txtInsertLon = new JTextField();
		txtInsertLon.setText("insert lon");
		txtInsertLon.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertLon.setColumns(10);
		txtInsertLon.setBounds(519, 320, 237, 26);
		desktopPane.add(txtInsertLon);
		
		txtInsertRadios = new JTextField();
		txtInsertRadios.setText("insert radios");
		txtInsertRadios.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertRadios.setColumns(10);
		txtInsertRadios.setBounds(359, 378, 237, 26);
		desktopPane.add(txtInsertRadios);
		
		 CheckboxByLocation = new JCheckBox("");
		CheckboxByLocation.setBounds(158, 269, 21, 29);
		desktopPane.add(CheckboxByLocation);
		
		CheckBoxByTime = new JCheckBox("");
		CheckBoxByTime.setBounds(158, 161, 21, 29);
		desktopPane.add(CheckBoxByTime);
		
		CheckBoxByPhoneID = new JCheckBox("");
		CheckBoxByPhoneID.setBounds(158, 65, 21, 26);
		desktopPane.add(CheckBoxByPhoneID);
		//---------------------------------------------export filtered comb database-----------------------------

		JButton btnExportDatabaseFiltered = new JButton("Export database filtered");
		btnExportDatabaseFiltered.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExportDatabaseFiltered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Gui_Data.combData= filterID(Gui_Data.combData);
				try {
					String tempoutOutPut=Gui_Data.OutputfoldePath;
					Gui_Data.OutputfoldePath=Gui_Data.folderPathOutput_textField.getText()+Gui_Data.combFilterdName;
					WriteCombCsv.writeCsvFile(Gui_Data.combData);
					Gui_Data.OutputfoldePath=tempoutOutPut;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Gui_Data.AnsNumberOfRecords.setText(""+Gui_Data.combData.size());
				Gui_Data.AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(Gui_Data.combData));
			}
		});
		btnExportDatabaseFiltered.setForeground(new Color(248, 248, 255));
		btnExportDatabaseFiltered.setBackground(Color.BLACK);
		btnExportDatabaseFiltered.setBounds(10, 430, 273, 41);
		desktopPane.add(btnExportDatabaseFiltered);
		
		JButton button_1 = new JButton("Filter properties");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilteredFileObjectStream.ExportObject(Constant.filename);
				JOptionPane.showMessageDialog(null,Gui_Data.filter_Properties );
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setForeground(new Color(248, 248, 255));
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(296, 430, 256, 41);
		desktopPane.add(button_1);
		
		JButton button_2 = new JButton("Undo Filter ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gui_Data.combData= Gui_Data.processedCsvFile;
				Gui_Data.AnsNumberOfRecords.setText(""+Gui_Data.combData.size());
				Gui_Data.AnsNumOfNetworks.setText(""+SetDataBase.numOfDifferentMacSamples(Gui_Data.combData));
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setForeground(new Color(248, 248, 255));
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(562, 430, 282, 41);
		desktopPane.add(button_2);
		
		JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setBounds(777, 11, 67, 29);
		desktopPane.add(btnClose);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Tal\\git\\oop\\Matala_0\\img\\filter.png"));
		lblNewLabel.setBounds(0, 0, 857, 480);
		desktopPane.add(lblNewLabel);
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

			if ((CheckBoxByPhoneID.isSelected())&&(CheckBoxByTime.isSelected()))
			{
				if (!textFieldPhoneID.getText().isEmpty()){
					Gui_Data.filterID=new FilterByPhoneId(textFieldPhoneID.getText());
					specificFiltered1=SetDataBase.filter(combData,Gui_Data.filterID);
				}
				if ((!CheckBoxByTime.getText().isEmpty())&&(!txtInsertEndTime.getText().isEmpty())){
					Gui_Data.filterTIME=new FilterByTime(CheckBoxByTime.getText(),txtInsertEndTime.getText());
					specificFiltered2=SetDataBase.filter(filtered,Gui_Data.filterTIME);
					specificFiltered3=SetDataBase.filter(specificFiltered1,Gui_Data.filterTIME);

				}
				Gui_Data.filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")||(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+")))";
				Gui_Data.label_Filter.setText(Gui_Data.filter_Properties);
				//ׁ‰׀µׁ€׀´
				Gui_Data.filterp=new FilterObject("","","",textFieldPhoneID.getText(),CheckBoxByTime.getText(),txtInsertEndTime.getText());
				System.out.println(Gui_Data.filter_Properties);
			}
			
			if ((Filter_Window.CheckBoxByPhoneID.isSelected())&&(CheckboxByLocation.isSelected()))
			{
				if (!textFieldPhoneID.getText().isEmpty()){
					Gui_Data.filterID=new FilterByPhoneId(textFieldPhoneID.getText());
					specificFiltered1=SetDataBase.filter(combData,Gui_Data.filterID);
				}
				if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
					double lat = Double.parseDouble(txtInsertLat.getText());
					double lon = Double.parseDouble(txtInsertLon.getText());
					double radios = Double.parseDouble(txtInsertRadios.getText());
					if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
						Gui_Data.filterLocation=new FilterByLocation(lat,lon,0,radios);
						specificFiltered2=SetDataBase.filter(filtered,Gui_Data.filterLocation);
						specificFiltered3=SetDataBase.filter(specificFiltered1,Gui_Data.filterLocation);

					}
				}
				Gui_Data.filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")||(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				Gui_Data.label_Filter.setText(Gui_Data.filter_Properties);
				//ׁ‰׀µׁ€׀´
				Gui_Data.filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText(),textFieldPhoneID.getText(),"","");
				System.out.println(Gui_Data.filter_Properties);
			}
			
			if ((CheckBoxByTime.isSelected())&&(CheckboxByLocation.isSelected()))
			{
				if ((!CheckBoxByTime.getText().isEmpty())&&(!txtInsertEndTime.getText().isEmpty())){
					Gui_Data.filterTIME=new FilterByTime(CheckBoxByTime.getText(),txtInsertEndTime.getText());
					specificFiltered1=SetDataBase.filter(filtered,Gui_Data.filterTIME);
				}
				if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
					double lat = Double.parseDouble(txtInsertLat.getText());
					double lon = Double.parseDouble(txtInsertLon.getText());
					double radios = Double.parseDouble(txtInsertRadios.getText());
					if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
						Gui_Data.filterLocation=new FilterByLocation(lat,lon,0,radios);
						specificFiltered2=SetDataBase.filter(filtered,Gui_Data.filterLocation);
						specificFiltered3=SetDataBase.filter(specificFiltered1,Gui_Data.filterLocation);

					}
				}
				Gui_Data.filter_Properties="(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+")||(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				Gui_Data.label_Filter.setText(Gui_Data.filter_Properties);
				//ׁ‰׀µׁ€׀´
				Gui_Data.filterp=new FilterObject(txtInsertRadios.getText(),txtInsertRadios.getText(),txtInsertLon.getText(),"",CheckBoxByTime.getText(),txtInsertEndTime.getText());
				System.out.println(Gui_Data.filter_Properties);
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
				if ((CheckBoxByPhoneID.isSelected())&&(CheckBoxByTime.isSelected())){
					Gui_Data.filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")&&(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+")))";
					Gui_Data.filterp=new FilterObject("","","",textFieldPhoneID.getText(),CheckBoxByTime.getText(),txtInsertEndTime.getText());
					System.out.println("time+id ");
				}
				else if((CheckBoxByPhoneID.isSelected())&&(CheckboxByLocation.isSelected())){
					Gui_Data.filter_Properties="(PhoneId("+textFieldPhoneID.getText()+")&&(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
					Gui_Data.filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText(),textFieldPhoneID.getText(),"","");
					System.out.println("hhhhhhhhhhhhhhhhhhhh");
				}
				else if((CheckBoxByTime.isSelected())&&(CheckboxByLocation.isSelected())){
					Gui_Data.filter_Properties="(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+")&&(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
					Gui_Data.filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText(),"",CheckBoxByTime.getText(),txtInsertEndTime.getText());
				}
			}
			filtered=join;
			
			

		}///////////////////////////////////////////////////////////////////////////////
		else
		{
			System.out.println("is correct"+ filtered.get(3).getPhoneId());
			//_________________filter by phone ID___________
			if (CheckBoxByPhoneID.isSelected()){
				if (!textFieldPhoneID.getText().isEmpty()){
					Gui_Data.filterID=new FilterByPhoneId(textFieldPhoneID.getText());
					filtered=SetDataBase.filter(combData,Gui_Data.filterID);
				}
				Gui_Data.filter_Properties="(PhoneId("+textFieldPhoneID.getText()+"))";
				Gui_Data.filterp=new FilterObject("", "", "", textFieldPhoneID.getText(), "", "");
				Gui_Data.label_Filter.setText(Gui_Data.filter_Properties);
				System.out.println(Gui_Data.filter_Properties);
			}
			//_________________filter by time___________
					if (CheckBoxByTime.isSelected()){
						if ((!CheckBoxByTime.getText().isEmpty())&&(!txtInsertEndTime.getText().isEmpty())){
							Gui_Data.filterTIME=new FilterByTime(CheckBoxByTime.getText(),txtInsertEndTime.getText());
							filtered=SetDataBase.filter(filtered,Gui_Data.filterTIME);
						}
						Gui_Data.filter_Properties="(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+"))";
						Gui_Data.filterp=new FilterObject("", "", "", "", CheckBoxByTime.getText(),(String)txtInsertEndTime.getText());
						Gui_Data.label_Filter.setText(Gui_Data.filter_Properties);
						System.out.println(Gui_Data.filter_Properties);
					}
			
			//_________________filter by location___________
					
			if (CheckboxByLocation.isSelected()){
				if ((!txtInsertLat.getText().isEmpty())&&(!txtInsertLon.getText().isEmpty())&&(!txtInsertRadios.getText().isEmpty())){
					double lat = Double.parseDouble(txtInsertLat.getText());
					double lon = Double.parseDouble(txtInsertLon.getText());
					double radios = Double.parseDouble(txtInsertRadios.getText());
					Gui_Data.filterLocation=new FilterByLocation(lat,lon,0,radios);
					filtered=SetDataBase.filter(filtered,Gui_Data.filterLocation);
				}
				Gui_Data.filter_Properties="(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+")))";
				Gui_Data.filterp=new FilterObject(txtInsertRadios.getText(),txtInsertLat.getText(), txtInsertLon.getText(), "", "","");
				Gui_Data.label_Filter.setText(Gui_Data.filter_Properties);
				System.out.println(Gui_Data.filter_Properties);
			}
		}
		
		if(Gui_Data.Operand.equals("NOT")){
			if(CheckBoxByPhoneID.isSelected()){
				Gui_Data.filter_Properties="!"+textFieldPhoneID.getText();
				Gui_Data.filterp=new FilterObject("", "", "", "!"+textFieldPhoneID.getText(), "", "");
			}
			else if(CheckBoxByTime.isSelected()){
				Gui_Data.filter_Properties="!(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+"))";
				Gui_Data.filterp=new FilterObject("", "", "", "", CheckBoxByTime.getText(),"!"+txtInsertEndTime.getText());
			}
			else if(CheckboxByLocation.isSelected()){
				Gui_Data.filter_Properties="!(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+")))";
				Gui_Data.filterp=new FilterObject("!("+txtInsertRadios.getText(),txtInsertLat.getText(), txtInsertLon.getText()+")", "", "","");
			}
			else if ((CheckBoxByPhoneID.isSelected())&&(CheckBoxByTime.isSelected())){ 
				Gui_Data.filter_Properties="!(PhoneId("+textFieldPhoneID.getText()+")&&!(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+")))";
				Gui_Data.filterp=new FilterObject("","","","!"+textFieldPhoneID.getText(),"!("+CheckBoxByTime.getText(),txtInsertEndTime.getText()+")");
				System.out.println("time+id ");
			}
			else if((CheckBoxByPhoneID.isSelected())&&(CheckboxByLocation.isSelected())){
				Gui_Data.filter_Properties="(!PhoneId("+textFieldPhoneID.getText()+")&&(!Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				Gui_Data.filterp=new FilterObject("!"+txtInsertRadios.getText(),txtInsertLat.getText(),"!"+txtInsertLon.getText(),"!"+textFieldPhoneID.getText(),"","");
			}
			else if((CheckBoxByTime.isSelected())&&(CheckboxByLocation.isSelected())){
				Gui_Data.filter_Properties="!(Time("+CheckBoxByTime.getText()+"<data<"+txtInsertEndTime.getText()+")&&!(Location("+txtInsertRadios.getText()+"<"+"Distance ("+txtInsertLat.getText()+","+txtInsertLon.getText()+"))))";
				Gui_Data.filterp=new FilterObject("!("+txtInsertRadios.getText(),txtInsertLat.getText(),txtInsertLon.getText()+")","","!("+CheckBoxByTime.getText(),txtInsertEndTime.getText()+")");
			}
		}
		
	filtered=SetDataBase.filterNOT(combData, filtered);
	return filtered;
	}
	
	
	
}
