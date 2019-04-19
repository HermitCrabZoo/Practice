package com.zoo.main;

public class Christmas {

	public static void main(String[] args)
	{
		
		goldenTriangle3(4);
		goldenTriangle2(6);
		goldenTriangle1(8);
		goldenTriangle0(5);
	}
	public static void goldenTriangle3(int n)
	{
		int a=n;
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=2*a-2+8;j++)
				System.out.print(" ");
			a--;
			for(int k=1;k<=4*i-3;k++)
				System.out.print("*");
			System.out.println();
		}
	}
	public static void goldenTriangle2(int n)
	{
		int a=n;
		for(int i=2;i<=n;i++)
		{
			for(int j=1;j<=2*a-2+2;j++)
				System.out.print(" ");
			a--;
			for(int k=1;k<=4*i-3;k++)
				System.out.print("*");
			System.out.println();
		}
	}
	public static void goldenTriangle1(int n)
	{
		int a=n-2;
		for(int i=3;i<=n;i++)
		{
			for(int j=1;j<=2*a-2;j++)
				System.out.print(" ");
			a--;
			for(int k=1;k<=4*i-3;k++)
				System.out.print("*");
			System.out.println();
		}
	}
	public static void goldenTriangle0(int n)
	{
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=13;j++)
				System.out.print(" ");
			for(int k=1;k<=3;k++)
				System.out.print("*");
			System.out.println();
		}
	}

}
