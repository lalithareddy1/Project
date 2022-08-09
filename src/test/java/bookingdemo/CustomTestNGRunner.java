package bookingdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class CustomTestNGRunner {
	
	
	TestNG myTestNG;
	List<XmlSuite> mySuites;
	XmlSuite suite;
	List<XmlTest> suiteTestcases;
	XmlTest test;
	List<XmlClass> testClasses;
	Map<String, String> testngParams;
	
	
	
	public CustomTestNGRunner(int threadPoolSize) {
		// TODO Auto-generated constructor stub
		myTestNG = new TestNG();
		mySuites = new ArrayList<XmlSuite>();
		myTestNG.setSuiteThreadPoolSize(threadPoolSize); //how many suites can run parallelly
		myTestNG.setXmlSuites(mySuites);
		
	}
	
	public void createSuite(String suiteName, boolean parallesTests) {
		suite = new XmlSuite();
		suite.setName(suiteName);
		if(parallesTests)
		{
			suite.setParallel(ParallelMode.TESTS);
		}
		mySuites.add(suite);
		suiteTestcases = new ArrayList<XmlTest>();

	}
	
	public void addTest(String name) {
		
		test = new XmlTest(suite);
		test.setName(name);
		testngParams = new HashMap<String, String>();
		test.setParameters(testngParams);
		testClasses = new ArrayList<XmlClass>();
		test.setXmlClasses(testClasses);
		suiteTestcases.add(test);
		
		
	}
	
	public void addTestParameters(String key, String value) {
		testngParams.put(key, value);
	}
	
	public void addTestClass(String fullClassName, List<String> includedMethodNames) {
		
		XmlClass testclass = new XmlClass(fullClassName);
		List<XmlInclude> methods = new ArrayList<XmlInclude>();
		int order =1;
		for(String name : includedMethodNames) {
			methods.add(new XmlInclude(name, order));
			order++;
		}
		testclass.setIncludedMethods(methods);
		testClasses.add(testclass);
	}
	
	public void run() {
		myTestNG.run();
	}
	
	
	

}
