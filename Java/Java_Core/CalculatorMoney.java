package Java_Core;

import java.util.Scanner;

public class CalculatorMoney {

	public static void main(String[] args) {
		int[] model = { 10, 20, 50, 100, 200, 500 };
		Scanner input = new Scanner(System.in);
		System.out.print("Giá sản phẩm: ");
		int price = input.nextInt();

		System.out.print("Tiền đưa: "); 
		int money = input.nextInt();

		int tienthua = money - price;
		int thuong = 0;
		int du = 0;

		if (price == money) {
			System.out.println("Không có tiền thừa");
		} else {
			System.out.println("Tiền thừa: ");
			for (int i = model.length - 1; i > -1; i--) {
				thuong = tienthua / model[i];
				du = tienthua % model[i];
				if (thuong > 0) {
					System.out.println(thuong + " tờ " + model[i]);
				}
				tienthua = tienthua - (thuong * model[i]);
				if (du == 0) {
					break;
				}
			}
		}

	}

}
