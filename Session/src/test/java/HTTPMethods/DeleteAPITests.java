package HTTPMethods;

import Utilities.APIEndPoints;
import Utilities.ApiHelpers;
import Utilities.Constants;
import Utilities.JavaHelpers;
import dataobjects.roamy.admin.event.show.ShowInvalidInputResponse;
import dataobjects.roamy.admin.event.show.ShowValidationResponse;
import dataobjects.roamy.admin.event.list.ListRequest;
import dataobjects.roamy.admin.event.list.ListResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DeleteAPITests
{
    @Test
    public void Event_Delete_SingleLineMessage_Delete_UnauthenticatedAdmin_Unauthorized()
    {
        ApiHelpers helper = new ApiHelpers();

        Reporter.log("Step -1: Add new Event");
        //Given
        String name = "Sound Spirit Fest"+ new JavaHelpers().getCurrentTime();
        String prefix = new JavaHelpers().getAlphaNumericString(5).toUpperCase(Locale.ROOT);
        File thumbnailImage = new File("src/main/java/resources/Event/Event_800x800.jpg");
        File lineUpImage = new File("src/main/java/resources/Event/Event_FlyerImage.jpg");
        String startDate = new JavaHelpers().getFuturePastCurrentDateAndTime(10,0).substring(0,10);
        String endDate = new JavaHelpers().getFuturePastCurrentDateAndTime(12,0).substring(0,10);
        String startTime = new JavaHelpers().getFuturePastCurrentDateAndTime(0,0).substring(11,16);
        String endTime = new JavaHelpers().getFuturePastCurrentDateAndTime(0,62).substring(11,16);
        String publishAt = new JavaHelpers().getFuturePastCurrentDateAndTime(2,5);
        String saleStartFrom = new JavaHelpers().getFuturePastCurrentDateAndTime(3,5);
        String saleEndAt = new JavaHelpers().getFuturePastCurrentDateAndTime(4,5);
        String valueAs100 = new JavaHelpers().getAlphaNumericString(100);
        String capacity = new JavaHelpers().getRandomNumber(3);
        File FlyerImage = new File("src/main/java/resources/Event/FlyerImage1.jpg");
        File FlyerImage1 = new File("src/main/java/resources/Event/FlyerImage1.jpg");
        Response genresList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Genres);
        String genre = (String) genresList.getBody().jsonPath().getList("value").get(0);
        Response locationList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Locations);
        String location = (String) locationList.getBody().jsonPath().getList("value").get(0);
        Response inclusionsList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Inclusions);
        String inclusions = (String) inclusionsList.getBody().jsonPath().getList("value").get(0);
        Response artistsList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Artists);
        String artists = (String) artistsList.getBody().jsonPath().getList("value").get(0);

        //When
        Response eventAddResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuthMultiPart()).
                multiPart("name", name).
                multiPart("prefix",prefix).
                multiPart("location_uuid",location).
                multiPart("genre_uuid", genre).
                multiPart("description",valueAs100).
                multiPart("capacity",capacity).
                multiPart("start_date",startDate).
                multiPart("start_time",startTime).
                multiPart("end_date",endDate).
                multiPart("end_time",endTime).
                multiPart("publish_at", publishAt).
                multiPart("sale_start_from", saleStartFrom).
                multiPart("sale_end_at",saleEndAt).
                multiPart("thumbnail_image",thumbnailImage).
                multiPart("line_up_image",lineUpImage).
                multiPart("url_slug",valueAs100.toLowerCase()).
                multiPart("seo_title",valueAs100).
                multiPart("seo_description",valueAs100).
                multiPart("inclusions[0]",inclusions).
                multiPart("artists[0]",artists).
                multiPart("flyer_images[0][image]",FlyerImage).
                multiPart("flyer_images[0][sort_order]",1).
                multiPart("flyer_images[0][is_cover_image]",1).
                multiPart("flyer_images[1][image]",FlyerImage1).
                multiPart("flyer_images[1][sort_order]",2).
                multiPart("flyer_images[1][is_cover_image]",0).
                multiPart("flyer_images[2][image]",FlyerImage1).
                multiPart("flyer_images[2][sort_order]",3).
                multiPart("flyer_images[2][is_cover_image]",0).
                multiPart("itineraries["+startDate+"][0]",valueAs100).
                multiPart("itineraries["+startDate+"][1]",valueAs100).
                multiPart("itineraries["+new JavaHelpers().getFuturePastCurrentDateAndTime(11,0).substring(0,10)+"][0]",valueAs100).
                multiPart("itineraries["+endDate+"][0]",valueAs100).
                when()
                .post(APIEndPoints.EventAdd);

        //Then
        Assert.assertEquals(eventAddResponse.getStatusCode(),201,"Status code doesn't matched");

        Reporter.log("Step -2: To get a uuid of new added event from List");
        //Given-When
        ListRequest requestListData = new ListRequest();
        requestListData.setTake(Constants.take);
        requestListData.setSkip(Constants.skip);
        Response listResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(requestListData).
                when().post(APIEndPoints.EventList);
        ListResponse responseListData = listResponse.as(ListResponse.class);

        //Then
        String uuid = responseListData.getData().stream().filter(el -> el.getName().contains(name)).findFirst().get().getUuid();

        Reporter.log("Step -3: To delete an event");
        //Given- When
        Response eventDeleteResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationBaseURI()).
                when().delete(APIEndPoints.EventDelete + uuid);
        String validationMessage = eventDeleteResponse.path("message");
        ShowValidationResponse message = eventDeleteResponse.as(ShowValidationResponse.class);

        //Then
        Assert.assertEquals(eventDeleteResponse.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertEquals(validationMessage,"Event has been deleted.","Event deleted message is not matched");
        Assert.assertEquals(message.getMessage(),"Event has been deleted.","Event deleted message is not matched through the class");
    }

    @Test
    public void Event_Delete_ArrayMessage_Delete_InvalidEventUuid_UnprocessableEntity()
    {
        ApiHelpers helper = new ApiHelpers();
        String Uuid = "075e9340-e091-11eb-ae63-06060ef335eq";

        //Given- When
        Response eventDeleteResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationBaseURI()).
                when().delete(APIEndPoints.EventDelete+ Uuid);
        ShowInvalidInputResponse validationMessage = eventDeleteResponse.as(ShowInvalidInputResponse.class);

        //Then
        Assert.assertEquals(eventDeleteResponse.getStatusCode(),422,"Status code doesn't matched");
        Assert.assertEquals(validationMessage.getEventUuid().get(0),"The event uuid must be in a valid UUID format.","Invalid Uuid validation message is not matched through Array index");
        Assert.assertEquals(validationMessage.getEventUuid().toString(),"[The event uuid must be in a valid UUID format.]","Invalid Uuid validation message is not matched through Array");
    }
}
