package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

// creating CRUD tests
public class UserTest {
	
	Faker faker; //data will generate using faker
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	//setupData- this particular method will execute before starting all the test method
	public void setup() {
		
		faker=new Faker();
		userPayload= new User();
		
		//payload pass the data that generate by faker, like setId.
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone()); 
		
		//log
		logger =LogManager.getLogger(this.getClass());		
	}
	

	
@Test(priority=1)	
	public void tsetPostUser(){
	
	logger.info("*************** Creating user ******************");
	Response response= UserEndPoints.creteUser(userPayload);
	response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("*************** User is created ******************");
		
	}


@Test(priority=2)	
public void tsetGetUser(){
// here we need to extract the user name not to set 
	// as in post req so --> this.userPayload.getUsername
	
	logger.info("*************** Reading user info ******************");
	Response response= UserEndPoints.readUser(this.userPayload.getUsername());
	response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200);	
	logger.info("*************** user info displayed ******************");
}


@Test(priority=3)	
public void tsetUpdateUser(){
	
	logger.info("*************** updating user ******************"); 
	userPayload.setFirstName(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().emailAddress());
    // Updater the data
	Response response= UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
	response.then().log().body();// other way of assertion	
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("***************  user updated ******************");
	
	//AfterUpdate- need to retrieve the data to check it
	Response responseAfterUpdate= UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);	
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	
}


@Test(priority=4)	
public void tsetDeleteUser(){
// here we need to extract the user name not to set 
	// as in post req so --> this.userPayload.getUsername
	
	logger.info("*************** deleting user ******************");
	
	Response response= UserEndPoints.deleteUser(this.userPayload.getUsername());
	response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("*************** User deleted ******************");
	
}
}















