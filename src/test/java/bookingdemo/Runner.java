package bookingdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Runner {
	
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		
		XmlSuite suite = new XmlSuite();
		suite.setName("BookingSuite");
		
		List<XmlSuite> allSuites = new ArrayList<XmlSuite>();
		allSuites.add(suite);
		testng.setXmlSuites(allSuites);
		
		XmlTest test = new XmlTest(suite);
		test.setName("Create Booking Test - Apply Discount -- pay@Hotel");
		
		/************************Adding Parameters***************************************/
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("action", "pay@Hotel");
		test.setParameters(parameters);
		
		List<XmlClass> testClasses = new ArrayList<XmlClass>();				
		/************************Adding class and Including Methods***************************************/
		XmlClass testclass = new XmlClass("bookingdemo.BookingSearch");

		List<XmlInclude> methods = new ArrayList<XmlInclude>();
		methods.add(new XmlInclude("search_hotel",1));
		methods.add(new XmlInclude("select_hotel",2));
		methods.add(new XmlInclude("enterGuestInfo",3));
		
		testclass.setIncludedMethods(methods);
		testClasses.add(testclass);
			
		/************************Adding another Class***************************************/
		testclass = new XmlClass("bookingdemo.PaymentManager");
		methods = new ArrayList<XmlInclude>();
		methods.add(new XmlInclude("apply_discount",1));
		methods.add(new XmlInclude("make_payment",2));
		testclass.setIncludedMethods(methods);
		testClasses.add(testclass);
		
		/************************Adding another Class***************************************/
		testclass = new XmlClass("bookingdemo.CustomerCommunications");
		methods = new ArrayList<XmlInclude>();
		methods.add(new XmlInclude("check_email",1));
		testclass.setIncludedMethods(methods);
		testClasses.add(testclass);
		test.setXmlClasses(testClasses);	
		
		
		testng.run();
		

		
		
		
		
		
	
	}

}
