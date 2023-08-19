package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	//1. using dp class-getting excel utilities of getAllData method
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	
	public void testPostuser(String userID,String userName, String fname, String lname,String useremail,String pwd,String ph ) {// 2. now we have to receive the data in multiple parameters
	
		//3 creating payload- store all the data that receiving
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response= UserEndPoints.creteUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);				
	}
	
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	
	public void testDeleteuserByname(String userName) {
		
		Response response= UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);	
		
	}

}
