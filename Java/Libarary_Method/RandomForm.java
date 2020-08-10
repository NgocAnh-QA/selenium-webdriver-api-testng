package Libarary_Method;

import java.util.Random;

public class RandomForm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(RanDomMail());
	}

	public String RanDomName() {
		return "";
	}
	
	public String RanDomNumberPhone() {
		return "";
	}
	
	public static String RanDomMail() {
		String mail = "testauto-qa"; 
		String formatMail ="@gmail.com";
		String timeNow = java.time.LocalTime.now().toString();
		timeNow = timeNow.replace(".", ":").replace(":", "");
		return mail+timeNow+formatMail;
	}
	
	
}
