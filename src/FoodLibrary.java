import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/***
 * This class is to read info from the data file - MyFoodData.csv, 
 * where include the nutrition info for a large collection of foods.
 * @author Team-70
 *
 */
public class FoodLibrary {
	private HashMap<String, Food> library;
	
	public FoodLibrary() {
		// Read Food data into a ArrayList
		ArrayList<String> data = loadData("MyFoodData.csv");
		// save the data to HashMap library
		library = foodList(data);
	}
	
	/**
	 * The getter for library
	 * @return library
	 */
	public HashMap<String, Food> getLibrary() {
		return library;
	}

	
	/**
	 * Loads Data from the input file
	 * @param filename, the path of the input file
	 * @return 
	 */
	private ArrayList<String> loadData(String filename){
		ArrayList<String> foodData = new ArrayList<String>();
		File f = new File(filename);
		try {
			Scanner in = new Scanner(f);
			in.nextLine();
			while (in.hasNextLine()) {
				String line = in.nextLine();
				foodData.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodData;
	}
	
	// Create a food HashMap, using food name as the key
	/**
	 * Transfers the data to a food HashMap by using the names as keys
	 * @param foodData, the data read from the load function
	 * @return food HashMap
	 */
	private HashMap<String, Food> foodList(ArrayList<String> foodData){
		HashMap<String, Food> foodList = new HashMap<String, Food>();
		for (String row : foodData) {
			Food food = new Food(row);
			String name = food.getName();
			foodList.put(name,food);
		}
		return foodList;	
	}
	
	/*
	public static void main(String[] args) {
		FoodLibrary foods = new FoodLibrary();
		System.out.println(foods.getLibrary().size());
		Food a = foods.getLibrary().get("Bagels Wheat");
		System.out.println(a.getCarbs());
		//.get("Bagels Wheat"));
		//ArrayList<String> data = load.loadData("MyFoodData.csv");
		//System.out.println(load.foodList(data).get("Bagels Wheat"));
	}
	*/
}
