package NutritionRecommender;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/***
 * This is for function validation test
 * @author Team-70
 *
 */
class ValidationTest {
	FoodLibrary library = new FoodLibrary();
		
	@Test
	/**
	 * Tests FoodLibrary Class: if we read foodLibrary from the source data-MyFoodData correctly:
	 * check if length == 16101
	 */
	public void test1() {
		HashMap<String, Food>  foodList = library.getLibrary();
		int length = 16101;
		assertEquals(length, foodList.size());
	}
	
	@Test
	/**
	 * Tests Food Class: if constructor with input string from source data works well:
	 * check if each elements is correct or not
	 * check if the volume calculation is correct for each serving
	 */
	public void test2() {
		HashMap<String, Food>  foodList = library.getLibrary();
		Food f = foodList.get("Bagels Wheat");
		double calories = f.getCalories();
		double fat = f.getFat();
		double protein = f.getProtein();
		double carbs = f.getCarbs();
		String servingDes = f.getServingDes();
		double servingWeight = f.getServingWeight();
		assertTrue(Math.abs(calories-250*0.98)<0.0001);
		assertTrue(Math.abs(fat-1.53*0.98)<0.0001);
		assertTrue(Math.abs(protein-10.2*0.98)<0.0001);
		assertTrue(Math.abs(carbs-48.89*0.98)<0.0001);
		assertEquals("1 Bagel",servingDes);
		assertTrue(Math.abs(servingWeight-98)<0.0001);
	}
	
	@Test
	/**
	 * Tests GuideLine Class: 
	 * check if we read guide line correctly from source data-calorie_guidline
	 * check if we calculate the fat/protein/carbs needed correctly
	 * check if test edge case works well (age>75)
	 */
	public void test3() {
		int age = 88;
		String activityLevel = "M";
		String gender = "F";
		String userName = "Louis";
		User p1 = new User(userName, age, gender,activityLevel);
		NutritionGuideline test1 = new NutritionGuideline(p1);
		int calories = (int) test1.getCalories();
		int protein = (int) test1.getProtein();
		int fat = (int) test1.getFat();
		int carbs = (int) test1.getCarbs();
		assertEquals(1800, calories);
		assertEquals(101, protein);
		assertEquals(55, fat);
		assertEquals(247, carbs);
	}
	
	@Test
	/**
	 * Test the method addFood and getPortion in FoodGroup Class:
	 * check if the addFood works well for adding/deleting food
	 * check if the getPortion can get correct portion for a given food
	 * check if the getPortion returns 0 if the food not in the group
	 */
	public void test4() {
		FoodGroup group = new FoodGroup();
		HashMap<String, Food>  foodList = library.getLibrary();
		Food foodInGroup = foodList.get("Bagels Wheat");
		Food foodNotInGroup = foodList.get("Whole Milk");
		group.addFood(foodInGroup, 12);
		group.addFood(foodInGroup, -12.5);
		double portionInGroup = group.getPortion(foodInGroup);
		double portionNotInGroup = group.getPortion(foodNotInGroup);
		assertTrue(Math.abs(-0.5-portionInGroup)<0.0001);
		assertTrue(Math.abs(portionNotInGroup)<0.0001);
	}
	
	@Test
	/**
	 * test the FoodFinder Class:
	 * check if we get correct sorted array for given input keywords
	 */
	public void test5() {
		FoodFinder finder = new FoodFinder();
		ArrayList<String> matchedNames = finder.getTopNMatched("whole  milk",3);
		ArrayList<String> answer = new ArrayList<>(Arrays.asList("Milk Whole","Whole Milk","Buttermilk Whole"));
		assertEquals(answer,matchedNames);
	}
}
