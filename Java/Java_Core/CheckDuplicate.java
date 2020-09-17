package Java_Core;

import java.util.ArrayList;
import java.util.List;

public class CheckDuplicate {

	public static void main(String[] args) {
		String[] arr = { "August", "April", "September", "April", "Jane", "May", "July", "September" };
		checkDuplicate_String(arr);
		
		Integer[] arr1 = {1,2,3,4,5,2,8,9,4,6};
		checkDuplicate_Integer(arr1);
	}

	public static void checkDuplicate_String(String[] Stringvalue) {
	
		ArrayList<String> listString = new ArrayList<String>();
		boolean check = false;
		listString.add(Stringvalue[0]);

		for (int i = 1; i < Stringvalue.length; i++) {
			for (int j = 0; j < listString.size(); j++) {
				if (Stringvalue[i].equals(listString.get(j))) {
					check = false;
					break;
				} else {
					check = true;
				}
			}
			if (check == true) {
				listString.add(Stringvalue[i]);
			}
			check = false;
		}
		for (String a : listString) {
			System.out.println(a);
		}
	}

	public static void checkDuplicate_Integer(Integer[] Integervalue) {

		ArrayList<Integer> listString = new ArrayList<Integer>();
		boolean check = false;
		listString.add(Integervalue[0]);

		for (int i = 1; i < Integervalue.length; i++) {
			for (int j = 0; j < listString.size(); j++) {
				if (Integervalue[i].equals(listString.get(j))) {
					check = false;
					break;
				} else {
					check = true;
				}
			}
			if (check == true) {
				listString.add(Integervalue[i]);
			}
			check = false;
		}
		for (Integer a : listString) {
			System.out.println(a);
		}
	}

}
