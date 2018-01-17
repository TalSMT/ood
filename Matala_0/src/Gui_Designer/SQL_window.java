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

public class SQL_window {

	public static JFrame frame;
	public static JTextField txtInsertIp;
	public  static JTextField txtInsertUrl;
	public  static JTextField txtInsertPassword;
	public static JTextField txtInsertDatabase;
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
		frame.setBounds(100, 100, 809, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane);
		
		txtInsertIp = new JTextField();
		txtInsertIp.setText("5.29.193.52");
		txtInsertIp.setBounds(198, 46, 146, 26);
		layeredPane.add(txtInsertIp);
		txtInsertIp.setColumns(10);
		
		txtInsertUrl = new JTextField();
		txtInsertUrl.setText("oop1");
		txtInsertUrl.setColumns(10);
		txtInsertUrl.setBounds(198, 141, 146, 26);
		layeredPane.add(txtInsertUrl);
		
		txtInsertPassword = new JTextField();
		txtInsertPassword.setText("Lambda1();");
		txtInsertPassword.setColumns(10);
		txtInsertPassword.setBounds(198, 197, 146, 26);
		layeredPane.add(txtInsertPassword);
		
		txtInsertDatabase = new JTextField();
		txtInsertDatabase.setText("jdbc:mysql://5.29.193.52:3306/oop_course_ariel");
		txtInsertDatabase.setColumns(10);
		txtInsertDatabase.setBounds(202, 95, 370, 26);
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
		btnConnect.setBounds(619, 134, 115, 29);
		layeredPane.add(btnConnect);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(81, 49, 69, 20);
		layeredPane.add(lblNewLabel);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(81, 98, 69, 20);
		layeredPane.add(lblUrl);
		
		JLabel lblUser = new JLabel("USER");
		lblUser.setBounds(81, 144, 69, 20);
		layeredPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(83, 200, 85, 20);
		layeredPane.add(lblPassword);
	}
}
