package ReAssuredAPITesting.RestAssuredLearning;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TestRestAssuredShortForms
{
    final static String ROOT_URI = "https://reqres.in/";

    @Test
    public void simpleGetTest()
    {
        Response response = get(ROOT_URI + "api/users?page=2"); //static method import
        System.out.println(response.asString());
    }

    @Test
    public void post_test() {
        Response response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Lisa\",\"salary\": \"2000\"}")
                .when()
                .post(ROOT_URI + "/create");
        System.out.println("POST Response\n" + response.asString());
        // tests
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("name", Matchers.is("Lisa"));
    }
}
