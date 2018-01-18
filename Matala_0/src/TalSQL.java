//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class TalSQL {
//
//	private static String _ip = "5.29.193.52";
//	private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
//	private static String _user = "oop1";
//	private static String _password = "Lambda1();";
//	private static Connection _con = null;
//
//	public static void main(String[] args) {
//		test_ex4_db();
//	}
//
//	public static void test_ex4_db() {
//		Statement st = null;
//		ResultSet rs = null;
//		int max_id = -1;
//
//		try {     
//			_con = DriverManager.getConnection(_url, _user, _password);
//			st = _con.createStatement();
//			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
//			if (rs.next()) {
//				System.out.println("**** Update: "+rs.getString(1));
//			}
//
//			PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");
//			rs = pst.executeQuery();
//			int ind=0;
//			while (rs.next()) {
//				int size = rs.getInt(7);
//				int len = 7+2*size;
//				if(ind%100==0) {
//					for(int i=1;i<=len;i++){
//						System.out.print(ind+") "+rs.getString(i)+",");
//					}
//					System.out.println();
//				}
//				ind++;
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}
