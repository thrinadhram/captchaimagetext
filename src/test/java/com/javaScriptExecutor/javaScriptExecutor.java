package com.javaScriptExecutor;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class javaScriptExecutor {

	public static void main(String[] args) throws IOException {

		WebDriver driver=new ChromeDriver();
		driver.get("https://www.twoplugs.com/");

		driver.manage().window().maximize();

		//Flashing Element

		/*	WebElement joinfree= driver.findElement(By.xpath("(//a[@class='btn green']/span)[1]"));	
			javaUlities.flash(joinfree, driver);*/

		//Border around a element

		/*	WebElement joinfree= driver.findElement(By.xpath("(//a[@class='btn green']/span)[1]"));	
		javaUlities.drawBoader(joinfree, driver);*/

		//Boader ScreenShot

		/*	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File trg=new File("c://SreenShot/twoplug.png");
		FileUtils.copyFile(src, trg);*/

		//Capture Title of the Page

		/*	System.out.println(driver.getTitle()); //Using Normal driver Method	
		String title=javaUlities.getTitleByJS(driver);
		System.out.println(title);*/

		//Click Button Using JavaScript

		/*	WebElement loginBtn=driver.findElement(By.xpath("/html/body/div/header/div/ul/li[1]/a"));
		javaUlities.clickElementByJS(loginBtn, driver);*/

		//Generate Alert Message	
		//javaUlities.generateAlert(driver,"Click on Login Button......");

		//Refreshing Page Using JavaScript
		//	javaUlities.refreshPageByJS(driver);

		//scrollByElement
		/*WebElement img=driver.findElement(By.xpath("//*[@id=\"rslides3_s0\"]/div[1]/img"));
		javaUlities.scrollIntoView(img, driver);*/

		//ScrollpageDown

		javaUlities.scrollPageDown(driver);
	}

}
