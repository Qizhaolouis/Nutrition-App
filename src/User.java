import java.util.ArrayList;
import java.lang.Math;
/***
 * This class is for the object of User, where includes setters and getters for user's properties.
 * @author Team-70
 *
 */
public class User {
	int age;
	String gender; // "M" for male, "F" for female
	String activityLevel = "M"; //"L" for Sedentary, "M" for Moderately Active, "H" for Active
	String name;
	double height = -1; //in cm
	double weight = -1; //in kg
	double sleepingHours = -1;
	FoodGroup foods = new FoodGroup(); 
	NutritionGuideline guide = new NutritionGuideline();
	
	/**
	 * User needs to input the username to initialize the data
	 * @param inputName user name
	 * @param inputAge user age (must be an integer)
	 * @param inputGender (user gender, must be M or F)
	 */
	public User(String inputName, int inputAge, String inputGender) {
		name = inputName;
		gender = inputGender;
		age = inputAge;
	}
	/**
	 * @return the calories that the user needs each day
	 */
	public int getUserDailyCaloriesNeeded() {
		return guide.getCalorieNeeded(this);
	}
	
	/**
	 * Add food to breakfast
	 */
	public void addFood(Food food, double serving) {
		foods.addFood(food, serving);
	}
	/**
	 * check the followings:
	 * does the user sleep enough?
	 * does the user eat too little or too much?
	 * does the user need more exercise?
	 * does the user has a healthy BMI index?
	 * does the user has balanced diet?
	 * @return a list of general suggestions
	 */
	public ArrayList<String> getGeneralSuggestions() {
		ArrayList<String> suggestions = new ArrayList<String>();
		suggestions.add(this.suggestionActivity());
		suggestions.add(this.suggestionSleepHours());
		suggestions.add(this.suggestionCalories());
		return suggestions;
	}
	
	/**
	 * check sleeping hours
	 * @return suggestion
	 */
	public String suggestionSleepHours() {
		String suggestion = "";
		if (sleepingHours > 0) {
			if (sleepingHours < 7) {
				suggestion = "You need to sleep more. At least 7 hours.";
			}
			else if (sleepingHours < 9) {
				suggestion = "Your sleeping time is perfect!";
			}
			else {
				suggestion = "You sleep too much.";
			}
		}
		else {
			suggestion = "No sleeping data provided.";
		}
		return suggestion;
	}
	
	/**
	 * check calories
	 * @return suggestion
	 */
	public String suggestionCalories() {
		String suggestion = "";
		//get caleries needed
		double caloriesNeeded = this.getUserDailyCaloriesNeeded();
		// add food
		double caloriesConsumed = 0;
		caloriesConsumed += foods.getMealCalories();
		// get suggestion
		if (Math.abs(caloriesNeeded - caloriesConsumed) < 100) {
			suggestion = "The calories you consumed is the right amount.";
		}
		else if (caloriesConsumed > caloriesNeeded) {
			suggestion = "You will need to eat less food. (about " + String.valueOf(caloriesConsumed - caloriesNeeded) + " calories less)";
		}
		else {
			suggestion = "You will need to eat more food. (about " + String.valueOf(caloriesNeeded - caloriesConsumed) + " calories more)";
		}
		return suggestion;
	}
	
	/**
	 * If activity level is L , user need more exercises.
	 * @return suggestion for activity
	 */
	public String suggestionActivity() {
		String suggestion = "";
		if (activityLevel == "activityLevel") {
			suggestion = "Activilty level not provided.";
		}
		else if (activityLevel == "L") {
			suggestion = "You need more exercises.";
		}
		else {
			suggestion = "Your activity level is fine.";
		}
		return suggestion;
	}
	
	/**
	 * first get the calories/protein/fat/.. needed
	 * create a raw line
	 * make a food
	 * @return
	 */
	public Food getSuggestedFood() {
		Food userFood = new Food("");
		return userFood;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getSleepingHours() {
		return sleepingHours;
	}

	public void setSleepingHours(double sleepingHours) {
		this.sleepingHours = sleepingHours;
	}
	public FoodGroup getFoods() {
		return foods;
	}
	public void setFoods(FoodGroup foods) {
		this.foods = foods;
	}
	
}
