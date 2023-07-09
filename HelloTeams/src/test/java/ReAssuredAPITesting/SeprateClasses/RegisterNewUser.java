package ReAssuredAPITesting.SeprateClasses;

import dataobjectsapi.ChildDataObjectOfRegisterUser;
import dataobjectsapi.Registration;
import dataobjectsapi.UserResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Constants;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

public class RegisterNewUser
{
    /*Test 1 : Register a user */
    @Test
    public void registerNewUserSuccessfully()
    {
        //Given
        Registration request = new Registration();
        request.setEmail("abc.def@ghi.in");
        request.setPassword("Ab123");

        //When
        Response response = given().baseUri(Constants.Base_URI).body(request).when()
                .post(Constants.Registration_Path);

        //Then
        Assert.assertEquals(response.getStatusCode(),201,"User does not register");
        Assert.assertTrue(response.asString().contains("id"));
        Assert.assertTrue(response.asString().contains("createdAt"));
    }

    /*Test 2 : Get list of all the Registered users */
    @Test
    public void getRegisteredUsers()
    {
        SoftAssert softAssert = new SoftAssert();

        //Given-When
        Response response = given().baseUri(Constants.Base_URI).get(Constants.List_Of_Users);
        UserResponse responseData = response.as(UserResponse.class);

        //Then
        softAssert.assertEquals(response.getStatusCode(),200,"Status code does not match");
        softAssert.assertTrue(responseData.getPage()>0,"pages are less than 0");
        softAssert.assertTrue(responseData.getPerPage()>0,"per page is less than 0");
        softAssert.assertTrue(responseData.getTotal()>0,"total value is less than 0");
        softAssert.assertTrue(responseData.getTotalPages()>0,"total pages are less than 0");
        softAssert.assertTrue(responseData.getData().stream().noneMatch(el -> el.getId() != null && el.getId() <0),"User id is not null");
        softAssert.assertEquals(responseData.getData().stream().filter(el -> el.getId() != null && el.getId() <0).count(),0,"user id is not null");
        softAssert.assertEquals(responseData.getData().stream().filter(el -> el.getEmail() != null && el.getEmail().trim().isEmpty()).count(),0,"user email is not null");
        softAssert.assertEquals(responseData.getData().stream().filter(el -> el.getFirstName() != null && el.getFirstName().trim().isEmpty()).count(),0,"user firstName is not null");
        softAssert.assertEquals(responseData.getData().stream().filter(el -> el.getLastName() != null && el.getLastName().trim().isEmpty()).count(),0,"user lastName is not null");
        softAssert.assertEquals(responseData.getData().stream().filter(el -> el.getAvatar() != null && el.getAvatar().trim().isEmpty()).count(),0,"user Avatar is not null");

        softAssert.assertTrue(responseData.getAd().getUrl() != null,"Company name is not present");
        softAssert.assertTrue(responseData.getAd().getText() != null,"Text is not present");
        softAssert.assertTrue(responseData.getAd().getUrl() != null, "URL is not present");
        softAssert.assertAll();
    }

    /*Test 3: update a registered user data*/
    @Test
    public void putRegisteredUserData()
    {
        //Given
        Registration request = new Registration();
        request.setEmail("janet.waver@reqres.in");

        //When
        Response response = given().baseUri(Constants.Base_URI).body(request).when()
                .put(Constants.Put_User);

        //Then
        Assert.assertTrue(response.asString().contains("updatedAt"),"not updated");
    }


    /*Test 4: Delete a registered user data*/
    @Test
    public void deleteRegisteredUserData()
    {
        //Given - When
        Response response = delete(Constants.Base_URI+ Constants.Put_User);

        //Then
        Assert.assertEquals(response.getStatusCode(),204,"Not delete User");
    }

    /*Test 5: User not found in "Get" method */
    @Test
    public void userNotFoundInData()
    {
        //Given - When
        Response response = given().baseUri(Constants.Base_URI).get(Constants.Registration_Path);

        //then
        Assert.assertEquals(response.getStatusCode(),404,"Something is Wrong");
    }
}
