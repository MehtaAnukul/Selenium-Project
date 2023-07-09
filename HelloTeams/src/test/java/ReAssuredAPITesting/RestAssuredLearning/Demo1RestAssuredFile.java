package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Demo1RestAssuredFile
{
    @Test
    public void getListRestAssuredWithJsonFile()
    {

        RestAssured.baseURI = "https://reqres.in/";

        Reporter.log("HTTP request which is given to restAssured");
        RequestSpecification httpRequest = RestAssured.given();

        Reporter.log("Call a server and get a response");
        Response response = httpRequest.get("api/users?page=2");

        String jsonString = response.asString();
        List<Map<String, String>> users = JsonPath.from(jsonString).get("data");
        Assert.assertTrue(users.size() > 0);

        String email = users.get(1).get("email");
        System.out.println(email);
        System.out.println("************************");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> allUsers = jsonPathEvaluator.getList("data.email");

        for(String user : allUsers)
        {
            System.out.println(user);
        }
        System.out.println("all user name : "+allUsers.size()+allUsers);
    }
}

//Book[] books = response.jsonPath().getObject("books",Book[].class );  Book[] Json file array name bcz convert JSON array to array