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
	double epsilon = 0.0001; //used for double comparison
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
		assertEquals(calories,250*0.98,epsilon);
		assertEquals(fat,1.53*0.98,epsilon);
		assertEquals(protein,10.2*0.98,epsilon);
		assertEquals(carbs,48.89*0.98,epsilon);
		assertEquals("1 Bagel",servingDes);
		assertEquals(servingWeight,98,epsilon);
	}
	
	@Test
	/**
	 * Tests Food Class: if constructor (fake food constructor) with nutrition as input works well:
	 * check if each elements is correct or not
	 * 1. calories, fat, protein, carbs should be the values of input values
	 * 2. others will be the default value (string for null, double for 0)
	 * this (fake) food constructor (where nutrition can be negative) will be used as functionality in the NutritionRecommder
	 * test the negative nutirtion input
	 */
	public void test3() {
		double calories = 101;
		double fat = 16.12;
		double protein = -1.001;
		double carbs = 10;
		Food f = new Food(calories, fat, protein, carbs);
		assertEquals(calories, f.getCalories(), epsilon);
		assertEquals(fat, f.getFat(), epsilon);
		assertEquals(protein, f.getProtein(), epsilon);
		assertEquals(carbs, f.getCarbs(), epsilon);
		System.out.println(f.getName());
		assertTrue(f.getName()==null);
		assertEquals(f.getSugars(),0,epsilon);
	}
	
	@Test
	/**
	 * Tests GuideLine Class: 
	 * check if we read guide line correctly from source data-calorie_guidline
	 * check if we calculate the fat/protein/carbs needed correctly
	 * check if test edge case works well (age>75)
	 */
	public void test4() {
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
	 * Test the method addFood and getPortion in the FoodGroup Class:
	 * check if the addFood works well for adding/deleting food
	 * check if the getPortion can get correct portion for a given food
	 * check if the getPortion returns 0 if the food not in the group
	 */
	public void test5() {
		FoodGroup group = new FoodGroup();
		HashMap<String, Food>  foodList = library.getLibrary();
		Food foodInGroup = foodList.get("Bagels Wheat");
		Food foodNotInGroup = foodList.get("Whole Milk");
		group.addFood(foodInGroup, 12);
		group.addFood(foodInGroup, -12.5);
		double portionInGroup = group.getPortion(foodInGroup);
		double portionNotInGroup = group.getPortion(foodNotInGroup);
		assertEquals(-0.5,portionInGroup,epsilon);
		assertEquals(portionNotInGroup,0,epsilon);
	}
	
	@Test
	/**
	 * Test the method addFood for edge case in the FoodGroup Class:
	 * check if the getPortion can successfully remove a food from the group
	 * check if the food name still in portion, name, food ArrayList/HashMap
	 */
	public void test6() {
		FoodGroup group = new FoodGroup();
		HashMap<String, Food>  foodList = library.getLibrary();
		Food food = foodList.get("Alfalfa Sprouts");
		group.addFood(food, 12);
		group.addFood(food, -12);
		double portion = group.getPortion(food);
		assertEquals(portion, 0, epsilon);
		assertFalse(group.getFoodPortion().containsKey(food.getName()));
		assertFalse(group.getFoodDetail().containsKey(food.getName()));
		assertFalse(group.getMeal().contains(food.getName()));
	}
	
	@Test
	/**
	 * Test the properties (Nutrition) in the FoodGroup Class:
	 * check if the volume calculation is correct for each serving
	 * check if we get correct sorted array for given input keywords
	 */
	public void test7() {
		FoodGroup group = new FoodGroup();
		HashMap<String, Food>  foodList = library.getLibrary();
		Food food1 = foodList.get("Watermelon");
		Food food2 = foodList.get("Restaurant Latino Tripe Soup");
		Food food3 = foodList.get("Frozen Yogurts Chocolate");
		group.addFood(food1, 1.5);
		group.addFood(food2, 1.25);
		group.addFood(food3, 4.00);
		double protion = 43.8141;
		double calories = 1166.06;
		double fat = 31.8525;
		double carbs = 177.9515;
		assertEquals(protion,group.getProtein(),epsilon);
		assertEquals(fat,group.getFat(),epsilon);
		assertEquals(calories,group.getCalories(),epsilon);
		assertEquals(carbs,group.getCarbs(),epsilon);
	}
	
	@Test
	/**
	 * Test the FoodFinder Class:
	 * check if the getTopNMatch can get correct sorted matched food names ArrayList back or not
	 */
	public void test8() {
		FoodFinder finder = new FoodFinder();
		ArrayList<String> matchedNames = finder.getTopNMatched("whole  milk",3);
		ArrayList<String> answer = new ArrayList<>(Arrays.asList("Milk Whole","Whole Milk","Buttermilk Whole"));
		assertEquals(answer,matchedNames);
	}
	
	@Test
	/**
	 * Test the Ratios methods in NutritionCalculator Class
	 * Check if the ProteinRatio, FatRatio, CarbsRatio can get correct answers
	 */
	public void test9() {
		Food food = new Food(100, 20, 30, 40);
		double proteinRatio = NutritionCalculator.proteinRatio(food);
		double fatRatio = NutritionCalculator.fatRatio(food);
		double carbsRatio = NutritionCalculator.carbsRatio(food);
		assertEquals(fatRatio,0.2,epsilon);
		assertEquals(proteinRatio,0.3,epsilon);
		assertEquals(carbsRatio,0.4,epsilon);
	}
	
	@Test
	/**
	 * Test the Ratios methods in NutritionCalculator Class for edge case
	 * Check if the ProteinRatio, FatRatio, CarbsRatio can get correct answers
	 * it should return Double.MAX_VALUE
	 */
	public void test10() {
		Food food = new Food(0, 20, 30, 40);
		double proteinRatio = NutritionCalculator.proteinRatio(food);
		double fatRatio = NutritionCalculator.fatRatio(food);
		double carbsRatio = NutritionCalculator.carbsRatio(food);
		assertEquals(fatRatio,Double.MAX_VALUE,epsilon);
		assertEquals(proteinRatio,Double.MAX_VALUE,epsilon);
		assertEquals(carbsRatio,Double.MAX_VALUE,epsilon);
	}
	
	@Test
	/**
	 * Test the nutritionRatioSimilarity method in NutritionCalculator Class
	 * Check if the NutritionSimilarity method can get correct answer
	 */
	public void test11() {
		Food food1 = new Food(100, 10, 10, 10);
		Food food2 = new Food(100, 20, 30, 40);
		double similarity = NutritionCalculator.nutritionRatioSimiliarity(food1,food2);
		assertEquals(similarity,0.37416573,epsilon);
	}
	
	@Test
	/**
	 * Test the nutritionGap method in NutritionCalculator Class
	 * Check if the method can get correct answer (test for negative answer)
	 */
	public void test12() {
		Food meal = new Food(100, 10, 10, 10);
		Food guide = new Food(50, 20, 30, 40);
		Food anwser = new Food(-50,10,20,30);
		Food gap = NutritionCalculator.nutritionGap(meal,guide);
		assertEquals(anwser.getCalories(),gap.getCalories(),epsilon);
		assertEquals(anwser.getFat(),gap.getFat(),epsilon);
		assertEquals(anwser.getProtein(),gap.getProtein(),epsilon);
		assertEquals(anwser.getCarbs(),gap.getCarbs(),epsilon);
	}
	
	@Test
	/**
	 * Test the isSimiliar method in NutritionCalculator Class
	 * Check if the method can works for different acceptable errors
	 */
	public void test13() {
		Food meal = new Food(100,90,95,120);
		Food guide = new Food(100,100,100,100);
		assertTrue(NutritionCalculator.isSimiliar(meal,guide,0.2));
		assertFalse(NutritionCalculator.isSimiliar(meal,guide,0.1));
		assertFalse(NutritionCalculator.isSimiliar(meal,guide,0.05));
	}
	
	@Test
	/**
	 * Test the dropExtratFood (for each nutrition) methods in NutritionCalculator Class
	 * check if the dropExtratFood for each nutrition methods work
	 */
	public void test14() {
		Food food1 = new Food(100,0,0,0);
		Food food2 = new Food(0,100,0,0);
		Food food3 = new Food(0,0,100,0);
		Food food4 = new Food(0,0,0,100);
		Food guide = new Food(1000, 0, 330, 950);
		food1.servingWeight = 1;
		food2.servingWeight = 1;
		food3.servingWeight = 1;
		food4.servingWeight = 1;
		food1.name = "1";
		food2.name = "2";
		food3.name = "3";
		food4.name = "4";
		
		FoodGroup group = new FoodGroup();
		group.addFood(food1, 11.1);
		group.addFood(food2, 50);
		group.addFood(food3, 3.5);
		group.addFood(food4, 12.9);
		double error = 0.1; //accept 10% difference
		
		group = NutritionCalculator.dropExtraFoodOnCalories(group, guide, error);
		assertEquals(group.getPortion(food1),10,epsilon);
		group = NutritionCalculator.dropExtraFoodOnFat(group, guide, error);
		assertEquals(group.getPortion(food2),0,epsilon);
		group = NutritionCalculator.dropExtraFoodOnProtein(group, guide, error);
		assertEquals(group.getPortion(food3),3,epsilon);
		group = NutritionCalculator.dropExtraFoodOnCarbs(group, guide, error);
		assertEquals(group.getPortion(food4),10,epsilon);
	}
	
	@Test
	/**
	 * Test the dropExtratFood (for each nutrition) methods in NutritionCalculator Class
	 * check if the dropExtratFood for each nutrition methods work
	 */
	public void test15() {
		Food food1 = new Food(100,0,0,0);
		Food food2 = new Food(0,100,0,0);
		Food food3 = new Food(0,0,100,0);
		Food food4 = new Food(0,0,0,100);
		Food guide = new Food(1000, 0, 330, 950);
		food1.servingWeight = 1;
		food2.servingWeight = 1;
		food3.servingWeight = 1;
		food4.servingWeight = 1;
		food1.name = "1";
		food2.name = "2";
		food3.name = "3";
		food4.name = "4";
		
		FoodGroup group = new FoodGroup();
		group.addFood(food1, 11.1);
		group.addFood(food2, 50);
		group.addFood(food3, 3.5);
		group.addFood(food4, 12.9);
		
		double error = 0.1; //accept 10% difference
		
		group = NutritionCalculator.dropExtraFood(group, guide, error);
		assertEquals(group.getPortion(food1),10,epsilon);
		assertEquals(group.getPortion(food2),0,epsilon);
		assertEquals(group.getPortion(food3),3,epsilon);
		assertEquals(group.getPortion(food4),10,epsilon);
	}
	
	@Test
	/**
	 * Test the getSuggestedFood methods in NutritionCalculator Class
	 * check if it can get correct suggested foodGroup by giving initial food group
	 */
	public void test16() {
		HashMap<String, Food>  foodList = library.getLibrary();;
		FoodGroup group = new FoodGroup();
		Food food1 = foodList.get("Bagels Wheat");
		Food food2 = foodList.get("Whole Milk");
		Food food3 = foodList.get("Fish Sandwich With Tartar Sauce");
		Food food4 = foodList.get("Apples (Without Skin)");
		Food food5 = foodList.get("Beef Short Loin Porterhouse Steak Separable Lean And Fat Trimmed To 1/8 Inch Fat All Grades Cooked Grilled");
		
		group.addFood(food1,1);
		group.addFood(food2,2);
		group.addFood(food3,1);
		group.addFood(food4,2);
		group.addFood(food5,1);
		int age = 88;
		String activityLevel = "M";
		String gender = "F";
		String userName = "Louis";
		User p1 = new User(userName, age, gender,activityLevel);
		NutritionGuideline guide = new NutritionGuideline(p1);
		FoodGroup suggestion = NutritionCalculator.getSuggestedFood(group,guide,foodList,0.1);
		assertEquals(group.getPortion(food1),1,epsilon);
		assertEquals(group.getPortion(food2),1,epsilon);
		assertEquals(group.getPortion(food3),1,epsilon);
		assertEquals(group.getPortion(food4),2,epsilon);
		assertEquals(group.getPortion(food5),1,epsilon);
		assertEquals(group.getPortion(foodList.get("Cowpeas Young Pods With Seeds Cooked Boiled Drained Without Salt")),15.497213622,epsilon);

	}
	
	
	
}
