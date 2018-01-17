package Sql;

import java.sql.PreparedStatement;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Level;

import java.util.logging.Logger;

import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;

import java.sql.Statement;



public class MySql {



	  private static String _ip ;

	  private static String _url;

	  private static String _user;

	  private static String _password;

	  
	  private static Connection _con = null;

    public static ArrayList<SampleOfWifi> sqlManage (ArrayList<SampleOfWifi> oldDatabase , String ip,String url,String user,String password)
    {
    	_ip=ip;
    	_url=url;
    	_user= user;
    	_password=password;
        ArrayList<SampleOfWifi> listSampOfWifi = new ArrayList<>();

        listSampOfWifi = readSqlTable(); //call test ex4 max id will be -1

    	return mergeDatabase( oldDatabase,listSampOfWifi);
    }
    
    public static ArrayList<SampleOfWifi> mergeDatabase(ArrayList<SampleOfWifi> oldDatabase,ArrayList<SampleOfWifi> newDatabase) {
    	boolean exists;
    	ArrayList<SampleOfWifi> merge= newDatabase;
    	for (int i = 0; i < oldDatabase.size(); i++) {
    		exists=false;
    		
    		for (int j = 0; j < newDatabase.size(); j++) {
				if (oldDatabase.get(i).equals(newDatabase.get(j)))
				exists=true;
			}
			if (!exists) {
				merge.add(oldDatabase.get(i));
				
			}
		}
    return merge;
    }
    
    
    
    

    public static ArrayList<SampleOfWifi> readSqlTable() {
        Statement st = null;
        ResultSet rs = null;
        
        ArrayList<SampleOfWifi> listSampOfWifi = new ArrayList<>();
        SampleOfWifi line = null;
       
        
        String time,phoneId, lat,lon,alt;
       double signal;
        int wifi_network;

        try {     

        	System.out.println(_url);
        	System.out.println(_user);
        	System.out.println(_password);

            _con = DriverManager.getConnection(_url, _user, _password);

            st = _con.createStatement();

            rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
            if (rs.next()) {

                System.out.println("**** Update: "+rs.getString(1));

            }

           

            PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");

            rs = pst.executeQuery();

            

            while (rs.next()) {
            	 ArrayList<WifiPoint> listWifiPoint = new ArrayList<>();
                 WifiPoint point;
            	int size = rs.getInt(7);

            	//int len = 7+2*size;


//            		for(int i=1;i<=len;i++){
//
//            			System.out.print(ind+") "+rs.getString(i)+",");
//
//            		}

            		System.out.println();
            		time=rs.getString(2);
            		phoneId=rs.getString(3);
            		lat=rs.getString(4);
            		lon=rs.getString(5);
            		alt=rs.getString(6);
            		wifi_network=Integer.parseInt(rs.getString(7));
            		
            		for (int i =8 ; i<2*wifi_network+8;i=i+2){
            		signal=Double.parseDouble(rs.getString(i+1));
                        point = new WifiPoint("sql table",rs.getString(i), 0,signal);
                        		//("from sql table","","","",rs.getString(i+1),wifi_Lat,wifi_Lon,wifi_Alt,"wifi",wifi_Device);
                        listWifiPoint.add(point);

            		}
            		line.addWifiSpot(listWifiPoint);
            		listSampOfWifi.add(line);
            		

            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(MySql.class.getName());

            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {

                if (rs != null) {rs.close();}

                if (st != null) { st.close(); }

                if (_con != null) { _con.close();  }

            } catch (SQLException ex) {

                

                Logger lgr = Logger.getLogger(MySql.class.getName());

                lgr.log(Level.WARNING, ex.getMessage(), ex);

            }

        }
    	System.out.println(listSampOfWifi.get(4).getPhoneId());

        System.out.println("done!!!!!");

        return listSampOfWifi;
    }

   

}