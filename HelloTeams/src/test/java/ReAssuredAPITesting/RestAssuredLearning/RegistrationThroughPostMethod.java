package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RegistrationThroughPostMethod
{
    @Test
    public void CreateUserSuccessful()
    {
        Reporter.log("Base URl");
        RestAssured.baseURI = "https://reqres.in/";

        Reporter.log("HTTP request which is given to restAssured");
        RequestSpecification httpRequest = RestAssured.given();

        Reporter.log("User information pass as a parameter");
        JSONObject requestParam = new JSONObject();
        requestParam.put("name","michael");
        requestParam.put("job","Junior");
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParam.toJSONString());
        Response response = httpRequest.post("api/users");

        Reporter.log("check whether user successfully added or not");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,201,"user does not created");

        System.out.println("The status code received: "+statusCode);
        System.out.println("The response of body: "+response.getBody().asString());

      /*  String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");*/
    }
}
