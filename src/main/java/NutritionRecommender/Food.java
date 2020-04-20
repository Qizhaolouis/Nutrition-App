package NutritionRecommender;

/***
 * This class is for the food, including getters and setters for the properties of this food.
 * @author Team-70
 *
 */
public class Food implements Nutrition {
	String name = null;
	String type = null;
	double calories = 0;
	double fat = 0;
	double protein = 0;
	double carbs = 0;
	double sugars = 0;
	double fiber = 0;
	double servingWeight = 0;
	String ServingDes = null;  
	
	/**
	 * This method is used for parsing the food data string into fields we need
	 * @param foodLine A line that contains all the food information
	 */
	public Food(String foodLine) {
		String[] elements = foodLine.split(",");
		servingWeight = parseDouble(elements[elements.length - 5]);
		ServingDes = fillString(elements[elements.length - 4]);
		name = elements[1];
		type = elements[2];
		calories = parseDouble(elements[3]) * servingWeight / 100;
		fat = parseDouble(elements[4]) * servingWeight / 100;
		protein = parseDouble(elements[5]) * servingWeight / 100;
		carbs = parseDouble(elements[6]) * servingWeight / 100;
		sugars = parseDouble(elements[7]) * servingWeight / 100;
		fiber = parseDouble(elements[8]) * servingWeight / 100;
	}
	
	/**
	 * This method is initialize a Food with calories, fat, protein, and carbs,
	 * and keep other parameters as default
	 * @param foodLine A line that contains all the food information
	 */
	public Food(double calories, double fat, double protein, double carbs) {
		this.calories = calories;
		this.fat = fat;
		this.protein = protein;
		this.carbs = carbs;
	}
	
	public double getCalories() {
		return calories;
	}

	public double getFat() {
		return fat;
	}
	
	/**
	 * This method is to transfer the input string to double format, fill Null as 0.0
	 * @param value
	 * @return double
	 */
	public double parseDouble(String value) {
		double output;
		if (value.equals("NULL")) {
			output = 1.0;
		}
		else {
			output = Double.parseDouble(value);
		}
		return output;
	}
	
	/**
	 * This method is for filling up the null "Serving Description" with unit "g"
	 * @param value
	 * @return
	 */
	public String fillString(String value) {
		String output;
		if (value.equals("")) {
			output = "g";
		}
		else {
			output = value;
		}
		return output;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public double getProtein() {
		return protein;
	}
	
	public double getCarbs() {
		return carbs;
	}
	
	public double getSugars() {
		return sugars;
	}
	
	public double getFiber() {
		return fiber;
	}

	public double getServingWeight() {
		return servingWeight;
	}

	public String getServingDes() {
		return ServingDes;
	}
}