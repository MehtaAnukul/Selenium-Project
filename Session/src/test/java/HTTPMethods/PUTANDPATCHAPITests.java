package HTTPMethods;

import Utilities.APIEndPoints;
import Utilities.ApiHelpers;
import Utilities.Constants;
import Utilities.JavaHelpers;
import dataobjects.roamy.admin.country.add.AddCountryRequest;
import dataobjects.roamy.admin.country.list.ListRequest;
import dataobjects.roamy.admin.country.list.ListResponse;
import dataobjects.roamy.admin.country.update.UpdateCountryRequest;
import dataobjects.roamy.customer.customerWithLogin.editEmail.EditEmailRequest;
import dataobjects.roamy.customer.customerWithLogin.editEmail.EditEmailValidationResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class PUTANDPATCHAPITests
{
    @Test
    public void Country_POST_Update_Success()
    {
        ApiHelpers helper = new ApiHelpers();
        String name = "Australia"+ new JavaHelpers().getCurrentTime();

        Reporter.log("Step -1: To add Country");
        //Given
        AddCountryRequest addCountryRequest = new AddCountryRequest();
        addCountryRequest.setName(name);
        addCountryRequest.setCurrencyUuid(Constants.currency_Uuid);
        addCountryRequest.setDialingCode(Collections.singletonList("91"));

        //When
        Response countryResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(addCountryRequest).
                when().post(APIEndPoints.CountryAdd);

        //Then
        Assert.assertEquals(countryResponse.getStatusCode(),201,"status code doesn't matched");

        Reporter.log("Step -2: To get uuid from List");
        //Given
        ListRequest requestData = new ListRequest();
        requestData.setTake(Constants.take);
        requestData.setSkip(Constants.skip);

        //When
        Response responseList = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(requestData).
                when().post(APIEndPoints.CountryList);
        ListResponse responseDataList = responseList.as(ListResponse.class);

        //Then
        String uuid = responseDataList.getData().stream().filter(el -> el.getName().equals(name)).findFirst().get().getUuid();

        Reporter.log("Step -3: To update country");
        //Given
        UpdateCountryRequest countryUpdateRequest = new UpdateCountryRequest();
        countryUpdateRequest.setName(name);
        countryUpdateRequest.setCurrencyUuid(Constants.currency_Uuid);
        countryUpdateRequest.setDialingCode(Collections.singletonList("36"));

        //When
        Response countryUpdateResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(countryUpdateRequest).
                when().put(APIEndPoints.CountryUpdate+uuid);
        String successMessage = countryUpdateResponse.path("message");

        //Then
        Assert.assertEquals(countryUpdateResponse.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertEquals(successMessage,"Country has been updated.","Message doesn't matched");

    }

    @Test
    public void CustomerWithLogin_PATCH_EditEmail_UnprocessableEntity()
    {
        ApiHelpers helper = new ApiHelpers();

        //Given
        EditEmailRequest requestData = new EditEmailRequest();
        requestData.setEmail("harshita@jbsols.com");
        requestData.setUrlTemplate("https://roamy.earth/verify/{token}");

        //When
        Response response = given().spec(helper.getCustomerToken()).spec(helper.requestSpecificationWithoutAuth()).body(requestData).
                when().patch(APIEndPoints.EditEmail);
        EditEmailValidationResponse validationResponse = response.as(EditEmailValidationResponse.class);

        //Then
        Assert.assertEquals(response.getStatusCode(),422,"Status code doesn't matched");
        Assert.assertEquals(validationResponse.getEmail().toString(),"[The email has already been taken.]","Validation message doesn't matched");
        Assert.assertEquals(validationResponse.getEmail().get(0),"The email has already been taken.","Validation message doesn't matched");

    }
}
