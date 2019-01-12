package com.zoo.main;
import org.junit.Test;
public class Junit4Test {

	@Test
	public void test()
	{
		System.out.println("log1p:"+Math.log1p(99));//以自然对数e为底"参数"+1的对数
		System.out.println("log:"+Math.log(100));//以自然对数e为底"参数"的对数
		System.out.println("log10:"+Math.log10(2));//以10为底"参数"的对数
		System.out.println("cbrt:"+Math.cbrt(27.0));//"参数"的立方根
		System.out.println("sqrt:"+Math.sqrt(5.0));//"参数"的平方根
		System.out.println("sin:"+Math.sin(0.0));//"参数"的sin值
		System.out.println("cos:"+Math.cos(0.0));//"参数"的cos值
		System.out.println("tan:"+Math.tan(0.0));//"参数"的tan值
		System.out.println("sin endsWith  sin:"+"sin".endsWith("sin"));//以"参数"结尾？
		System.out.println("si endsWith sin:"+"si".endsWith("sin"));//以"参数"结尾？
		
		
	}
	//递归求整数阶乘
	public double c(int num)
	{
		double sum=1;
		if(num<=0)
		{
			System.out.println(num+"");
			return sum;
		}else{
			
			return sum=num*c(num-1);
		}
	}
	@Test
	public void test2()
	{
		final String initStr=
	            "360000000"+
	            "004230800"+
	            "000004200"+
	            "007046003"+
	            "820000014"+
	            "500013020"+
	            "001900000"+
	            "007048300"+
	            "000000045";
	        int i;
	        int temp[]=new int[initStr.length()];
	        System.out.println("转换字符串到int数组前："+System.currentTimeMillis());
	        for(i=0;i<81;i++)
	        {
	            temp[i]=initStr.charAt(i)-'0';
	        }

	        System.out.println("转换字符串到int数组后："+System.currentTimeMillis());
	        
	        int temp2[]=new int[temp.length];
	        for(i=0;i<temp.length;i++)
	        {
	            temp2[i]=temp[i];
	        }
	        System.out.println("初始的temp数组");
	        for(i=0;i<temp.length;i++)
	        {
	            System.out.print(temp[i]);
	        }
	        
	        System.out.println("初始的temp2数组");
	        for(i=0;i<temp2.length;i++)
	        {
	            System.out.print(temp2[i]);
	        }
	        temp[0]=0;
	        temp[1]=0;
	        System.out.println("改变后的temp数组");
	        for(i=0;i<temp.length;i++)
	        {
	            System.out.print(temp[i]);
	        }
	        System.out.println("改变后的temp2数组");
	        for(i=0;i<temp2.length;i++)
	        {
	            System.out.print(temp2[i]);
	        }
	        
	        System.out.println("new int[81].toString():"+temp.toString());
	}
}
