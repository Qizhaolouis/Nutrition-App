package NutritionRecommender;
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
		return guide.getCalories() - meal.getCalories();
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for protein.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double proteinGap(Nutrition meal, Nutrition guide) {
		return guide.getProtein() - meal.getProtein();
	}
	
	/**
	 * Calculates the protein to calories ratio for a given meal
	 * @param meal
	 * @return the protein to calories ratio
	 */
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
	
	/**
	 * Calculates the fat to calories ratio for a given meal
	 * @param meal
	 * @return the fat to calories ratio
	 */
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
		return guide.getCarbs() - meal.getCarbs();
	}
	
	/**
	 * Calculates the carbs to calories ratio for a given meal
	 * @param meal
	 * @return the carbs to calories ratio
	 */
	public static double carbsRatio(Nutrition meal) {
		if (meal.getCalories()==0) {
			return Double.MAX_VALUE;
		} else {
			return meal.getCarbs()/meal.getCalories();
		}
	}
	
	/**
	 * Calculates the nutrition ratio similarity between two meals. 
	 * Here, the similarity defines as the distance for three ratios
	 * @param meal1
	 * @param meal2
	 * @return similarity
	 */
	public static double nutritionRatioSimiliarity(Nutrition meal1, Nutrition meal2) {
		double proteinRatioDist = proteinRatio(meal1) - proteinRatio(meal2);
		double fatRatioDist = fatRatio(meal1) - fatRatio(meal2);
		double carbsRatioDist = carbsRatio(meal1) - carbsRatio(meal2);
		double similiarilty = Math.sqrt(Math.pow(proteinRatioDist,2) 
				                      + Math.pow(fatRatioDist,2) 
				                      + Math.pow(carbsRatioDist,2));
		
		return similiarilty;
	}
	
	/**
	 * Checks if a meal similar to the guide for all the four nutrition, 
	 * given acceptable error (0-1, deviation ratio from guide
	 * @param meal
	 * @param guide
	 * @param error
	 * @return true if meal is similar to the guide, otherwise false
	 */
	public static boolean isSimiliar(Nutrition meal, Nutrition guide, double error) {
		//every nutrition in the guide would be greater than 0 as definition
		if (Math.abs(caloriesGap(meal,guide)/guide.getFat())>error) return false;
		if (Math.abs(fatGap(meal,guide))/guide.getFat()>error) return false;
		if (Math.abs(carbsGap(meal,guide))/guide.getCarbs()>error) return false;
		if (Math.abs(proteinGap(meal,guide))/guide.getProtein()>error) return false;
		return true;
	}
	
	/**
	 * Checks if the volume of any nutrition element is greater than the guideline
	 * @param meal
	 * @param guide
	 * @return true the volume of any nutrition element is greater than the guideline, otherwise false
	 */
	public static boolean isNutritionExcess(Nutrition meal, Nutrition guide) {
		if (caloriesGap(meal,guide)<0) return false;
		if (fatGap(meal,guide)<0) return false;
		if (carbsGap(meal,guide)<0) return false;
		if (proteinGap(meal,guide)<0) return false;
		return true;
	}
	
	/**
	 * Calculates the Gaps between meal and guide, and returns a fake food to represent the gaps
	 * @param meal
	 * @param guide
	 * @return the fake food to represent the gaps
	 */
	public static Food nutritionGap(Nutrition meal, Nutrition guide) {
		double caloriesGap = caloriesGap(meal,guide);
		double proteinGap = proteinGap(meal,guide);
		double fatGap = fatGap(meal,guide);
		double carbsGap = carbsGap(meal,guide);
		return new Food(caloriesGap,fatGap,proteinGap,carbsGap);
	}
	
	/**
	 * Given a FoodGroup and a Guideline, drop the foods until calories is lower than standard,
	 * or until the FoodGroup is similar to the guideline.
	 * @param group
	 * @param guide
	 * @param error
	 * @return return the FoodGroup after dropping foods 
	 */
	public static FoodGroup dropExtraFoodOnCalories(FoodGroup group, Nutrition guide, double error) {
		Food gap = nutritionGap(group, guide);
		Food tempFood = null;
		double tempPortion = 0;
		while (gap.getCalories()<0) {
			// the FoodGroup is similar to the guideline, return the FoodGroup
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			// drop food based on dropping which food makes the food group close to the guideline
			for (Food f: foods) {
				if (f.getCalories()==0) continue;
				// assume drop 1 serving if the portion is an integer, 
				// otherwise drop the floating part.
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getCalories() * portion + gap.getCalories());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		return group;
	}
	
	/**
	 * Given a FoodGroup and a Guideline, drop the foods until fat is lower than standard,
	 * or until the FoodGroup is similar to the guideline.
	 * @param group
	 * @param guide
	 * @param error
	 * @return return the FoodGroup after dropping foods 
	 */
	public static FoodGroup dropExtraFoodOnFat(FoodGroup group, Nutrition guide, double error) {
		Food gap = nutritionGap(group, guide);
		Food tempFood = null;
		double tempPortion = 0;
		while (gap.getFat()<0) {
			// the FoodGroup is similar to the guideline, return the FoodGroup
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			// drop food based on dropping which food makes the food group close to the guideline
			for (Food f: foods) {
				if (f.getFat()==0) continue;
				// assume drop 1 serving if the portion is an integer, 
				// otherwise drop the floating part.
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getFat() * portion + gap.getFat());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		return group;
	}
	
	/**
	 * Given a FoodGroup and a Guideline, drop the foods until protein is lower than standard,
	 * or until the FoodGroup is similar to the guideline.
	 * @param group
	 * @param guide
	 * @param error
	 * @return return the FoodGroup after dropping foods 
	 */
	public static FoodGroup dropExtraFoodOnProtein(FoodGroup group, Nutrition guide, double error) {
		Food gap = nutritionGap(group, guide);
		Food tempFood = null;
		double tempPortion = 0;
		while (gap.getProtein()<0) {
			// the FoodGroup is similar to the guideline, return the FoodGroup
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			// drop food based on dropping which food makes the food group close to the guideline
			for (Food f: foods) {
				if (f.getProtein()==0) continue;
				// assume drop 1 serving if the portion is an integer, 
				// otherwise drop the floating part.
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getProtein() * portion + gap.getProtein());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		return group;
	}
	
	/**
	 * Given a FoodGroup and a Guideline, drop the foods until carbs is lower than standard,
	 * or until the FoodGroup is similar to the guideline.
	 * @param group
	 * @param guide
	 * @param error
	 * @return return the FoodGroup after dropping foods 
	 */
	public static FoodGroup dropExtraFoodOnCarbs(FoodGroup group, Nutrition guide, double error) {
		Food gap = nutritionGap(group, guide);
		Food tempFood = null;
		double tempPortion = 0;
		while (gap.getCarbs()<0) {
			// the FoodGroup is similar to the guideline, return the FoodGroup
			if (isSimiliar(group,guide,error)) return group;
			ArrayList<Food> foods = new ArrayList<>(group.getFoodDetail().values());
			double min = Double.MAX_VALUE;
			double portion;
			double value;
			// drop food based on dropping which food makes the food group close to the guideline
			for (Food f: foods) {
				if (f.getCarbs()==0) continue;
				// assume drop 1 serving if the portion is an integer, 
				// otherwise drop the floating part.
				portion = group.getPortion(f) - Math.max(Math.ceil(group.getPortion(f))-1,0);
				value = Math.abs(f.getCarbs() * portion + gap.getCarbs());
				if (value<min) {
					min = value;
					tempFood = f;
					tempPortion = portion;
				}
		
			}
			group.addFood(tempFood, -tempPortion);
			gap = nutritionGap(group, guide);
		}
		return group;
	}
	
	/**
	 * Given a FoodGroup and a Guideline, drop the foods until every nutrition element is lower than standard,
	 * or until the FoodGroup is similar to the guideline. (A combination of dropExtraFoodOnCalories,
	 * dropExtraFoodOnFat, dropExtraFoodOnProtein, dropExtraFoodOnCarbs)
	 * @param group
	 * @param guide
	 * @param error
	 * @return return the FoodGroup after dropping foods 
	 */
	public static FoodGroup dropExtraFood(FoodGroup group, Nutrition guide, double error) {
		group = dropExtraFoodOnCalories(group,guide,error);
		group = dropExtraFoodOnFat(group,guide,error);
		group = dropExtraFoodOnProtein(group,guide,error);
		group = dropExtraFoodOnCarbs(group,guide,error);
		return group;
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
	
	/**
	 * Calculates the difference between two given FoodGroups, and returns the difference
	 * as a food group. The portion in the return can be negative.
	 * (didn't use now, keep it in case any use)
	 * @param group1
	 * @param group2
	 * @return FoodGroup difference
	 */
	public static FoodGroup FoodGroupDifference(FoodGroup group1, FoodGroup group2) {
		HashMap<String,Food> foodDetail2 = group2.getFoodDetail();
		HashMap<String,Double> foodPortion2 = group2.getFoodPortion();
		for (String foodName : foodDetail2.keySet()) {
			group1.addFood(foodDetail2.get(foodName), -foodPortion2.get(foodName));
		}
		return group1;
	}
}
