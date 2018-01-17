package Gui_Designer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;

import Algoritems.CallToAlgo1;
import Algoritems.CallToAlgo2;
import Sample_Object.macSamlpe;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class Algo_Window {

	private JFrame frame;
	private JTextField txtMac;
	private JTextField txtInsrertMac1;
	private JTextField txtInsrertSignal1;
	private JTextField txtInsrertSignal2;
	private JTextField txtInsrertMac3;
	private JTextField txtInsrertSignal3;
	private JTextField txtInsrertMac2;
	private JTextField txtInsrertStringOfCombLine;
	public static JLabel label_macLocation;
	JLabel label_SamplerLocation;
	JLabel lblNewLabel;
	private JButton button;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void createAlgoWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Algo_Window window = new Algo_Window();
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
	public Algo_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1164, 839);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(15, 0, 1112, 767);
		desktopPane.add(layeredPane);
		//-------------------------------------------call algo 1-------------------------------------

		JButton btn_CallAlgo1 = new JButton("FInd Mac Location");
		btn_CallAlgo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtMac.getText().isEmpty()){
					try {
						Gui_Data.algo1= new CallToAlgo1(txtMac.getText());
						label_macLocation.setText("LOCTAION: "+Gui_Data.algo1.averg.getLat()+" , "+Gui_Data.algo1.averg.getLon()+" alt: "+Gui_Data.algo1.averg.getAlt());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btn_CallAlgo1.setForeground(Color.CYAN);
		btn_CallAlgo1.setBackground(Color.BLACK);
		btn_CallAlgo1.setBounds(608, 182, 76, 46);
		layeredPane.add(btn_CallAlgo1);
		
		txtMac = new JTextField();
		txtMac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMac.setText("Insert Mac");
		txtMac.setColumns(10);
		txtMac.setBounds(194, 190, 295, 26);
		layeredPane.add(txtMac);
		
		label_macLocation = new JLabel("___");
		label_macLocation.setForeground(Color.CYAN);
		label_macLocation.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		label_macLocation.setBounds(183, 254, 336, 18);
		layeredPane.add(label_macLocation);
		//-------------------------------------------call algo 2-------------------------------------

		JButton btnSubmit = new JButton("Find Sampler Location");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
if ((!txtInsrertMac1.getText().isEmpty())&&(!txtInsrertMac2.getText().isEmpty())&&(!txtInsrertMac3.getText().isEmpty())&&(!txtInsrertSignal1.getText().isEmpty())&&(!txtInsrertSignal2.getText().isEmpty())&&(!txtInsrertSignal3.getText().isEmpty())){
					
					double signal1D = Double.parseDouble(txtInsrertSignal1.getText());
					Gui_Data.mac1= new macSamlpe(signal1D, 0, 0, 0);
					Gui_Data.mac1.setMac(txtInsrertMac1.getText());
					double signal2D = Double.parseDouble(txtInsrertSignal2.getText());
					Gui_Data.mac2= new macSamlpe(signal2D, 0, 0, 0);
					Gui_Data.mac2.setMac(txtInsrertMac2.getText());
					double signal3D = Double.parseDouble(txtInsrertSignal2.getText());
					Gui_Data.mac3= new macSamlpe(signal3D, 0, 0, 0);
					Gui_Data.mac3.setMac(txtInsrertMac3.getText());
					try {
						Gui_Data.algo2= new CallToAlgo2(Gui_Data.mac1, Gui_Data.mac2, Gui_Data.mac3);
						label_SamplerLocation.setText("LOCTAION: "+Gui_Data.algo2.thelocation.getLat()+" , "+Gui_Data.algo2.thelocation.getLon()+" alt: "+Gui_Data.algo2.thelocation.getAlt());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				else if ((!txtInsrertStringOfCombLine.getText().isEmpty()))
				{
					Gui_Data.smp=CallToAlgo2.lineToSampleOfWifi(txtInsrertStringOfCombLine.getText());
					try {
						Gui_Data.algo2= new CallToAlgo2(Gui_Data.smp);
						label_SamplerLocation.setText("LOCTAION: "+Gui_Data.algo2.thelocation.getLat()+" , "+Gui_Data.algo2.thelocation.getLon()+" alt: "+Gui_Data.algo2.thelocation.getAlt());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			
			}
		});
		btnSubmit.setForeground(Color.CYAN);
		btnSubmit.setBackground(Color.BLACK);
		btnSubmit.setBounds(654, 461, 246, 46);
		layeredPane.add(btnSubmit);
		
		txtInsrertMac1 = new JTextField();
		txtInsrertMac1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInsrertMac1.setText("insrert mac1");
		txtInsrertMac1.setColumns(10);
		txtInsrertMac1.setBounds(158, 396, 194, 26);
		layeredPane.add(txtInsrertMac1);
		
		txtInsrertSignal1 = new JTextField();
		txtInsrertSignal1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInsrertSignal1.setText("insrert signal1");
		txtInsrertSignal1.setColumns(10);
		txtInsrertSignal1.setBounds(370, 396, 194, 26);
		layeredPane.add(txtInsrertSignal1);
		
		txtInsrertSignal2 = new JTextField();
		txtInsrertSignal2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInsrertSignal2.setText("insrert signal2");
		txtInsrertSignal2.setColumns(10);
		txtInsrertSignal2.setBounds(370, 452, 194, 26);
		layeredPane.add(txtInsrertSignal2);
		
		txtInsrertMac3 = new JTextField();
		txtInsrertMac3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInsrertMac3.setText("insrert mac3");
		txtInsrertMac3.setColumns(10);
		txtInsrertMac3.setBounds(158, 514, 194, 26);
		layeredPane.add(txtInsrertMac3);
		
		txtInsrertSignal3 = new JTextField();
		txtInsrertSignal3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInsrertSignal3.setText("insrert signal3");
		txtInsrertSignal3.setColumns(10);
		txtInsrertSignal3.setBounds(370, 514, 194, 26);
		layeredPane.add(txtInsrertSignal3);
		
		txtInsrertMac2 = new JTextField();
		txtInsrertMac2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInsrertMac2.setText("insrert mac2");
		txtInsrertMac2.setColumns(10);
		txtInsrertMac2.setBounds(158, 452, 194, 26);
		layeredPane.add(txtInsrertMac2);
		
		txtInsrertStringOfCombLine = new JTextField();
		txtInsrertStringOfCombLine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtInsrertStringOfCombLine.setText("insrert String of comb line");
		txtInsrertStringOfCombLine.setForeground(new Color(0, 0, 128));
		txtInsrertStringOfCombLine.setColumns(10);
		txtInsrertStringOfCombLine.setBackground(Color.WHITE);
		txtInsrertStringOfCombLine.setBounds(91, 574, 455, 26);
		layeredPane.add(txtInsrertStringOfCombLine);
		 
		 button = new JButton("close");
		 button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		frame.dispose();
		 	}
		 });
		 button.setBounds(15, 16, 67, 29);
		 layeredPane.add(button);
		
		 label_SamplerLocation = new JLabel("_________________________________");
		label_SamplerLocation.setForeground(Color.CYAN);
		label_SamplerLocation.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		label_SamplerLocation.setBackground(Color.BLACK);
		label_SamplerLocation.setBounds(564, 579, 548, 18);
		layeredPane.add(label_SamplerLocation);
		
		 lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1112, 751);
		layeredPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Tal\\git\\oop\\Matala_0\\bin\\back.png"));
	}
}
