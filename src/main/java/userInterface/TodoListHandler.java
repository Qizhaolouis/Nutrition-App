package userInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import NutritionRecommender.Food;
import NutritionRecommender.FoodFinder;
import NutritionRecommender.FoodLibrary;
import spark.Request;
import spark.Response;
import spark.Route;

public class TodoListHandler implements Route{
	private final FoodLibrary foodLib = new FoodLibrary();
	
	private final String htmlHead = "\n" + 
			"<html>\n" + 
			"<head>\n" + 
			"  <title>Nutrition App</title>\n" + 
			"  <meta charset=\"utf-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\n" + 
			"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/4.4.1/jquery.min.js\"></script>\n" + 
			"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>`\n" + 
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
			"  bottom: 1px;\n" + 
			"  right: 34px;\n" + 
			"  color: white;\n" + 
			"  padding-left: 20px;\n" + 
			"  padding-right: 10px;\n" + 
			"}\n" + 
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"    <div class=\"container\">\n" + 
			"     <img class=\"img-fluid\" src=\"https://res.cloudinary.com/sanitarium/image/fetch/q_auto/https://www.sanitarium.com.au/getmedia%2Fae51f174-984f-4a70-ad3d-3f6b517b6da1%2Ffruits-vegetables-healthy-fats.jpg%3Fwidth%3D1180%26height%3D524%26ext%3D.jpg\" alt=\"food\" style = \"width: 100%:;height:50%\">\n" + 
			"	  <div class=\"text-block bg-success text-white\">\n" + 
			"	    <h1>Welcome to the Nutrition App</h1>\n" + 
			"	    <h4>Your Health Matters</h4>\n" + 
			"<a href='/'><h2>Go Back Home</h2></a>\n" + 
			"	  </div>\n" + 
			"\n" + 
			"		</div>\n" + 
			"		</div>\n" + 
			"		<body><div class=\"container\"><div><ul></ul></div>\n" + 
			"			<div>";
	
	private final String newTaskForm = "<div><form action=\"/createtodo\" method=\"post\">"
			+ "<br>"
			+ "<h2 class=\"text-warning\">Tell us what you ate today!</h2>"
			+ "<br>" 
			+ "<dl>\n" + 
			   "<dt class = \"text-success\">Add Food name and number of servings:</dt>\n" + 
			   "<dd class = \"text-success\">- Breakfast</dd>\n" + 
			   "<dd class = \"text-success\">- Lunch</dd>\n" + 
			   "<dd class = \"text-success\">- Dinner</dd>" 
			+ "<input type=\"text\" name=\"searchfood\" value=\"\">"
			+ "<br>"
			+ "<br>"
			+ "<button class = \"btn btn-outline-success\" style=\"margin-left: 2px\" type=\"submit\">Search Food</button>"
			+ "</form></div>";


	
	private final String finishButton = "<button class=\"btn btn-success\" style=\"margin-left: 2px\" onclick=\"location.href='/taskdone'\">Finished Adding Food</button>";
	

	private HashMap<String, Double> meal;
	
	public TodoListHandler() {
		meal = new HashMap<String, Double>();
	}

	public Object handle(Request request, Response response) throws Exception {
		String error = "";
		String suggest = "";
		if ("/toeat".equals(request.pathInfo())) {
			meal.clear();
		}
		else if ("/taskdone".equals(request.pathInfo())) {
			if (meal.isEmpty()) {
				error = "<div class=\"alert alert-primary\" role=\"alert\">\n" + 
						"Sorry! Please add at least one food!!!\n" + 
						"</div>";
			} else {
				response.cookie("meal", meal.toString(), 3600);  
				meal.clear();
				response.redirect("/user");
			}
			
			
		} else if ("/createtodo".equals(request.pathInfo())) {
			
			String foodName = request.queryParams("searchfood");
			FoodFinder finder = new FoodFinder();
			ArrayList<String> foodList = finder.getTopNMatched(foodName, 10);
			
			if (foodList.size() >0) {
				suggest = "<br>";
				StringBuilder sb = new StringBuilder();
				sb.append("<div><form action=\"/searchFood\" method=\"post\"> We have some matches<br><br>");
				sb.append("<label for=\"foodname\">Select a food in our library: </label><br><br>");
				sb.append("<select id=\"foodname\", name=\"foodname\">");
				for ( String fd : foodList) {
					Food food = foodLib.getLibrary().get(fd);
					sb.append("<option value=\"" + fd + "\">"+ "Food: " + fd + " ------  Each serving is: " + food.getServingDes() + "</option>");
					sb.append("</ul></div>");
				}
				sb.append("</select>");
				sb.append("<br><br><input type=\"number\" name=\"portion\" min=\"0.1\" max=\"10000.0\" step=\"any\"><br><br>");
				sb.append("<button style=\"margin-left: 10px\" type=\"submit\">Add Food</button>");
				sb.append("</form></div>");
				suggest += sb.toString();	
			} else {
				error = "<div class=\"alert alert-primary\" role=\"alert\">\n" + 
						"Sorry! Cannot find any food that matches this food.\n" +
						"</div>";
			}
		} else if ("/searchFood".equals(request.pathInfo())) {
			String foodname = request.queryParams("foodname");
			Double portion = Double.valueOf(request.queryParams("portion"));
			meal.put(foodname, portion);
		}
		return htmlHead + error +
				taskList() + newTaskForm + "<br>"  + suggest + "<br>" + finishButton + "</div></body></html>";
	}
	
	public HashMap<String, Double> getMeal() {
		return meal;
	}
	
	public String taskList() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div><ul>");
		
		for (Entry<String, Double> task : meal.entrySet()) {
			sb.append("<li class=\"list-group-item active\">" + "Food: " + task.getKey() + " servings: " + String.valueOf(task.getValue()) + "</li>");
		}
		
		sb.append("</ul></div>");
		
		return sb.toString();
	}
	
}