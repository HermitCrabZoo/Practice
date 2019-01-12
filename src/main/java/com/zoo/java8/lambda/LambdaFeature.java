package com.zoo.java8.lambda;

public class LambdaFeature {
	public static void main(String[] args) {
		
		LambdaFeature tester = new LambdaFeature();

        // 带有类型声明的表达式,实现MathOperation接口,重写operation方法
        MathOperation addition = (int a, int b) -> a + b;

        // 没有类型声明的表达式,实现MathOperation接口,重写operation方法
        MathOperation subtraction = (a, b) -> a - b;

        // 带有大括号、带有返回语句的表达式,实现MathOperation接口,重写operation方法
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号和return语句的表达式,实现MathOperation接口,重写operation方法
        MathOperation division = (int a, int b) -> a / b;

        // 输出结果
        System.out.println("100 + 2 = " + tester.operate(100, 2, addition));
        System.out.println("100 - 2 = " + tester.operate(100, 2, subtraction));
        System.out.println("100 x 2 = " + tester.operate(100, 2, multiplication));
        System.out.println("100 / 2 = " + tester.operate(100, 2, division));

        // 没有括号的表达式            
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 有括号的表达式            
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        // 调用sayMessage方法输出结果
        greetService1.sayMessage("Shiyanlou");
        greetService2.sayMessage("Classmate");
	}
	
	private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
     }
	//此为函数式接口
	interface MathOperation {
        int operation(int a, int b);
     }
	//此为函数式接口
     interface GreetingService {
        void sayMessage(String message);
     }
     
}
