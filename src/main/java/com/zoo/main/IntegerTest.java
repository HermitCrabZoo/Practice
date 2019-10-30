package com.zoo.main;

import java.util.Comparator;

public class IntegerTest {
    public static void main(String[] args) {
        compara();
    }
    static Integer integer;

    private static void compara(){
        //此比较器在比较两个相同数值的Integer类型时会出错，原因在于:
        //i<j的比较计算会导致i，j被拆箱，所以比较的是数值
        //i==j是对两个对象做同一性比较，是比较两个对象的地址
        Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);

        //这种调用方式会将参数自动装箱，且由于Integer的缓存机制，默认情况下传入的会是同一个对象，所以不会暴露问题，结果：0
        System.out.println(naturalOrder.compare(42, 42));
        //同样的调用方式，改用不同的参数，使默认情况下Integer的缓存机制无法生效于这些值，传入的不是同一个对象，所以暴露了问题，结果：1
        System.out.println(naturalOrder.compare(128, 128));
        //当然也可以通过制定"java.lang.Integer.IntegerCache.high"系统属性来设置Integer缓存的上边界(Integer默认缓存-128~127之间对应的Integer对象)，这时候可能造成上面的测试结果不同

        //这里会报空指针异常，因为：当在一项操作中混合使用基本类型和装箱基本类型，装箱基本类型就会自动拆箱。
        if(integer==42){
            System.out.println("Unbelievable");
        }
    }
}
