package com.zoo.jvm;

import java.lang.reflect.InvocationTargetException;

/**
 * 类加载：双亲委托机制，先通过父加载器加载，若加载成功则返回Class实例，否则自己加载。
 * 根类加载器：Bootstrap
 * ---------扩展类加载器：Extension^
 * --------------------系统类加载器：System^
 * -------------------------------自定义加载器^
 */
public class TestJVM {

    public static void main(String[] args) {
        MyClassLoader loader1 = new MyClassLoader("loader1");//父加载器为：System
        loader1.setPath("E:\\Eclipse\\Practice\\bin\\main\\com\\zoo\\jvm");

        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");//父加载器为:loader1
        loader2.setPath("E:\\Eclipse\\Practice\\bin\\main\\com\\zoo\\jvm");

        MyClassLoader loader3 = new MyClassLoader(null, "loader3");//父加载器为：Bootstrap
        loader3.setPath("E:\\Eclipse\\Practice\\bin\\main");

        //委托父加载器加载成功
        load(loader2, "com.zoo.jvm.Dog");
        load(loader2, "com.zoo.jvm.Cat");

        //父加载器无法加载,自己加载成功
        load(loader3, "com.zoo.jvm.Dog");
        load(loader3, "com.zoo.jvm.Cat");

        System.out.println(System.getProperty("sun.boot.class.path"));//Bootstrap加载的目录或jar
        System.out.println(System.getProperty("java.ext.dirs"));//Extension加载的目录或jar
        System.out.println(System.getProperty("java.class.path"));//System加载的目录或jar

    }

    @SuppressWarnings("unused")
    public static void load(ClassLoader loader, String name) {
        try {
            Class<?> clazz = loader.loadClass(name);
//			Object obj=clazz.newInstance();//java9开始过期
            Object obj = clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 所谓的热部署就是利用同一个class文件不同的类加载器在内存创建出两个不同的class对象，由于JVM在加载类之前会检测请求的类是否已加载过(即在loadClass()方法中调用findLoadedClass()方法)，
     * 如果被加载过，则直接从缓存获取，不会重新加载。注意同一个类加载器的实例和同一个class文件只能被加载器一次，多次加载将报错，
     * 因此我们实现的热部署必须让同一个class文件可以根据不同的类加载器重复加载，以实现所谓的热部署。
     * 实际上MyClassLoader已具备这个功能，但前提是直接调用findClass()方法，而不是调用loadClass()方法，因为ClassLoader中loadClass()方法体中调用findLoadedClass()方法进行了检测是否已被加载，
     * 因此我们直接调用findClass()方法就可以绕过这个问题，当然也可以重新loadClass方法，但强烈不建议这么干。
     */
    private static void hotLoad() {
        String rootDir = "/Users/zejian/Downloads/Java8_Action/src/main/java/";
        //创建自定义文件类加载器
        MyClassLoader loader = new MyClassLoader(rootDir);
        MyClassLoader loader2 = new MyClassLoader(rootDir);

        try {
            //加载指定的class文件,调用loadClass()
            Class<?> object1 = loader.loadClass("com.zejian.classloader.DemoObj");
            Class<?> object2 = loader2.loadClass("com.zejian.classloader.DemoObj");

            System.out.println("loadClass->obj1:" + object1.hashCode());
            System.out.println("loadClass->obj2:" + object2.hashCode());

            //加载指定的class文件,直接调用findClass(),绕过检测机制，创建不同class对象。
            Class<?> object3 = loader.findClass("com.zejian.classloader.DemoObj");
            Class<?> object4 = loader2.findClass("com.zejian.classloader.DemoObj");

            System.out.println("loadClass->obj3:" + object3.hashCode());
            System.out.println("loadClass->obj4:" + object4.hashCode());

            /*
             * 输出结果:
             * loadClass->obj1:644117698
             * loadClass->obj2:644117698
             * findClass->obj3:723074861
             * findClass->obj4:895328852
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
