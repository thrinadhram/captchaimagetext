package com.brokenLinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLink {

	public static void main(String[] args) throws IOException, InterruptedException {


		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		List<WebElement> Links = driver.findElements(By.tagName("a"));
		System.out.println("total links are =" + Links.size());
		for (WebElement Total : Links) {

			String url = Total.getAttribute("href");
			URL obj = new URL(url);
			HttpURLConnection http = (HttpURLConnection) obj.openConnection();
			http.connect();
			Thread.sleep(2000);
			int code = http.getResponseCode();
			if (code >= 400) {
				System.out.println(url += "Url is broken link");
			} else {
				System.out.println(url += "Valid Url ");

			}
		}
	}

}
