package NutritionRecommender;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/***
 * This class is to read and calculate calorie and nutrition guideline for given user
 * (source: https://health.gov/our-work/food-nutrition/2015-2020-dietary-guidelines)
 * @author Team-70
 */
public class NutritionGuideline implements Nutrition {
	private HashMap<String,Integer> caloriesGuideline = new HashMap<>();
	private User user;
	private double calories; //daily calories needed
	private double proteinMin; //the floor of daily protein needed
	private double proteinMax; //the cap of daily protein needed
	private double protein; //the middle level of daily protein needed
	private double fatMin; //the floor of daily fat needed
	private double fatMax; //the cap of daily fat needed
	private double fat; //the middle level of daily fat needed
	private double carbsMin; //the floor of daily carbs needed
	private double carbsMax; //the cap of daily carbs needed
	private double carbs; //the middle level of daily carbs needed
	
	
	/**
	 * Initializes the NutritionGuideline Object for a given user.
	 * @param user
	 */
	public NutritionGuideline(User user) {
		this.user = user;
		//run the readCalorieGuideline() method to get calorieGuideline
		readCalorieGuideline();
		calculateCalorieNeeded();
		calculateProteinAMDR();
		calculateFatAMDR();
		calculateCarbsAMDR();
	}
	
	/**
	 * Reads the calorie_guideline.csv into this.calorieGuideline
	 */
	private void readCalorieGuideline() {
		//hard code input filename
		String filename = "calorie_guideline.csv";
		File f = new File(filename);
		Scanner fileReader;
		try {
			fileReader = new Scanner(f);
			fileReader.nextLine();
			
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				String[] lineElements = line.split(",");

				String ageGroup = lineElements[0];
				int calorieForLActivity = Integer.parseInt(lineElements[1]);
				int calorieForMActivity = Integer.parseInt(lineElements[2]);
				int calorieForHActivity = Integer.parseInt(lineElements[3]);
				String gender = lineElements[4];
				
				String keyForLActivity = getGroupKey(ageGroup,gender,"L");
				String keyForMActivity = getGroupKey(ageGroup,gender,"M");
				String keyForHActivity = getGroupKey(ageGroup,gender,"H");
				
				caloriesGuideline.put(keyForLActivity, calorieForLActivity);
				caloriesGuideline.put(keyForMActivity, calorieForMActivity);
				caloriesGuideline.put(keyForHActivity, calorieForHActivity);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("File Reader failed");
			e1.printStackTrace();
		}
	}
	
	/**
	 * Calculates the calories needed based on a given user
	 * @return the calories needed for the given user based on the US Nutrition Guideline 
	 */
	private void calculateCalorieNeeded() {
		String ageGroup = getAgeGroup(user.getAge());
		String gender = user.getGender();
		String activityLevel = user.getActivityLevel();
		String key = getGroupKey(ageGroup, gender, activityLevel);
		calories = caloriesGuideline.get(key);
	}

	/**
	 * Returns a key for given user's info
	 * @param ageGroup
	 * @param gender
	 * @param activityLevel
	 * @return key
	 */
    private String getGroupKey(String ageGroup,String gender,String activityLevel) {
    	return ageGroup + "|" + gender + "|" + activityLevel;
    }
    
    /**
     * return the group of age for a given age
     * @param age
     * @return String the group age
     */
	private String getAgeGroup(int age) {
		String ageGroup;
		if (age<=18) {
			ageGroup = String.valueOf(age);
		} else if (age<=20) {
			ageGroup = "19-20";
		} else if (age>=76) {
			ageGroup = "76+";
		} else {
			ageGroup = String.valueOf((age/5)*5+1) + "-" + String.valueOf((age/5)*5+5);
		}
		return ageGroup;
	}
	
	/**
	 * Calculates the protein needed (floor, cap, middle) based on a given user
	 */
	private void calculateProteinAMDR() {
		double mutiplier = 4.0;//protein provides 4 calories per gram
		double ratioMin;
		double ratioMax;
		int age = user.getAge();
		if (age <= 3) {
			ratioMin = 5;
			ratioMax = 20;
		} else if (age <= 50) {
			ratioMin = 10;
			ratioMax = 30;			
		} else {
			ratioMin = 10;
			ratioMax = 35;
		}
		
		double ratioMid = (ratioMin + ratioMax)/2;
		proteinMin = calories * ratioMin / (mutiplier * 100);
		proteinMax = calories * ratioMax / (mutiplier * 100);
		protein = calories* ratioMid / (mutiplier * 100);
	}
	
	/**
	 * Calculates the fat needed (floor, cap, middle) based on a given user
	 */
	private void calculateFatAMDR() {
		double mutiplier = 9.0; //fat provides 9 calories per gram
		double ratioMin;
		double ratioMax;
		int age = user.getAge();
		if (age <= 3) {
			ratioMin = 30;
			ratioMax = 40;
		} else if (age <= 50) {
			ratioMin = 25;
			ratioMax = 35;			
		} else {
			ratioMin = 20;
			ratioMax = 35;
		}
		
		double ratioMid = (ratioMin + ratioMax)/2;
		fatMin = calories * ratioMin / (mutiplier * 100);
		fatMax = calories * ratioMax / (mutiplier * 100);
		fat = calories * ratioMid / (mutiplier * 100);
	}
	
	/**
	 * Calculates the Carbohydrates needed (floor, cap, middle) based on a given user
	 */
	private void calculateCarbsAMDR() {
		double mutiplier = 4.0; // Carbohydrates provide 4 calories per gram
		double ratioMin = 45;
		double ratioMax = 65;
		double ratioMid = (ratioMin + ratioMax)/2;
		carbsMin = calories * ratioMin / (mutiplier * 100);
		carbsMax = calories * ratioMax / (mutiplier * 100);
		carbs = calories * ratioMid / (mutiplier * 100);

	}
	
	public HashMap<String,Integer> getCaloriesGuideline() {
		return caloriesGuideline;
	}

	public double getCalories() {
		return calories;
	}

	public double getProteinMin() {
		return proteinMin;
	}

	public double getProteinMax() {
		return proteinMax;
	}

	public double getProtein() {
		return protein;
	}

	public double getFatMin() {
		return fatMin;
	}

	public double getFatMax() {
		return fatMax;
	}

	public double getFat() {
		return fat;
	}

	public double getCarbsMin() {
		return carbsMin;
	}

	public double getCarbsMax() {
		return carbsMax;
	}

	public double getCarbs() {
		return carbs;
	}

}
