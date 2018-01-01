package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Sample_Object.SampleOfWifi;
import Sample_Object.WifiPoint;
import Sample_Object.macSamlpe;

public class TestFilterByMac {
	SampleOfWifi sampCheck;
	ArrayList<SampleOfWifi> arrListCheck=new ArrayList<SampleOfWifi>();
	ArrayList<WifiPoint> arrcheck =new ArrayList<WifiPoint>();
	WifiPoint check;
	ArrayList<macSamlpe> ans =new ArrayList<macSamlpe>();
	
	@Before
	public void setUp() throws Exception {
		sampCheck = new SampleOfWifi("28/10/2017 20:10","model=ONEPLUS A3003",	32.09038727	,34.87862948,56,10);
		sampCheck.addWifiSpot("Bezeq-NGN_E9CBFB",	"cc:b2:55:e9:cb:fc",	6,	-68);
		sampCheck.addWifiSpot("Glenda",	"08:6a:0a:22:4d:25",	1,	-72);
		sampCheck.addWifiSpot("Partner"	,"42501_10131_855317	",0,	-51);
		sampCheck.addWifiSpot("Partner"	,"42501_10131_855317	",0,	-63);
		arrListCheck.add(sampCheck);
		sampCheck =new SampleOfWifi("28/10/2017 20:10", "model=ONEPLUS A3003", 32.0909486, 34.8786141, 0, 9);
	}
	@Test
	public void test() {
		//ans=FilterByMac.
		
	}

}