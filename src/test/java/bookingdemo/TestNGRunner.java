package bookingdemo;

import java.util.ArrayList;
import java.util.List;

public class TestNGRunner {

	public static void main(String[] args) {
		CustomTestNGRunner runner = new CustomTestNGRunner(1);
		runner.createSuite("BookingSuite", false);
		runner.addTest("Create Booking Test - Apply Discount -- pay@Hotel");
		runner.addTestParameters("action", "pay@Hotel");

		List<String> includedMethodNames = new ArrayList<String>();
		includedMethodNames.add("search_hotel");
		includedMethodNames.add("select_hotel");
		includedMethodNames.add("enterGuestInfo");
		runner.addTestClass("bookingdemo.BookingSearch", includedMethodNames);
		
		
		
		includedMethodNames = new ArrayList<String>();
		includedMethodNames.add("apply_discount");
		includedMethodNames.add("make_payment");
		runner.addTestClass("bookingdemo.PaymentManager", includedMethodNames);
		
		includedMethodNames = new ArrayList<String>();
		includedMethodNames.add("check_email");
		runner.addTestClass("bookingdemo.CustomerCommunications", includedMethodNames);
		
		
		runner.run();

	}

}
