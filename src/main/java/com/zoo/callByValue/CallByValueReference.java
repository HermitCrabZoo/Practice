package com.zoo.callByValue;

/**
 * java中的引用数据类型作为方法参数的按值调用
 * 
 * @author Devil
 */
public class CallByValueReference {
	private static User user = null;

	public static void updateUser(User student) {
		student.setName("Lishen");
		student.setAge(18);
	}

	public static void main(String[] args) {
		user = new User("zhangsan", 26);
		System.out.println("调用前user的值：" + user.toString());
		updateUser(user);
		System.out.println("调用后user的值：" + user.toString());
		//调用前user的值：User [name=zhangsan, age=26]
		//调用后user的值：User [name=Lishen, age=18]
	}
}

class User {
	private String name;
	private int age;

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
}
