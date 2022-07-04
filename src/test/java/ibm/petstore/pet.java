package ibm.petstore;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class pet {

	@DataProvider(name="testdata")
	//@Test(enabled=true)
	public Object[][] data()
	{
		Object[][] data=new Object[2][7];
		//data[0][0]="1";
		data[0][0]="mohisin07";
		data[0][1]="sayyad";
		data[0][2]="Mohisin";
		data[0][3]="mm@m.com";
		data[0][4]="123456789";
		data[0][5]="8977996443";
		data[0][6]="2";
		//data[1][0]="1";
		data[1][0]="fareed08";
		data[1][1]="Shaik";
		data[1][2]="Fareed";
		data[1][3]="ff@m.com";
		data[1][4]="1234321";
		data[1][5]="8976357944";
		data[1][6]="3";
		
		return data;
	}
	
	
	@Test(enabled = true,priority=1,dataProvider="testdata")
	public void user(String uname, String fname, String lname, String email, String pass, String ph,String us)
	{
		
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		//String bodyvariable="{\"id\":0,\"username\":\"string\",\"firstName\":\"string\",\"lastName\":\"string\",\"email\":\"string\",\"password\":\"string\",\"phone\":\"string\",\"userStatus\":0}";
	
		JSONObject obj= new JSONObject();
		
		//obj.put( "id",id );
		obj.put( "username",uname );
		obj.put("firstname",fname);
		obj.put("lastname",lname);
		obj.put("email",email);
		obj.put("password",pass);
		obj.put("phone",ph);
		obj.put("userstatus",us);
		
		given()
	        .headers("content-type","application/json")
	        .body(obj.toJSONString()).
	    when()
	        .post("user").
	then()
	        .statusCode(200).log().all();
	}
	
	@Test(enabled=true, priority=2)
	public void sectest()

	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().get("/user/mohisin07").
		then()
		.statusCode(200)
		.log().all();
		
		}
	
	/*@Test(enabled=true)
	public void third()*/
	@Test(enabled=true,priority=3,dataProvider="testdata")
    public void LoginUser(String un,String fn,String ln,String email,String pass,String ph,String us)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given()
		.queryParam("username", un)
		.queryParam("password",pass).log().all();
		when()
		.get("/user/login").
		then()
		.statusCode(200)
		.log().all();
	}
		
	
	@Test(enabled=true, priority=4)
	public void modify()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		JSONObject obj= new JSONObject();
		//obj.put("id","1");
		obj.put("firstname","Sayyad");
		obj.put("lastname","Moshin");
		
		given().contentType(ContentType.JSON)
        .body(obj.toJSONString())
     .when()
	    .put("user/mohisin07").
	then()
	    .statusCode(200)
	    .log().all();
		
		/*given().contentType(ContentType.JSON)
               .body(obj.toJSONString())
            .when()
               
               .then()
               .statusCode(200).log().all();*/
		
				
	}
	
	@Test(enabled=true,priority=5)
	public void fifth()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().delete("/user/fareed08").
		then()
		.statusCode(200).log().all();
	}

}
