package com.CaptureText;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CaptureText {

	public static void main(String[] args) throws TesseractException, InterruptedException, IOException {

		WebDriver driver=new ChromeDriver();
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.xpath(" //div[@class='row col-sm-12 h_head1']//a[.=' LOGIN ']")).click();
		
		Thread.sleep(4000);
		
		File src = driver.findElement(By.className("captcha_div")).getScreenshotAs(OutputType.FILE);
		String dest =System.getProperty("user.dir")+"/SreenShot/capture.png" ;
		FileHandler.copy(src,new File(dest));
		
		ITesseract image =new Tesseract();
		String imageText=image.doOCR(new File(dest));
		
		String finalTest=imageText.split("below")[0].replace("[^a-zA-Z0-9]", "");
		System.out.println(finalTest);
		System.out.println(finalTest.length());
		String Text=finalTest.substring(0,finalTest.length()-3);
		System.out.println(Text);
	//	String[] arrOfStr = finalTest.split(" ");
		//System.out.println("FinalTest:"+arrOfStr);
		
		//thrinad_20
		
		driver.findElement(By.xpath("//input[@formcontrolname='userid']")).sendKeys("thrinad_20");
		
		//Thrinadh@1995
		
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Thrinadh@1995");

	//	driver.findElement(By.xpath("//label[@for='otpLogin']")).click();
		
		
	//	driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
		
		driver.findElement(By.xpath("//input[@id='captcha']")).sendKeys(Text);
		Thread.sleep(5000);

		driver.findElement(By.xpath("//button[.='SIGN IN']")).click();

	}

}
