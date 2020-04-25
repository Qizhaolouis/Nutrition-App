# Nurtrition Recommender Team 70
The java app for CIT591 of Team 70

# Configuration
## Method 1
1. download the project and unzip it.
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/Screenshot%20at%20Apr%2020%2020-50-52.png'>
2. open command line in computer, and do `cd \path\to\folder\final-project-nutrition-app`
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/Screenshot%20at%20Apr%2020%2020-51-39.png'>
3. on the command line, run `java -jar \path\to\folder\final-project-nutrition-app\NutritionAppTeam70.jar`
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/Screenshot%20at%20Apr%2020%2020-52-12.png'>
- Now, go to http://localhost:4567/


## Method 2
1. First you will have to clone the project to a folder, You should see a "pom.xml" in the folder, change the `<artifactId>my-app</artifactId>` to a different name that you haven't used before for a maven project, like `<artifactId>qi-app</artifactId>`.
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/21.Change_pom_xml.png'>
2. Open File -> Import, and under maven folder click `Existing Maven Projects`. 
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/22.import.png'>

3. Browse the root path to the folder you just cloned.
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/221.import.png'>

4. If you see any error next to your "src" folder or others, right click on the folder with error and `Build Path -> Remove from Build Path`. 
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/23.Exclude.png'>
Then right click on the `src/main/java` and `Build Path -> Use as Source Folder`
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/24.addsource.png'>

5. right click on the **pom.xml** file and go to **Maven -> Update Project** and update it.
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/6.updatemaven.png'>

6. If you do not have the Junit library in classpath, right click on the project and go to **properties**, in the **Java build path -> Libraries** click **Add Library** and add Junit.
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/7.enablejunit.png'>
<img src='hhttps://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/8.junit.png'>

7. Go to **UserInterface -> Main.java** and run it. After it is running, go to http://localhost:4567/
<img src='https://github.com/UPenn-CIT599/final-project-nutrition-app/blob/master/images/9.runMain.png'>

# How to use the App

# Technology Used
- **Front end**
  - Java spark Framework
  	- We used different handlers for this purpose.
- **Deployment**
  - The web application is deployed to a server using heroku
  - It is mapped to a domain using cname.
- **Website**
  - <a href="http://www.qizhaolouis.com">Nutrition App</a>
  - The web application is deployed to a server using Heroku
  - It is mapped to a domain using cname.
- **Website**
  - <a href="http://www.qizhaolouis.com">Nutrition App</a>

# Team Member
- Deyi Zhang: `deyi@seas.upenn.edu`
- Xixi Zhou: `xixizhou@seas.upenn.edu`
- Qi Zhao: `qiz216@seas.upenn.edu`
