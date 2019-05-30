package com.zoo.enumeration;

import com.zoo.enumeration.Food.Appetizer;
import com.zoo.enumeration.Food.Coffee;
import com.zoo.enumeration.Food.Dessert;
import com.zoo.enumeration.Food.MainCourse;

interface Food {
	  enum Appetizer implements Food {
	    SALAD, SOUP, SPRING_ROLLS;
	  }
	  enum MainCourse implements Food {
	    LASAGNE, BURRITO, PAD_THAI,
	    LENTILS, HUMMOUS, VINDALOO;
	  }
	  enum Dessert implements Food {
	    TIRAMISU, GELATO, BLACK_FOREST_CAKE,
	    FRUIT, CREME_CARAMEL;
	  }
	  enum Coffee implements Food {
	    BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
	    LATTE, CAPPUCCINO, TEA, HERB_TEA;
	  }
}


public class TypeOfFood {

	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
	    food = MainCourse.LASAGNE;
	    food = Dessert.GELATO;
	    food = Coffee.CAPPUCCINO;
	}

}
