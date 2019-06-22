package com.zoo.enumeration;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class EnumMapDemo {
	enum Color {
	    GREEN,RED,BLUE,YELLOW
	}
	static class Clothes{
		private String name;
		private Color color;
		
		public Clothes(String name, Color color) {
			this.name = name;
			this.color = color;
		}

		public String getName() {
			return name;
		}

		public Color getColor() {
			return color;
		}
		
	}
	public static void main(String[] args){
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes("C001",Color.BLUE));
        list.add(new Clothes("C002",Color.YELLOW));
        list.add(new Clothes("C003",Color.RED));
        list.add(new Clothes("C004",Color.GREEN));
        list.add(new Clothes("C005",Color.BLUE));
        list.add(new Clothes("C006",Color.BLUE));
        list.add(new Clothes("C007",Color.RED));
        list.add(new Clothes("C008",Color.YELLOW));
        list.add(new Clothes("C009",Color.YELLOW));
        list.add(new Clothes("C010",Color.GREEN));
        //方案1:使用HashMap
        Map<String,Integer> map = new HashMap<>();
        for (Clothes clothes:list){
           String colorName=clothes.getColor().name();
           Integer count = map.get(colorName);
            if(count!=null){
                map.put(colorName,count+1);
            }else {
                map.put(colorName,1);
            }
        }
        System.out.println(map.toString());
        System.out.println("---------------");

        //方案2:使用EnumMap
        Map<Color,Integer> enumMap=new EnumMap<>(Color.class);

        for (Clothes clothes:list){
            Color color=clothes.getColor();
            Integer count = enumMap.get(color);
            if(count!=null){
                enumMap.put(color,count+1);
            }else {
                enumMap.put(color,1);
            }
        }
        System.out.println(enumMap.toString());
    }

    /**
     输出结果:
     {RED=2, BLUE=3, YELLOW=3, GREEN=2}
     ---------------
     {GREEN=2, RED=2, BLUE=3, YELLOW=3}
     */
}

