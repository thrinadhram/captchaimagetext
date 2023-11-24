package com.GetCookies;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cookies {

	public static void main(String[] args) {

		WebDriver driver= new ChromeDriver();
		driver.get("http://www.google.com/");
		driver.manage().window().maximize();
		
		Set<Cookie> cookies = driver.manage().getCookies();
		
		for(Cookie cookie:cookies)
		{
			System.out.println("Cookie Name is:"+cookie.getName());
			System.out.println("Cookie Value is:"+cookie.getValue());
			System.out.println("Cookie Domain is:"+cookie.getDomain());
			System.out.println("Cookie path is:"+cookie.getPath());
			System.out.println("Cookie Expirty Data is:"+cookie.getExpiry());

			System.out.println("-----------------------------------");

		}
	}

}
