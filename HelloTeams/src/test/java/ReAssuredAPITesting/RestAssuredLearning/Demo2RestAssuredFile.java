package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;

public class Demo2RestAssuredFile {
    @Test
    public void testRestAssuredWithJsonFile() {
        File jsonDataFile = new File("C://Users//Chhevangee//Documents//HelloTeams//src//test//java//ReAssuredAPITesting//RestAssuredLearning//Demo1.json");

        RestAssured.baseURI = "https://reqres.in/";
        Reporter.log("HTTP request which is given to restAssured");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body(jsonDataFile);

        Reporter.log("Call a server and get a response");
        Response response = httpRequest.post("api/users/23");

        System.out.println("Header : "+response.headers());
        System.out.println("Status code :" + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201, "User not created");
        Assert.assertTrue(response.asString().contains("id"));

        int id = JsonPath.from(response.asString()).getInt("id");
        System.out.println(id);
        String createTime = response.getBody().jsonPath().getString("createdAt");
        System.out.println(createTime);
    }
}
