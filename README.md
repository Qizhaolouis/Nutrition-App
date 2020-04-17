# Nurtrition-Recommender
The java app for CIT591 of Team 70

# Team-70-Nutrition-App
Course CIT591: The nutrition java app for Team 70.

## Team Member
- Deyi Zhang: `deyi@seas.upenn.edu`
- Xixi Zhou: `xixizhou@seas.upenn.edu`
- Qi Zhao: `qiz216@seas.upenn.edu`

## TA
- Krishna Morawala: `morawala@seas.upenn.edu`

## Design
- Food
	- Food with the nutrition facts
	- methods:
		- getters and setters
- FoodGroup
	- A group of food together
	- methods:
		- add food and the portion
		- calculate simple nutrition facts
- FoodLibrary
	- The universe of our food informations
- FoodFinder
	- A utility class to find food by fuzzy matching
	- Compare names
- NutritionCalculator
	- calculate the difference between foods inputed and nutrition needed, and recommend foods adds/deletes for the user.
- NutritionGuiderline
	- Store information about the guideline: the caleries needed for different age/gender
- NutritionRecommender
	- The program logic workflow and user interface.
- NutritionRunner
	- The program to run the app.

## User Interface
- **Front end**
  - Java spark Framework
  	- We used different handlers for this purpose.
- **Deployment**
  - The web application is deployed to a server using heroku
  - It is mapped to a domain using cname.
- **Website**
  - <a href="http://www.qizhaolouis.com">Nutrition App</a>
