package bookingdemo;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PaymentManager {
	
	
	
	@Test
	public void apply_discount() {
		System.out.println("Apply Discount");
	}
	
	
	
	@Parameters({"action"})
	@Test
	public void make_payment(String paymentType, ITestContext context) {
		
		System.out.println("Making payment");
		if(paymentType.equals("instantpayment")) {
			System.out.println("Making Instant Payment");
		}
		
		if(paymentType.equals("pay@Hotel")) {
			System.out.println("Make Payment at hotel");
		}
		String BookingID = "abcdefghijlkm1234";
		context.setAttribute("bookingID", BookingID);
	}
	

}
