package Java_Core;

import java.util.Scanner;

public class RotateArray {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input row: ");
		int hang = input.nextInt();

		System.out.print("Input column: ");
		int cot = input.nextInt();

		int[][] arr = new int[hang][cot];
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < cot; j++) {
				System.out.print("Array[" + i + "][" + j + "]: ");
				arr[i][j] = input.nextInt();
			}
		}
		//  XUẤT
		System.out.println("Output: ");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {

				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		// Lật mảng về bên trái
		System.out.println("Rotating output 1: ");
		for (int m = (cot - 1); m > -1 ; m--) {
			for (int i = 0; i < hang; i++) {
				System.out.print(arr[i][m] + " ");
			}
			System.out.println();
		}
		// Lật mảng về bên phải
		System.out.println("Rotating output 2: ");
		for (int m = 0; m < cot ; m++) {
			for (int i = (hang-1); i > -1 ; i--) {
				System.out.print(arr[i][m] + " ");
			}
			System.out.println();
		}
	}
}
