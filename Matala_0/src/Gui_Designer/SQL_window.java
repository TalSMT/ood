package Gui_Designer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Sql.MySql;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SQL_window {

	public static JFrame frame;
	public static JTextField txtInsertIp;
	public  static JTextField txtInsertUrl;
	public  static JTextField txtInsertPassword;
	public static JTextField txtInsertDatabase;
	private JTextField txtTabelName;
	/**
	 * Launch the application.
	 */
	public static void createSQL_Window() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQL_window window = new SQL_window();
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
	public SQL_window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 809, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane);
		
		txtInsertIp = new JTextField();
		txtInsertIp.setText("5.29.193.52");
		txtInsertIp.setBounds(212, 101, 146, 26);
		layeredPane.add(txtInsertIp);
		txtInsertIp.setColumns(10);
		
		txtInsertUrl = new JTextField();
		txtInsertUrl.setText("oop1");
		txtInsertUrl.setColumns(10);
		txtInsertUrl.setBounds(225, 227, 255, 26);
		layeredPane.add(txtInsertUrl);
		
		txtInsertPassword = new JTextField();
		txtInsertPassword.setText("Lambda1();");
		txtInsertPassword.setColumns(10);
		txtInsertPassword.setBounds(312, 284, 240, 26);
		layeredPane.add(txtInsertPassword);
		
		txtInsertDatabase = new JTextField();
		txtInsertDatabase.setText("jdbc:mysql://5.29.193.52:3306/oop_course_ariel");
		txtInsertDatabase.setColumns(10);
		txtInsertDatabase.setBounds(212, 161, 370, 26);
		layeredPane.add(txtInsertDatabase);
		
		JButton btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////////////////////////////////////////////////
				if ((txtInsertIp.getText()!="")&&(txtInsertUrl.getText()!="")&&(txtInsertPassword.getText()!="")&&(txtInsertDatabase.getText()!=""))
				{
					frame.dispose();
					Gui_Data.combData=MySql.sqlManage(Gui_Data.combData,txtInsertIp.getText(), txtInsertUrl.getText(), txtInsertPassword.getText(), txtInsertDatabase.getText());
				}
				
				
				////////////////////////////////////////////////////////////////////////////////////////////
				

			}
		});
		btnConnect.setBounds(321, 435, 190, 36);
		layeredPane.add(btnConnect);
		
		txtTabelName = new JTextField();
		txtTabelName.setText("TABEL NAME");
		txtTabelName.setColumns(10);
		txtTabelName.setBounds(359, 352, 240, 26);
		layeredPane.add(txtTabelName);
		
		JButton button = new JButton("close");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		 		frame.dispose();

			}
		});
		button.setBounds(702, 11, 67, 29);
		layeredPane.add(button);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Tal\\git\\oop\\Matala_0\\img\\sql.png"));
		lblNewLabel_1.setBounds(0, 0, 783, 480);
		layeredPane.add(lblNewLabel_1);
	}
}
