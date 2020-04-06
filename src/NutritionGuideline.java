import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/***
 * This class is to read and calculate calorie and nutrition guideline for given user
 * @author Team-70
 */
public class NutritionGuideline {
	private HashMap<String,Integer> calorieGuideline = new HashMap<>();
	
	public NutritionGuideline() {
		//run the readCalorieGuideline() method to get calorieGuideline
		readCalorieGuideline();
	}
	
	
	//Carbohydrates provide 4 calories per gram, protein provides 4 calories per gram, 
	//and fat provides 9 calories per gram.
	
	/**
	 * The getter of calorieGuideline
	 * @return
	 */
	public HashMap<String,Integer> getCalorieGuideline() {
		return calorieGuideline;
	}
	
	/**
	 * This method is to read the calorie_guideline.csv into HashMap<String,Integer> calorieGuideline
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
				
				calorieGuideline.put(keyForLActivity, calorieForLActivity);
				calorieGuideline.put(keyForMActivity, calorieForMActivity);
				calorieGuideline.put(keyForHActivity, calorieForHActivity);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("File Reader failed");
			e1.printStackTrace();
		}
	}
	
	public int getCalorieNeeded(User user) {
		String ageGroup = getAgeGroup(user.getAge());
		String gender = user.getGender();
		String activityLevel = user.getActivityLevel();
		
		String key = getGroupKey(ageGroup,gender,activityLevel);
		return calorieGuideline.get(key);
	}

    private String getGroupKey(String ageGroup, String gender, String activityLevel) {
    	return ageGroup + "|" + gender + "|" + activityLevel;
    }
    
    	
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
	
	public void getProteinRDA() {
	}
	
	public void getFatRDA() {
	}
	
	public void getCarbohydrateRDA() {
	}

}
