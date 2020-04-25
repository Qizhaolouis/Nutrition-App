package NutritionRecommender;
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
	double sleepingHours = 8;
	FoodGroup foods = new FoodGroup(); 
	NutritionGuideline guide;
	
	/**
	 * Initializes a newly created User Object by the given name, age, and activity level.
	 * @param inputName user name
	 * @param inputAge user age (must be an integer)
	 * @param inputGender (user gender, must be M or F)
	 */
	public User(String inputName, int inputAge, String inputGender, String inputActivityLevel) {
		name = inputName;
		gender = inputGender;
		age = inputAge;
		activityLevel = inputActivityLevel;
		guide = new NutritionGuideline(this);
	}

	public NutritionGuideline getGuide() {
		return guide;
	}

	public void setGuide(NutritionGuideline guide) {
		this.guide = guide;
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
		suggestions.add(this.suggestionFat());
		suggestions.add(this.suggestionCarbs());
		suggestions.add(this.suggestionProtein());
		return suggestions;
	}
	
	/**
	 * check sleeping hours, return suggestion regarding sleep
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
	 * check calories, return suggestion regarding calories
	 * @return suggestion
	 */
	public String suggestionCalories() {
		String suggestion = "";
		// get calories needed
		double caloriesNeeded = guide.getCalories();
		// add food
		double caloriesConsumed = 0;
		caloriesConsumed += foods.getCalories();
		// get suggestion
		if (Math.abs(caloriesNeeded - caloriesConsumed) < 100) {
			suggestion = "The calories you consumed is the right amount.";
		}
		else if (caloriesConsumed > caloriesNeeded) {
			suggestion = "You will need to eat less food. (about " + String.format("%.0f",caloriesConsumed - caloriesNeeded) + " calories less)";
		}
		else {
			suggestion = "You will need to eat more food. (about " + String.format("%.0f",caloriesNeeded - caloriesConsumed) + " calories more)";
		}
		return suggestion;
	}
	
	/**
	 * check fat needed and gives suggestion
	 * @return suggestion about fat
	 */
	public String suggestionFat() {
		String suggestion = "";
		double maxFat = guide.getFatMax();
		double minFat = guide.getFatMin();
		double midFat = guide.getFat();
		double fatConsumed = 0;
		fatConsumed += foods.getFat();
		if (fatConsumed > maxFat) {
			suggestion = "You are eating too much fat. (about " + String.format("%.0f",fatConsumed - midFat) + "g less)";;
		}
		else if (fatConsumed < minFat) {
			suggestion = "You will need to eat more fat. (about "  + String.format("%.0f",midFat - fatConsumed) + "g more)";
		}
		else {
			suggestion = "You are eating the right amount of fat.";
		}
		return suggestion;
	}
	
	/**
	 * check protein needed and give suggestion
	 * @return suggestion about fat
	 */
	public String suggestionProtein() {
		String suggestion = "";
		double maxProtein = guide.getProteinMax();
		double minProtein = guide.getProteinMin();
		double midProtein = guide.getProtein();
		double proteinConsumed = 0;
		proteinConsumed += foods.getProtein();
		if (proteinConsumed > maxProtein) {
			suggestion = "You are eating too much protein. (about " + String.format("%.0f",proteinConsumed - midProtein) + "g less)";;
		}
		else if (proteinConsumed < minProtein) {
			suggestion = "You will need to eat more protein. (about "  + String.format("%.0f",midProtein - proteinConsumed) + "g more)";
		}
		else {
			suggestion = "You are eating the right amount of protein.";
		}
		return suggestion;
	}	
	
	/**
	 * check carbs needed and give suggestion
	 * @return suggestion about carbs
	 */
	public String suggestionCarbs() {
		String suggestion = "";
		double maxCarbs = guide.getCarbsMax();
		double minCarbs = guide.getCarbsMin();
		double midCarbs = guide.getCarbs();
		double carbsConsumed = 0;
		carbsConsumed += foods.getCarbs();
		if (carbsConsumed > maxCarbs) {
			suggestion = "You are eating too much Carbs. (about " + String.format("%.0f",carbsConsumed - midCarbs) + "g less)";;
		}
		else if (carbsConsumed < minCarbs) {
			suggestion = "You will need to eat more Carbs. (about "  + String.format("%.0f",midCarbs - carbsConsumed) + "g more)";
		}
		else {
			suggestion = "You are eating the right amount of Carbs.";
		}
		return suggestion;
	}	
	
	/**
	 * check activity level, if activity level is L , user need more exercises.
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
	 * provide the guide and food group, get back suggested food.
	 * @return a list of food names
	 */
	public FoodGroup getSuggestedFood() {
		FoodGroup foodResultList;
		FoodLibrary foodlib = new FoodLibrary();
		String caloriesSuggestion = this.suggestionCalories();
		if (caloriesSuggestion.contains("more")) {
			foodResultList = NutritionCalculator.getSuggestedFood(foods, guide, foodlib.getLibrary(), 100.0);
		}
		else {
			foodResultList = NutritionCalculator.dropExtraFood(foods, guide, 100.0);
		}		
		return foodResultList;
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
