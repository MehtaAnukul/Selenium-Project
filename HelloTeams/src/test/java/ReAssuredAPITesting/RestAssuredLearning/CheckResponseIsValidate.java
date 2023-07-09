package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class CheckResponseIsValidate
{
    final static String ROOT_URI = "https://localhost:7000/employees";

    /*Test 1 : Operation perform successfully or not */
    @Test
    public void getValidateStatusCode()
    {
        Reporter.log("Base URl mean where u want to request");
        RestAssured.baseURI = "https://reqres.in/";

        Reporter.log("HTTP request which is given to restAssured");
        RequestSpecification httpRequest = RestAssured.given();

        Reporter.log("Call a server and get a response");
        Response response = httpRequest.request(Method.GET,"api/users?page=2");

        Reporter.log("Get a statusCode,statusLine and check that are as expected");
        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();

        Assert.assertEquals(statusCode, 200,"Correct status code does not returned");
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK","Correct status line does not returned");
    }

}
