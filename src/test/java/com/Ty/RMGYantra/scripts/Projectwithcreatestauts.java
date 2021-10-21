package com.Ty.RMGYantra.scripts;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.Ty.remgYantra.Pojolib.Project;
import com.Ty.rmgyantra.genericlib.IEndPoints;
import com.Ty.rmgyantra.genericlib.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static  io.restassured.RestAssured.*;

import java.sql.SQLException;
public class Projectwithcreatestauts extends BaseClass {
	@Test
	public void createdstatus() throws SQLException {
		String projectName="APItest"+JavaUtility.getRandomnumb();
		Project pobj=new Project("John",projectName,"Created",5);
		
	
		 Response resp = given()
		.contentType(ContentType.JSON)
		.body(pobj)
		.when()
		
		.post(IEndPoints.addProjectwithcreatedStatus);
		 
		 resp.then().log().all()
		 .and()
		 .contentType(ContentType.JSON);
		 
		 //capture the projectname and status
		String apiprojectname = resp.jsonPath().get("projectName");
		 String apiprojectstatus=resp.jsonPath().get("status");
		 
	boolean projectNameresult = dblib.executeQueryAndGetData("select * from project",4,apiprojectname);
	Assert.assertTrue(projectNameresult);	
	boolean projectstatusresult = dblib.executeQueryAndGetData("select * from project",5,apiprojectstatus);
	Assert.assertTrue(projectstatusresult);	
	
		 
		
	}

}

