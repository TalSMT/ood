package Gui_Designer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SQL_window {

	public static JFrame frame;
	private JTextField txtInsertIp;
	private JTextField txtInsertUrl;
	private JTextField txtInsertPassword;
	private JTextField txtInsertPort;
	private JTextField txtInsertDatabase;
	private JTextField txtInsertTable;
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
		txtInsertIp.setText("insert IP");
		txtInsertIp.setBounds(74, 50, 146, 26);
		layeredPane.add(txtInsertIp);
		txtInsertIp.setColumns(10);
		
		txtInsertUrl = new JTextField();
		txtInsertUrl.setText("insert user");
		txtInsertUrl.setColumns(10);
		txtInsertUrl.setBounds(74, 92, 146, 26);
		layeredPane.add(txtInsertUrl);
		
		txtInsertPassword = new JTextField();
		txtInsertPassword.setText("insert password");
		txtInsertPassword.setColumns(10);
		txtInsertPassword.setBounds(74, 134, 146, 26);
		layeredPane.add(txtInsertPassword);
		
		txtInsertPort = new JTextField();
		txtInsertPort.setText("insert port");
		txtInsertPort.setColumns(10);
		txtInsertPort.setBounds(74, 176, 146, 26);
		layeredPane.add(txtInsertPort);
		
		txtInsertDatabase = new JTextField();
		txtInsertDatabase.setText("insert database");
		txtInsertDatabase.setColumns(10);
		txtInsertDatabase.setBounds(74, 218, 146, 26);
		layeredPane.add(txtInsertDatabase);
		
		txtInsertTable = new JTextField();
		txtInsertTable.setText("insert table");
		txtInsertTable.setColumns(10);
		txtInsertTable.setBounds(74, 259, 146, 26);
		layeredPane.add(txtInsertTable);
		
		JButton btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////////////////////////////////////////////////////////////
				frame.dispose();

			}
		});
		btnConnect.setBounds(318, 145, 115, 29);
		layeredPane.add(btnConnect);
	}
}
