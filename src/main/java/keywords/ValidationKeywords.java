package keywords;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ValidationKeywords extends GenericKeywords{
	
	
	public ValidationKeywords() {
		System.out.println("Inside Validation Keywords");
	}
	
	public void validateTitle() {
		
	}
	
	public void validateText() {
		
		
	}
	
	public void validateValuePresentInDropDown(String locatorKey, String name) {
		
		Select select = new Select(getElement(locatorKey));		
		WebElement option = select.getFirstSelectedOption();
		String option_value = option.getText();
		System.out.println("Selected Option value is " + option_value);
		if(!option_value.equals(name)){
			reportFailure("Option "+ name +" not present in Drop Down "+locatorKey, true);
		}
		
	}
	
	public void validateSelectedValueNotInDropDown(String locatorKey, String option) {
		Select select = new Select(getElement(locatorKey));
		
		List<WebElement> all_options = select.getOptions();
		System.out.println("***************All the drop Down Values**************************");
		int size_dropdown = all_options.size();
		for(int i =0; i<size_dropdown; i++) {			
			String text = all_options.get(i).getText();
			System.out.println("All the values from drop down : " + text);
			if(text.equals(option)){
				reportFailure("Option"+option+" present in Drop Down "+locatorKey,true);
			}
		}		
		
		
		
	}
	

}
