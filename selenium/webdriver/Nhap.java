package webdriver;

import org.openqa.selenium.Keys;

public class Nhap {
	public static void main(String[] args) {
		
		String infor_birth = "20082020";
		System.out.println(infor_birth.length());
		System.out.println(infor_birth.length()-3);
		System.out.println(infor_birth.substring(4,8)+"-"+infor_birth.substring(2,4)+"-" +infor_birth.substring(0,2));
	}
}
