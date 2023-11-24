package com.brokenLinks;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task2 {

		WebDriver driver=null;
		
		@BeforeMethod
		public void setup() {
		driver = new ChromeDriver();
		driver.get("https://dsdedicare.com/uat/admin/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//div[@class='form-group']//input")).sendKeys("bharath@dedicatedsleep.net");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("kjYYUdc$#@78782F");
		driver.findElement(By.className("btnSubmit")).click();
		}
		
		
		@Test(priority = 1)
		public void testCreatePatient() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//td[text()='Advanced Sleep - Conway Horowitz']")).click();

		WebElement platform= driver.findElement(By.xpath("//i[@class='fa fa-user-md']"));

		Actions action= new Actions(driver);
		action.moveToElement(platform).click().perform();

		driver.findElement(By.xpath("//a[text()=' Patients']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='tablinks']")).click();

		
		WebElement firstName= driver.findElement(By.xpath("//input[@name='first_name']"));
		firstName.sendKeys("Abhi");

		WebElement lastName= driver.findElement(By.xpath("//input[@name='last_name']"));
		lastName.sendKeys("Ram");

		WebElement preferredName= driver.findElement(By.xpath("//input[@name='preferred_name']"));
		preferredName.sendKeys("bujji");

		WebElement DOB= driver.findElement(By.xpath("//input[@name='date_of_birth']"));
		DOB.sendKeys("4/16/1996");

		WebElement age= driver.findElement(By.xpath("//input[@name='age']"));
		age.click();
		
		//DropDown Using Generic Method
		WebElement gender= driver.findElement(By.xpath("//select[@id='gender']"));
		selectOptionForDropDown(gender,"Male");
		WebElement height= driver.findElement(By.xpath("//select[@id='height']"));
		selectOptionForDropDown(height,"5 Ft");
		WebElement inch= driver.findElement(By.xpath("//select[@id='inch']"));
		selectOptionForDropDown(inch,"11 In");

		WebElement weight= driver.findElement(By.xpath("//input[@id='weight']"));
		weight.sendKeys("75");
		WebElement moblie= driver.findElement(By.xpath("//input[@id='mobile']"));
		moblie.sendKeys("8500423083");
		WebElement Homemoblie= driver.findElement(By.xpath("//input[@id='home_phone']"));
		Homemoblie.sendKeys("6200423083");
		WebElement Workmoblie= driver.findElement(By.xpath("//input[@id='work_phone']"));
		Workmoblie.sendKeys("8700423083");
		WebElement occution= driver.findElement(By.xpath("//input[@id='occupation']"));
		occution.sendKeys("Software");
		WebElement email= driver.findElement(By.xpath("//input[@id='email_id']"));
		email.sendKeys("abhikrishna1234@gmail.com");

		//DropDown Using Generic Method
		WebElement time_zone= driver.findElement(By.xpath("//select[@id='patient_time_zone']"));
		selectOptionForDropDown1(time_zone,"Mountain Time Zone");
		WebElement patient_type= driver.findElement(By.xpath("//select[@id='patient_type']"));
		selectOptionForDropDown1(patient_type,"Implant");
		WebElement patient_insurance= driver.findElement(By.xpath("//select[@id='patient_insurance']"));
		selectOptionForDropDown1(patient_insurance,"Anthem");
		WebElement provider= driver.findElement(By.xpath("//select[@id='provider']"));
		selectOptionForDropDown1(provider,"Sara Joslin");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//h6[starts-with(text(),'Additio')]")).click();
	
		driver.findElement(By.xpath("//input[@id='address']")).sendKeys("MadhuraNagar");
		driver.findElement(By.xpath("//input[@id='street']")).sendKeys("NagalaskhmiStreet");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Hyderabad");
		WebElement state= driver.findElement(By.xpath("//select[@id='state']"));
		selectOptionForDropDown1(state,"Alabama");

		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys("516101");


		driver.findElement(By.xpath("//button[@onclick='FormSubmit()']")).click();
		
		
		

		}
		
	
	public static void selectOptionForDropDown(WebElement element,String value) {
		Select sel= new Select(element);

		List<WebElement> allOptions=sel.getOptions();
		for (WebElement option : allOptions) {
			if(option.getText().equals(value)) {
				option.click();
			}
		}
	}
	public static void selectOptionForDropDown1(WebElement element,String value) {
		Select sel= new Select(element);

		List<WebElement> allOptions=sel.getOptions();
		for (WebElement option : allOptions) {
			if(option.getText().equals(value)) {
				option.click();
			}
		}
	}
	
	
	
	@AfterMethod
	void tearDown() throws InterruptedException {
		Thread.sleep(3000);
	//	driver.close();
	}
}



