package sessionTests;

import Utilities.APIEndPoints;
import Utilities.ApiHelpers;
import Utilities.Constants;
import dataobjects.roamy.customer.customerWithoutLogin.login.LoginRequest;
import dataobjects.roamy.customer.customerWithoutLogin.login.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class APITests
{
    @Test
    public void Option_GET_Artist_Success()
    {
        //Given-When
        Response artistListResponse = RestAssured.when().get(APIEndPoints.BaseUri+APIEndPoints.Artists);

        //Then
        Assert.assertEquals(artistListResponse.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertNotEquals(artistListResponse.getHeader("Connection"),"Connection","Header value doesn't matched");
        Assert.assertNotNull(artistListResponse.getContentType(),"Content type is null");
        Assert.assertFalse(artistListResponse.getBody().jsonPath().getList("label").isEmpty(),"Label is null");
        Assert.assertNull(artistListResponse.getBody().jsonPath().getList("New").get(0),"abc property is present");
        Assert.assertTrue(artistListResponse.getBody().jsonPath().getList("label").stream().noneMatch(el->el.equals("abc")),"abc label is present");
        Assert.assertFalse(artistListResponse.getBody().jsonPath().getList("label").stream().allMatch(el->el.equals("artist")),"all label name is artist");
        Assert.assertTrue(artistListResponse.getBody().jsonPath().getList("label").stream().anyMatch(el->el.equals("New")),"New label is not matched");
        long abc = artistListResponse.getBody().jsonPath().getList("label").stream().filter(el -> el.equals("Holly Schroeder")).count();
        System.out.println(abc);
    }

    @Test
    public void Login_POST_CustomerToken_Success()
    {
        RestAssured.baseURI = "https://apidev.roamy.earth";

        //Given-When
        Response response = given().contentType("application/json").body("{\n" +
                "    \"email\": \"romin@jignect.tech\",\n" +
                "    \"password\": \"Password@1234\"\n" +
                "}").when().post(APIEndPoints.CustomerLogin);

        String accessToken = response.then().extract().path("access_token");

        //Then
        response.then().statusCode(200);
        response.then().body("email",Matchers.is("romin@jignect.tech"));
        Assert.assertEquals(response.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertNotNull(accessToken,"Access token is null");
        Assert.assertEquals(response.getBody().jsonPath().get("email"),"romin@jignect.tech","Customer email is not matched");
        Assert.assertNotNull(response.getBody().jsonPath().getString("uuid"),"Uuid is null");
    }

    @Test
    public void Bookings_GET_Booking_Success()
    {
        RestAssured.baseURI = "https://apidev.roamy.earth";

        Reporter.log("Step 1: Hit a 'Login' API, to get a access token");
        //Given
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParam = new JSONObject();
        requestParam.put("email","romin@jignect.tech");
        requestParam.put("password","Password@1234");
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParam);

        //When
        Response response = httpRequest.post(APIEndPoints.CustomerLogin);

        //Then
        Assert.assertEquals(response.getStatusCode(),200,"Status code doesn't matched");
        String accessToken = response.getBody().jsonPath().get("access_token");

        Reporter.log("Step 2: To get booking details");
        //Given
        RequestSpecification requestArtist = RestAssured.given();
        requestArtist.auth().oauth2(accessToken);
        requestArtist.param("type","past");

        //When
        Response bookingList = requestArtist.get(APIEndPoints.Bookings);

        //Then
        Assert.assertEquals(bookingList.getStatusCode(),200,"Status code doesn't matched");

        Reporter.log("Method - 1");
        Assert.assertNotNull(bookingList.getBody().jsonPath().getList("data"),"data list is null");
        Assert.assertNotNull(bookingList.getBody().jsonPath().getList("data.booking_id"),"Booking id list is null");

        Reporter.log("Method - 2");
        String jsonString = bookingList.asString();
        Assert.assertNotNull(JsonPath.from(jsonString).get("data"),"data list is null");
        Assert.assertFalse(JsonPath.from(jsonString).getList("data.booking_id").isEmpty() && JsonPath.from(jsonString).getList("data.booking_id") != null,"Booking id is null");

        Reporter.log("Method - 3");
        JsonPath jsonPathEvaluator = bookingList.jsonPath();
  //      List name = Collections.singletonList(jsonPathEvaluator.getList("data.booking_id").stream().filter(el -> el.equals("R449BAGF000011")).findFirst().get().toString());
    //    System.out.println(name.get(0));

        Assert.assertNotNull(jsonPathEvaluator.getList("data"),"data is null");
        Assert.assertTrue(jsonPathEvaluator.getList("data.booking_id").stream().anyMatch(el->el.equals("R449BAGF000011")),"Booking id doesn't matched");
        Assert.assertFalse(jsonPathEvaluator.getList("data.event_status").stream().allMatch(el->el.equals("Completed")),"event status completed matched");
        Assert.assertTrue(jsonPathEvaluator.getList("data.event_status").stream().noneMatch(el->el.equals("Draft")),"event status draft matched");
    }

    @Test
    public void Login_POST_CustomerToken_Success_SoftAssert()
    {
        SoftAssert softAssert = new SoftAssert();
        RestAssured.baseURI = "https://apidev.roamy.earth";

        //Given
        RequestSpecification httpRequest = given();
        JSONObject requestParam = new JSONObject();
        requestParam.put("email", Constants.CustomerEmail);
        requestParam.put("password",Constants.CustomerPassword);
        httpRequest.body(requestParam);

        //When
        Response response = httpRequest.contentType("application/json").when().post(APIEndPoints.CustomerLogin);
        String accessToken = response.then().extract().path("access_token");

        //Then
        softAssert.assertEquals(response.getStatusCode(),200,"Status code doesn't matched");
        softAssert.assertNotNull(accessToken,"Access token is null");
        softAssert.assertEquals(response.getBody().jsonPath().get("email"),"chhevangee@jbsols.com","Email id is not matched");
        softAssert.assertNotNull(response.getBody().jsonPath().get("mobile_number"),"number is null");
        softAssert.assertNotNull(response.getBody().jsonPath().get("mobile_country_code_length"),"Code length is null");
        softAssert.assertNotNull(response.getBody().jsonPath().get("last_name"),"Last name is null");
        softAssert.assertNotNull(response.getBody().jsonPath().get("first_name"),"First name is null");
        softAssert.assertNotNull(response.getBody().jsonPath().get("uuid"),"Uuid is null");
        softAssert.assertNotNull(response.getBody().jsonPath().get("token_type"),"Token is null");
        softAssert.assertAll();
    }

    @Test
    public void Login_POST_CustomerToken_Success_MVC()
    {
        ApiHelpers helper = new ApiHelpers();

        //Given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(Constants.CustomerEmail);
        loginRequest.setPassword(Constants.CustomerPassword);

        //When
        Response response =
                given().
                        spec(helper.requestSpecificationWithoutAuth()).
                        body(loginRequest).
                        when().
                        post(APIEndPoints.CustomerLogin);
        LoginResponse responseLogin = response.as(LoginResponse.class);

        //Then
        Assert.assertEquals(response.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertEquals(responseLogin.getEmail(),Constants.CustomerEmail,"Email is not matched");
        Assert.assertNotNull(responseLogin.getUuid(),"Uuid is null");
        Assert.assertNotNull(responseLogin.getFirstName(),"First name is null");
        Assert.assertNotNull(responseLogin.getLastName(),"Last name is null");
        Assert.assertNotNull(responseLogin.getMobileCountryCodeLength(),"Mobile Country code is null");
        Assert.assertNotNull(responseLogin.getMobileNumber(),"Mobile number is null");
        Assert.assertNotNull(responseLogin.getAccessToken(),"Access Token is null");
        Assert.assertEquals(responseLogin.getTokenType(),"Bearer","First name is null");
    }

    @Test
    public void getClientToken()
    {
        //given-When
        Response response = given().header("Content-Type","multipart/form-data").
                multiPart("grant_type","client_credentials").
                when().post();

    }
}












