package Java_Core;

import java.util.ArrayList;
import java.util.Scanner;

public class RotateArray_part2 {
	static Scanner input = new Scanner(System.in);
	static int k_step;

	public static void main(String[] args) {

		int[] arr = {1,2,3,4,5,6,7,8,9};

		ArrayList<Integer> listTemp = new ArrayList<Integer>();
		System.out.print("Input k: ");
		int so_k = input.nextInt();
		check_K(so_k);
		
		// Cach 1
		for (int i = (arr.length - k_step); i < arr.length; i++) {
			listTemp.add(arr[i]);
		}
		for (int i = 0; i < (arr.length - k_step); i++) {
			listTemp.add(arr[i]);
		}

		for (Integer j : listTemp) {
			System.out.print(j + " ");
		}

	}

	public static void check_K(int k) {
		if (k > 0 || k == 0) {
			k_step = k;
		} else {
			System.out.print("nhap lai: ");
			k = input.nextInt();
			check_K(k);
		}
	}
}
