import java.util.*;

/***
 * The user interface for the user interaction
 *
 */
public class NutritionRecommender {
	
	User newUser;

	/**
	 * The interface for user input
	 */
	public NutritionRecommender() {
		System.out.println("-------Welcome to Nutrition App!--------");
		Scanner scanner = new Scanner(System.in);
		// name
		System.out.println("Tell us what is your name?");
		String userName = scanner.next();
		// age
		System.out.println("Please input your age as an integer!");
		while (!scanner.hasNextInt()) {
    		System.out.println("Please input a number!");
    		scanner.next();
    	}
		int age = scanner.nextInt();
		// gender
		System.out.println("Please input your gender! M or F:");
		String gender = scanner.next();
		while ((gender.equals("M") == false) && (gender.equals("F") == false)) {
    		System.out.println("Please input a valid gender! M or F please.");
    		gender = scanner.next();
    	}
		
		// make user
		newUser = new User(userName, age, gender);
		scanner.close();
	}
	
	/**
	 * Ask user the food they ate
	 * needs to be completed
	 * if a food user input is not in the key of the foodLibrary
	 * ask "Do you mean the food: XXX" using the foodFider class
	 */
	public void addMeal(HashMap<String, Double> meal) {
		FoodLibrary foodLib = new FoodLibrary();
		for (String key: meal.keySet()) {
			if (foodLib.getLibrary().containsKey(key)) {
				Food food = foodLib.getLibrary().get(key);
				newUser.addFood(food, meal.get(key));
			}
			else {
				// use foodFinder to ask for suggestions
				System.out.println("Cannot add the food " + key + ".");
			}
		}
	}

	/**
	 * print suggestions
	 */
	public void giveSuggestions() {
		ArrayList<String> suggestions = newUser.getGeneralSuggestions();
		System.out.println("Hi " + newUser.getName() + ", here is your general suggestions:");
		int i = 1;
		for (String suggestion : suggestions) {
			System.out.println(String.valueOf(i) + ". " + suggestion);
		}
	}
}
