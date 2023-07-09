package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class getRequestResponse
{
    @Test
    public void getRequestProperResponse()
    {
        Reporter.log("Base URl mean where u want to request");
        RestAssured.baseURI = "https://reqres.in/";

        Reporter.log("HTTP request which is given to restAssured");
        RequestSpecification httpRequest = RestAssured.given();

        Reporter.log("Call a server and get a response");
        Response response = httpRequest.request(Method.GET,"api/users?page=2");

        Reporter.log("transfer response of body part in String(Readable language)");
        ResponseBody body = response.getBody();
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + body.asString());
        System.out.println("Response Body is =>  " + response.asString());
        Assert.assertFalse(responseBody.contains("api/users?page=2"),"Response body contains api");
        //  Assert.assertEquals(responseBody.contains("api/users?page=2"),false,"not match"); // true false always write without ""

        Reporter.log("Check string present by ignoring alphabet casing");
        Assert.assertFalse(responseBody.toUpperCase().contains("API/USERS?PAGE=2"),"Response body contain api");

    }
}
/* not able to do because string does not contain path value
* // First get the JsonPath object instance from the Response interface
 JsonPath jsonPathEvaluator = response.jsonPath();

 // Let us print the city variable to see what we got
 System.out.println("City received from Response " + jsonPathEvaluator.get("City"));
 * */