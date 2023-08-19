package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import api.payload.User;


//create to perform CRUD requests to the user Api
public class UserEndPoints {
	
	public static Response creteUser(User payload)// payload will create on User pojo class
	{
		//the implement method
		Response res= given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		
		.when()
		 .post(Routes.post_url); // we allow to enter thorough class because its defined as static
		
		// .then(): we dont have because validation will be on the return
		// responde
	
		return res;
	}

	
	public static Response readUser(String userName)
	{
		//the implement method
		Response res= given()
         .pathParam("username",userName)
		
		.when()
		 .get(Routes.get_url); // we allow to enter thorough class because its defined as static
		
		return res;
	}
	
	
	public static Response updateUser(String userName, User payload)
	{
		//the implement method
		Response res= given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		 .pathParam("username",userName)
		
		.when()
		 .put(Routes.update_url); 
		return res;
	}
	
	
	public static Response deleteUser(String userName)
	{
		//the implement method
		Response res= given()
         .pathParam("username",userName)
		
		.when()
		 .delete(Routes.delete_url);
		
		return res;
	}
}
