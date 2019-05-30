package com.zoo.enumeration;

enum Color {
	GREEN, RED, BLUE
}

public class EnumSwitch {
	public static void showColor(Color color) {
		switch (color) {
		case BLUE: // 无需使用Color进行引用
			System.out.println("蓝色");
			break;
		case RED:
			System.out.println("红色");
			break;
		case GREEN:
			System.out.println("绿色");
			break;
		}
	}

	public static void main(String[] args) {
		showColor(Color.BLUE);
		showColor(Color.RED);
		showColor(Color.GREEN);
		// 蓝色
		// 红色
		// 绿色
	}

}
