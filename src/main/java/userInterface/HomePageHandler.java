package userInterface;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import spark.Request;
import spark.Response;
import spark.Route;

public class HomePageHandler implements Route{
	private String html_content = "";
	
	public HomePageHandler() {	
		try {
			html_content = new String (Files.readAllBytes(Paths.get("homepage.html") ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public Object handle(Request request, Response response) throws Exception {
		return html_content;
	}
}