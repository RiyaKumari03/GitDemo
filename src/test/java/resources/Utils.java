package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification request;
	public static RequestSpecification requestSpecification() throws IOException{
		
		if(request==null){
		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
		 request = new RequestSpecBuilder().
				setBaseUri(getGlobalProperties("baseUrl")).
				addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).
				build();
		}
		return request;
	}
	
	public static String getGlobalProperties(String key) throws IOException{
		
		Properties prop = new Properties();
		FileInputStream stream = new FileInputStream("C:\\NeonWorkspace\\APIFramework\\src\\test\\java\\resources\\Global.properties");
		
		prop.load(stream);
		String uri = prop.getProperty(key);
		return uri;
		
	}
	
	public String getJsonPath(Response resp ,String key){
		
		String response = resp.asString();
		JsonPath json = new JsonPath(response);
		
		return json.get(key).toString();
	}
	
}
