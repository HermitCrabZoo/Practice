package com.zoo.enumeration;

enum Day {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumDemo {

	public static void main(String[] args) {
		//创建枚举数组
        Day[] days=new Day[]{Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY,
                Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};

        for (int i = 0; i <days.length ; i++) {
            System.out.println("day["+i+"].ordinal():"+days[i].ordinal());
        }

        System.out.println("-------------------------------------");
        //通过compareTo方法比较,实际上其内部是通过ordinal()值比较的
        System.out.println("days[0].compareTo(days[1]):"+days[0].compareTo(days[1]));
        System.out.println("days[0].compareTo(days[1]):"+days[0].compareTo(days[2]));

        //获取该枚举对象的Class对象引用,当然也可以通过getClass方法
        Class<?> clazz = days[0].getDeclaringClass();
        System.out.println("clazz:"+clazz);

        System.out.println("-------------------------------------");

        //name()
        System.out.println(String.format("days[0].name():%s\tdays[0].toString():%s", days[0].name(), days[0].toString()));
        System.out.println(String.format("days[1].name():%s\tdays[1].toString():%s", days[1].name(), days[1].toString()));
        System.out.println(String.format("days[2].name():%s\tdays[2].toString():%s", days[2].name(), days[2].toString()));
        System.out.println(String.format("days[3].name():%s\tdays[3].toString():%s", days[3].name(), days[3].toString()));

        System.out.println("-------------------------------------");

        Day d=Enum.valueOf(Day.class,days[0].name());
        Day d2=Day.valueOf(Day.class,days[0].name());
        System.out.println("d:"+d);
        System.out.println("d2:"+d2);
        // 结果：
//        day[0].ordinal():0
//        day[1].ordinal():1
//        day[2].ordinal():2
//        day[3].ordinal():3
//        day[4].ordinal():4
//        day[5].ordinal():5
//        day[6].ordinal():6
//        -------------------------------------
//        days[0].compareTo(days[1]):-1
//        days[0].compareTo(days[1]):-2
//        clazz:class com.zoo.enumeration.Day
//        -------------------------------------
//        days[0].name():MONDAY	days[0].toString():MONDAY
//        days[1].name():TUESDAY	days[1].toString():TUESDAY
//        days[2].name():WEDNESDAY	days[2].toString():WEDNESDAY
//        days[3].name():THURSDAY	days[3].toString():THURSDAY
//        -------------------------------------
//        d:MONDAY
//        d2:MONDAY
	}
}
