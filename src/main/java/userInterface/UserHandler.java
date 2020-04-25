package userInterface;

import spark.Request;
import spark.Response;
import spark.Route;

public class UserHandler  implements Route{
	
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
			"  right: 10.5px;\n" + 
			"  color: white;\n" + 
			"  padding-left: 20px;\n" + 
			"  padding-right: 10px;\n" + 
			"}\n" + 
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"    <div class=\"container\">\n" + 
			"     <img class=\"img-fluid\" src=\"https://blog.obiaks.com/uploadsblog/171120095429.jpg\" alt=\"food\" style = \"width :150%;height: 60%\">\n" + 
			"      <div class=\"text-block bg-success text-white\">\n" + 
			"       <h1>Welcome to the Nutrition App</h1>\n" + 
			"       <h4>Your Health Matters</h4>\n" + "<a href='/'><h2>Go Back Home</h2></a>\n" + 
			"      </div>\n" + 
			"       </div>\n" + 
			"       <body><div class=\"container\"><div><ul></ul></div>\n" + 
			"        <h2 class=\"text-warning\">Tell us about yourself</h2>";
	
	private final String userForm = "<form action=\"/addProfile\" method=\"post\">"
			+ "<br>"
			+ "<h4 class=\"text-success\">Add User Profile:</h4>"
			+ "<br>"
			+ "<label class=\"text-success\" for=\"userName\">Tell us your name!</label>"
			+ "<br>"
			+ "<br>"
			+ "<input type=\"text\" name=\"userName\" value=\"UserName\">"
			+ "<br>"
			+ "<br>"
			+ "<label class=\"text-success\" for=\"userAge\">What is your age? (between 2 and 100)</label>"
			+ "<br>"
			+ "<br>"
			+ "<input id=\"rangeInput\" class=\"form-control-range\" type=\"range\" name=\"userAge\" , min=\"2\" max=\"100\"value=\"0\" oninput=\"amount.value=rangeInput.value\">"
			+ "<output id=\"amount\" name=\"amount\" for=\"rangeInput\">2</output>" 
			+ "<br><br>"
			+ "<label class=\"text-success\" for=\"userSleep\">How long to you sleep? (between 0 to 24)</label>"
			+ "<br><br>"
			+ "<input id=\"sleepInput\" class=\"form-control-range\" type=\"range\" name=\"userSleep\" , min=\"0\" max=\"24\"value=\"8\" oninput=\"sleepTime.value=sleepInput.value\">"
			+ "<output id=\"sleepTime\" name=\"sleepTime\" for=\"sleepInput\">8</output>" 
			+ "<br><br>"
			+ "<label class=\"text-success\" for=\"userGender\">What is your gender? (We only has M and F now)</label>"
			+ "<br><br>"
			+ "<select id=\"userGender\" name=\"userGender\">\n"
			+ " <option value=\"M\">Male</option>\n" 
			+ " <option value=\"F\">Female</option>\n" 
			+ "</select>"
			+ "<br><br>"
			+ "<label class=\\\"text-success\\\" for=\\\"userActivity\\\">How ofte do you work out?</label>"
			+ "<br><br>"
			+ "<select id=\"userActivity\" name=\"userActivity\">\n"
			+ " <option value=\"L\">low (less than 1 time a week)</option>\n" 
			+ " <option value=\"M\">medium (2 - 3 times a week)</option>\n" 
			+ " <option value=\"H\">high (more than 4 times a week)</option>\n" 
			+ "</select>"
			+ "<br><br>"
			+ "<button  class=\"btn btn-success\" style=\"margin-left: 2px\" type=\"submit\">I am done!</button>"
			+ "</form>";
	
	
	public Object handle(Request request, Response response) throws Exception {
		
		if ("/addProfile".equals(request.pathInfo())) {			
			response.cookie("name", request.queryParams("userName"), 3600);  
			response.cookie("sleep", request.queryParams("userSleep"), 3600);  
			response.cookie("age", request.queryParams("userAge"), 3600);  
			response.cookie("gender", request.queryParams("userGender"), 3600);  
			response.cookie("activityLevel", request.queryParams("userActivity"), 3600);
			response.redirect("/reward");
		}
		return htmlHead + userForm + "</div></body></html>";
	}

}
