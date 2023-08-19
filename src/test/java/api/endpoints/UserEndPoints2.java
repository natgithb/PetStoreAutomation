package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;


//create to perform CRUD requests to the user Api
public class UserEndPoints2 {
	
	//1. creating method that return all the routes
	static ResourceBundle getURL(){ //the return type=ResourceBundle, static is to allow access
		ResourceBundle routes= ResourceBundle.getBundle("routes");//load property file using ResourceBundle class
	    return routes;//routes is property file name that get from resources
	}
	
	public static Response creteUser(User payload)
	{
	
		String post_url= getURL().getString("post_url");//2. get the actual url from the property
		
		Response res= given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		
		.when()
		 .post(post_url); //3. url is pass to here
		return res;
	}

	
	public static Response readUser(String userName)
	{
		String get_url= getURL().getString("get_url");
		
		Response res= given()
         .pathParam("username",userName)
		
		.when()
		 .get(get_url); // we allow to enter thorough class because its defined as static
		
		return res;
	}
	
	
	public static Response updateUser(String userName, User payload)
	{
		
		String update_url= getURL().getString("update_url");
		
		//the implement method
		Response res= given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		 .pathParam("username",userName)
		
		.when()
		 .put(update_url); 
		return res;
	}
	
	
	public static Response deleteUser(String userName)
	{
		
		String delete_url= getURL().getString("delete_url");
		
		//the implement method
		Response res= given()
         .pathParam("username",userName)
		
		.when()
		 .delete(delete_url);
		
		return res;
	}
}
