package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CheckResponseHeaderValidate
{
    /*Test 1 : get Header value */
    @Test
    public void getHeaderValue()
    {
        Reporter.log("Base URl mean where u want to request");
        RestAssured.baseURI = "https://reqres.in/";

        Reporter.log("HTTP request which is given to restAssured");
        RequestSpecification httpRequest = RestAssured.given();

        Reporter.log("Call a server and get a response");
        Response response = httpRequest.request(Method.GET,"api/users?page=2");

        Reporter.log("Get a Content Type");
        String contentType = response.contentType();
        System.out.println("Content Type is =>  " + contentType);
        System.out.println("Content Type is =>  "+ response.header("Content-Type"));
        Assert.assertEquals(contentType , "application/json; charset=utf-8","not matched a content type");

        Reporter.log("get a Server Value");
        String serverType = response.header("server");
        System.out.println("Server value =>  "+serverType);
        Assert.assertEquals(serverType , "cloudflare","not matched a server type");

        Reporter.log("Get a Content-Encoding value");
        String acceptLanguage = response.getHeader("Content-Encoding");
        System.out.println("Content-Encoding =>  "+acceptLanguage);
        Assert.assertEquals(acceptLanguage , "gzip","not matched a Content-Encoding");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Reporter.log("Get a all value of Headers in 3 different way");
        Headers allHeaders = response.getHeaders();
        System.out.println("Whole header value: "+ allHeaders+"/n");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Headers allHeaders1 = response.headers();
        System.out.println("Header using headers: "+allHeaders1 +"/n");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for(Header header : response.headers())
        {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }

    }
}
