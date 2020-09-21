package Java_Core;

import java.util.ArrayList;

public class Pattern_FullName {

	public static void main(String[] args) {
		String fullName = "   automation    testing            selenium         ";

		String name1 = fullName.trim();

		char[] temp = name1.toCharArray();
//		if (Character.isSpaceChar(temp[14])) {
//			
//			System.out.println(Character.toUpperCase(temp[15]));
//			
//
//		}

		for (int i = 0; i < temp.length; i++) {
			if (Character.isSpaceChar(temp[i])) {
				Character.toUpperCase(temp[i + 1]);
			}
			System.out.println(temp[i]);

		}

	}

	public static String UpperCaseName() {
		return "";
	}

	public static String CutSpace() {
		return "";
	}
}
