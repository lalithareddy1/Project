package bookingdemo;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class BookingSearch {
	
	
	@Test
	public void search_hotel() {
		System.out.println("------------ Searching Hotel -----------------");
	}
	
	
	@Test
	public void select_hotel() {
		System.out.println("Selecting Hotel");

	
	}
	
	@Test
	public void enterGuestInfo() {
		System.out.println("Entering Guest Info");

	
	}
	
	@Test
	public void loadBooking(ITestContext context) {
		System.out.println("Loading booking");
		String id = (String) context.getAttribute("bookingID");
	}

}
