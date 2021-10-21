

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.Ty.rmgyantra.genericlib.DataBaseUtilies;

import io.restassured.RestAssured;



public class BaseClass {
	public DataBaseUtilies dblib=new DataBaseUtilies();
	
	@BeforeSuite
	public void configBS() throws SQLException {
		RestAssured.baseURI="http://localhost:8084/";
		dblib.connectToDB();
		
		}
	
	@AfterSuite
	public void configAS() throws SQLException {
		dblib.closeDb();
	}

}
