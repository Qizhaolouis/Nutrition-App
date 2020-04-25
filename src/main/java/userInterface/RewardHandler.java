package userInterface;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;

import NutritionRecommender.NutritionRecommender;

public class RewardHandler implements Route{
	
	private final String htmlHead = "\n" + 
			"<html>\n" + 
			"<head>\n" + 
			"  <title>Nutrition App</title>\n" + 
			"  <meta charset=\"utf-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\n" + 
			"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" + 
			"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\n" + 
			"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>\n" + 
			"  <script src=\"https://kit.fontawesome.com/bf2355cf37.js\" crossorigin=\"anonymous\"></script>\n" + 
			"  <style>\n" + 
			".container {\n" + 
			"  position: relative;\n" + 
			"  font-family: Arial;\n" + 
			"}\n" + 
			"\n" + 
			".text-block {\n" + 
			"  position: absolute;\n" + 
			"  bottom: 1.5px;\n" + 
			"  right: 13.5px;\n" + 
			"  color: white;\n" + 
			"  padding-left: 20px;\n" + 
			"  padding-right: 10px;\n" + 
			"}\n" + 
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"  <div class=\"container\">\n" + 
			"   <img cclass=\"img-fluid\" src=\"https://images.unsplash.com/photo-1521986329282-0436c1f1e212?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80\" alt=\"food\" style = \"width :100%;height: 70%\">\n" + 
			"    <div class=\"text-block bg-success text-white\">\n" + 
			"     <h1>Welcome to the Nutrition App</h1>\n" + 
			"     <h4>Your Health Matters</h4>\n" + "<a href='/'><h2>Go Back Home</h2></a>\n" + 
			"    </div>\n" + 
			"     </div>\n" + 
			"     <body><div class=\"container\"><div><ul></ul></div>\n" + 
			"      <div class=\"\\container\\\">\n" + 
			"        <br>\n" + 
			"        <h2 class=\"text-warning\">Our Suggestion</h2>\n" + 
			"        <br>\n";

	public Object handle(Request request, Response response) throws Exception {
		HashMap<String, Double> meal = new HashMap<String, Double>();
		String mealString = request.cookie("meal");
		mealString = mealString.substring(1, mealString.length()-1); 
		String[] keyValuePairs = mealString.split(",");              //split the string to creat key-value pairs

		for (String pair : keyValuePairs)                        //iterate over the pairs
		{
		    String[] entry = pair.split("=");                   //split the pairs to get key and value 
		    meal.put(entry[0].trim(), Double.parseDouble(entry[1].trim()));          //add them to the hashmap and trim whitespaces
		}
		// get data
		String userName = request.cookie("name");
		int age = Integer.parseInt(request.cookie("age"));
		int sleep = Integer.parseInt(request.cookie("sleep"));
		String gender = request.cookie("gender");
		String activityLevel = request.cookie("activityLevel");
		
		// clear form
		response.removeCookie("name");      
		response.removeCookie("age");      
		response.removeCookie("gender");
		response.removeCookie("sleep");
		response.removeCookie("activityLevel");      
		response.removeCookie("meal");      
		
		NutritionRecommender nutritionApp = new NutritionRecommender(userName, age, gender, activityLevel, sleep);
		nutritionApp.addMeal(meal);
		ArrayList<String> suggestions = nutritionApp.getSuggestions();
		//
		String htmlBody = "<ul class=\"list-group\">\n";
		meal = new HashMap<String, Double>();
		for (String suggestion: suggestions) {
			htmlBody +=  "<li class=\"list-group-item\">" + suggestion + "</li>\n";
		}
		htmlBody += "</ul>";
		return htmlHead + "<body><h3 class=\"text-success\">Hi " + userName + "!  Here is your health eating suggestion!<h3><br>\n" + htmlBody + "</div></body></html>";
	}
	
}