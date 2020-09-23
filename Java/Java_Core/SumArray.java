package Java_Core;

import java.util.Scanner;

public class SumArray {

	public static void main(String[] args) {
		int[] arr = {100, 3, 99, -1, 4,101,2000};
		int max = arr[0];
		int thuTuMax = 0;
		
		int min = arr[0];
		int thuTuMin = 0;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				thuTuMax = i;
			}
		}
		System.out.println("MAX ==>" + max);
		System.out.println("Thu Tu MAX ==>" + thuTuMax);
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
				thuTuMin = i;
			}
		}
		System.out.println("MIN ==>" + min);
		System.out.println("Thu Tu MIN ==>" + thuTuMin);
	}

}
