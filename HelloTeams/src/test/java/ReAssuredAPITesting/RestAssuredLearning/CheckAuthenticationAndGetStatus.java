package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;


    public class CheckAuthenticationAndGetStatus
    {
        @Test
        public void AuthenticationCheckAndGetAccordingStatus()
        {
            Reporter.log("Base URl mean where u want to request");
            RestAssured.baseURI = "https://reqres.in/";

            Reporter.log("HTTP request which is given to restAssured");
            RequestSpecification httpRequest = RestAssured.given();
            JSONObject requestParam = new JSONObject();
            requestParam.put("email","eve.holt@reqres.in");
            requestParam.put("password","cityslicka");
            httpRequest.body(requestParam.toJSONString());

            Reporter.log("Call a server and get a response");
            Response response = httpRequest.post("api/login");

            Reporter.log("Get a statusCode,statusLine and check that are as expected");
            int statusCode = response.getStatusCode();
            String statusMessage = response.body().asString();
            System.out.println(statusCode);
            System.out.println(statusMessage);
        }

    }
