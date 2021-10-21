package com.Ty.RMGYantra.scripts;

import org.codehaus.groovy.tools.javac.JavaAwareCompilationUnit;
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

public class ProjectwithOngoingstatus extends BaseClass {
	@Test
	public void projectwithongoingstatus() throws SQLException {
		String projectName="Manisha"+JavaUtility.getRandomnumb();
	Project pobj=new Project("Manisha", projectName,"On Going",10);
		
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(pobj)
				.when()
				.post(IEndPoints.addProjectwithOngoingStatus);
				
				resp.then()
				.log().all()
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
