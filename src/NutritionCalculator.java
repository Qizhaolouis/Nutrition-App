import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
/***
 * This class includes the methods to calculate the difference between foods inputed and nutrition needed, 
 * and recommend foods adds/deletes for the user, based on user's target, and user's input daily meals
 * @author Team-70
 *
 */
public class NutritionCalculator {
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for calories.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double caloriesGap(Nutrition meal, Nutrition guide) {
		// To be developed
		return guide.getCalories() - meal.getCalories();
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for protein.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double proteinGap(Nutrition meal, Nutrition guide) {
		// To be developed
		return guide.getProtein() - meal.getProtein();
	}
	
	public static double proteinRatio(Nutrition meal) {
		if (meal.getCalories()==0) {
			return Double.MAX_VALUE;
		} else {
			return meal.getProtein()/meal.getCalories();
		}
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for fat.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double fatGap(Nutrition meal, Nutrition guide) {
		return guide.getFat() - meal.getFat();
	}
	
	public static double fatRatio(Nutrition meal) {
		if (meal.getCalories()==0) {
			return Double.MAX_VALUE;
		} else {
			return meal.getFat()/meal.getCalories();
		}
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for carbs.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double carbsGap(Nutrition meal, Nutrition guide) {
		// To be developed
		return guide.getCarbs() - meal.getCarbs();
	}
	
	public static double carbsRatio(Nutrition meal) {
		if (meal.getCalories()==0) {
			return Double.MAX_VALUE;
		} else {
			return meal.getCarbs()/meal.getCalories();
		}
	}
	
	public static double nutritionRatioSimiliarity(Nutrition meal1, Nutrition meal2) {
		double proteinRatioDist = proteinRatio(meal1) - proteinRatio(meal2);
		double fatRatioDist = fatRatio(meal1) - fatRatio(meal2);
		double carbsRatioDist = carbsRatio(meal1) - carbsRatio(meal2);
		double similiarilty = Math.sqrt(Math.pow(proteinRatioDist,2) 
				                      + Math.pow(fatRatioDist,2) 
				                      + Math.pow(carbsRatioDist,2));
		
		// To be developed
		return similiarilty;
	}
	
	public static boolean isSimiliar(Nutrition meal, Nutrition guide, double error) {
		if (Math.abs(caloriesGap(meal,guide)/guide.getFat())>error) return false;
		if (Math.abs(fatGap(meal,guide))/guide.getFat()>error) return false;
		if (Math.abs(carbsGap(meal,guide))/guide.getCarbs()>error) return false;
		if (Math.abs(proteinGap(meal,guide))/guide.getProtein()>error) return false;
		return true;
	}
	
	public static boolean isNutritionExcess(Nutrition meal, Nutrition guide) {
		if (caloriesGap(meal,guide)<0) return false;
		if (fatGap(meal,guide)<0) return false;
		if (carbsGap(meal,guide)<0) return false;
		if (proteinGap(meal,guide)<0) return false;
		return true;
	}
	
	
	
	public static Food nutritionGap(Nutrition meal, Nutrition guide) {
		double caloriesGap = caloriesGap(meal,guide);
		double proteinGap = proteinGap(meal,guide);
		double fatGap = fatGap(meal,guide);
		double carbsGap = carbsGap(meal,guide);
		return new Food(caloriesGap,fatGap,proteinGap,carbsGap);
	}
	
	/**
	 * Gives the suggested food to be added to the meal
	 * @param calories difference from guideline
	 * @param protein difference from guideline
	 * @param fat difference from guideline
	 * @param carbs difference from guideline
	 * @return suggested Food
	 */
	public static FoodGroup getSuggestedFood(FoodGroup group, Nutrition guide, HashMap<String, Food> library,double error) {
		FoodGroup suggestedFoodGroup = dropExtraFood(group, guide, error);
		if (isSimiliar(suggestedFoodGroup,guide,error)) return suggestedFoodGroup;
		Food nutritionGap = nutritionGap(suggestedFoodGroup, guide);
		double min = Double.MAX_VALUE;
		double similarity;
		Food suggestedFood = null;
		for (String food : library.keySet()) {
			similarity = nutritionRatioSimiliarity(nutritionGap,library.get(food));
			if (similarity <min) {
				min = similarity;
				suggestedFood = library.get(food);
			}
		}
		suggestedFoodGroup.addFood(suggestedFood, nutritionGap.getCalories()/suggestedFood.getCalories());
		//System.out.println(suggestedFood.getName()+":" + nutritionGap.getCalories()/suggestedFood.getCalories());
		return suggestedFoodGroup;
	}
	
	public static FoodGroup dropExtraFood(FoodGroup group, Nutrition guide, double error) {
		Food gap = nutritionGap(group, guide);
		Food tempFood = null;
		double tempPortion = 0;
		while (gap.getCalories()<0) {
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			for (Food f: foods) {
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getCalories() * portion - gap.getCalories());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		while (gap.getFat()<0) {
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			for (Food f: foods) {
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getFat() * portion - gap.getFat());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		while (gap.getProtein()<0) {
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			for (Food f: foods) {
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getProtein() * portion - gap.getProtein());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		while (gap.getCarbs()<0) {
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			for (Food f: foods) {
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getCarbs() * portion - gap.getCarbs());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
					System.out.println(3);
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		ArrayList<Food> kk = new ArrayList<>(group.getFoodDetail().values());
		for (Food f: kk) {
			System.out.println(f.getName());
			System.out.println(group.getFoodPortion().get(f.getName()));
		}
		return group;
	}
	
	public static FoodGroup FoodGroupDifference(FoodGroup group1, FoodGroup group2) {
		HashMap<String,Food> foodDetail2 = group2.getFoodDetail();
		HashMap<String,Double> foodPortion2 = group2.getFoodPortion();
		ArrayList<Food> foods = new ArrayList<Food>();
		for (String foodName : foodDetail2.keySet()) {
			group1.addFood(foodDetail2.get(foodName), -foodPortion2.get(foodName));
		}
		return group1;
	}
	
	
	public static void main(String[] args) {
		FoodLibrary foods = new FoodLibrary();
		HashMap<String, Food>  foodList = foods.getLibrary();
		FoodGroup a = new FoodGroup();
		a.addFood(foodList.get("Bagels Wheat"),1);
		a.addFood(foodList.get("Whole Milk"),15);
		int age = 88;
		String activityLevel = "M";
		String gender = "F";
		String userName = "Louis";
		User p1 = new User(userName, age, gender,activityLevel);
		NutritionGuideline guide = new NutritionGuideline(p1);
		FoodGroup add = getSuggestedFood(a, guide,foodList,0.1);
		boolean out = isSimiliar(add,guide,0.1);
		System.out.println(out);
		ArrayList<Food> kk = new ArrayList<>(add.getFoodDetail().values());
		for (Food f: kk) {
			System.out.println(f.getName());
			System.out.println(add.getFoodPortion().get(f.getName()));
		}
	}
}