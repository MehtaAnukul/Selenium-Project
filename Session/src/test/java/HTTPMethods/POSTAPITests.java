package HTTPMethods;

import Utilities.APIEndPoints;
import Utilities.ApiHelpers;
import Utilities.Constants;
import Utilities.JavaHelpers;
import datafactory.event.EventList;
import dataobjects.roamy.admin.event.add.AddResponse;
import dataobjects.roamy.admin.event.cancelEvent.CancelEventRequest;
import dataobjects.roamy.admin.event.cancelEvent.CancelValidationResponse;
import dataobjects.roamy.admin.event.list.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;

public class POSTAPITests
{
    @Test (priority = 4)
    public void Event_POST_JSONRequest_SingleLineMessage_List_RemoveSkip_UnprocessableEntity()
    {
        ApiHelpers helper = new ApiHelpers();

        //Given
        ListRequest requestListData = new ListRequest();
        requestListData.setTake("1000");

        //When
        Response listResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(requestListData).
                when().post(APIEndPoints.EventList);
        String error = listResponse.path("error");

        //Then
        Assert.assertEquals(listResponse.getStatusCode(),422,"Status code doesn't matched");
        Assert.assertEquals(error,"Missing parameter: The 'skip' is required.","Skip missing error validation message is not matched");
    }

    @Test (priority = 2)
    public void Event_POST_JSONRequest_ResponseWithChildData_List_Success()
    {
        ApiHelpers helper = new ApiHelpers();

        //Given
        ListRequest requestListData = new ListRequest();
        requestListData.setTake("1000");
        requestListData.setSkip("0");

        EventList sortFilterList = new EventList();
        List<Sort> sortList = sortFilterList.getEventListCreatedAtAsc();

        Filter filterList = sortFilterList.getEventList();

        Search searchData = new Search();
        searchData.setFields(Collections.singletonList("name"));
        searchData.setValue("Sou");

        requestListData.setSort(sortList);
        requestListData.setFilter(filterList);
        requestListData.setSearch(searchData);

        //When
        Response listResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(requestListData).
                when().post(APIEndPoints.EventList);
        ListResponse responseList = listResponse.as(ListResponse.class);

        List<String> actualValue = new ArrayList<>();

        for (int i = 0; i <= responseList.getTotal() - 1; i++) {
            actualValue.add(responseList.getData().get(i).getCreatedAt());
        }

        List<String> expectedValue = new ArrayList<>();
        expectedValue.addAll(actualValue);
        Collections.sort(actualValue, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        //Then
        Assert.assertEquals(listResponse.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertTrue(responseList.getTotal()>0,"Total is 0");
        Assert.assertNotNull(responseList.getTotal(),"Total is null");
        Assert.assertEquals(expectedValue,actualValue,"Sorting data is not matched");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getUuid().trim().isEmpty() && el.getUuid() != null),"Uuid is null");
        Assert.assertTrue(responseList.getData().stream().allMatch(el->el.getName().startsWith("Sou")),"Event name is not start with the 'Sou'");
      //  Assert.assertFalse(responseList.getData().stream().anyMatch(el ->el.getName().equals("Sou")),"Event is start with the 'Sou'");
        Assert.assertTrue(responseList.getData().stream().allMatch(el->el.getStatusName().equals("Draft")),"Status name is not matched with Filter input");
        Assert.assertTrue(responseList.getData().stream().allMatch(el->el.getEventCode().startsWith("ROAMY")),"Event code doesn't matched with Format");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getLocationUuid().trim().isEmpty() && el.getLocationUuid()!=null),"Location uuid is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getLocationName().trim().isEmpty() && el.getLocationName()!=null),"Location name is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getCountryUuid().trim().isEmpty() && el.getCountryUuid()!=null),"Country uuid is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getCountryName().trim().isEmpty() && el.getCountryName()!=null),"Country name is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getGenreUuid().trim().isEmpty() && el.getGenreUuid()!=null),"Genre uuid is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getGenreName().trim().isEmpty() && el.getGenreName()!=null),"Genre name is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getPrefix().trim().isEmpty() && el.getPrefix() != null),"Prefix is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getStartDate().trim().isEmpty() && el.getStartDate() != null),"Start Date is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getEndDate().trim().isEmpty() && el.getEndDate() != null),"End Date is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getPublishAt().trim().isEmpty() && el.getPublishAt() != null),"Publish at Date is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getSaleStartFrom().trim().isEmpty() && el.getSaleStartFrom() != null),"Sale Start From Date is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getSaleEndAt().trim().isEmpty() && el.getSaleEndAt() != null),"Sale End Date is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getThumbnailImage().trim().isEmpty() && el.getThumbnailImage() != null),"Thumbnail Image is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getCoverImage().trim().isEmpty() && el.getCoverImage() != null),"Cover Image is null");
        Assert.assertTrue(responseList.getData().stream().allMatch(el->el.getCoverImage().startsWith("https://")),"Cover Image is not in url format");
        Assert.assertTrue(responseList.getData().stream().allMatch(el->el.getThumbnailImage().startsWith("https://")),"Thumbnail Image is not in url format");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getUrlSlug().trim().isEmpty() && el.getUrlSlug() != null),"Url Slug is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getSeoTitle().trim().isEmpty() && el.getSeoTitle() != null),"SEO Title is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getSeoDescription().trim().isEmpty() && el.getSeoDescription() != null),"SEO Description is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getStatusName().trim().isEmpty() && el.getStatusName() != null),"Status Name is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getStatusUuid().trim().isEmpty() && el.getStatusUuid() != null),"Status Uuid is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getCapacity() != 0 && el.getCapacity() != null && el.getCapacity() > 1000000),"Capacity is null or out of range");
        Assert.assertTrue(responseList.getData().stream().allMatch(el->el.getTicketsSold() != null),"Ticket sold is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getSold().trim().isEmpty() && el.getSold() != null),"Sold is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getCreatedAt().trim().isEmpty() && el.getCreatedAt() != null),"Created At is null");
        Assert.assertTrue(responseList.getData().stream().noneMatch(el->el.getCreatedBy().trim().isEmpty() && el.getCreatedBy() != null),"Created By is null");
    }

    @Test (priority = 0)
    public void Event_POST_FormRequest_Add_Success()
    {
        ApiHelpers helper = new ApiHelpers();

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

        Response genresList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Genres);
        String genre = (String) genresList.getBody().jsonPath().getList("value").get(0);
        Response locationList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Locations);
        String location = (String) locationList.getBody().jsonPath().getList("value").get(0);
        Response inclusionsList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Inclusions);
        Response artistsList = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Artists);

        //When
        Response eventResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuthMultiPart()).
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
                multiPart("inclusions[0]",inclusionsList.getBody().jsonPath().getList("value").get(0)).
                multiPart("artists[0]",artistsList.getBody().jsonPath().getList("value").get(0)).
                multiPart("flyer_images[0][image]",new File("src/main/java/resources/Event/Event_FlyerImage.jpg")).
                multiPart("flyer_images[0][sort_order]",1).
                multiPart("flyer_images[0][is_cover_image]",1).
                multiPart("flyer_images[1][image]",new File("src/main/java/resources/Event/FlyerImage1.jpg")).
                multiPart("flyer_images[1][sort_order]",2).
                multiPart("flyer_images[1][is_cover_image]",0).
                multiPart("flyer_images[2][image]",new File("src/main/java/resources/Event/FlyerImage1.jpg")).
                multiPart("flyer_images[2][sort_order]",3).
                multiPart("flyer_images[2][is_cover_image]",0).
                multiPart("itineraries["+startDate+"][0]",new JavaHelpers().getAlphaNumericString(10)).
                multiPart("itineraries["+startDate+"][1]",new JavaHelpers().getAlphaNumericString(10)).
                multiPart("itineraries["+new JavaHelpers().getFuturePastCurrentDateAndTime(11,0).substring(0,10)+"][0]",new JavaHelpers().getAlphaNumericString(10)).
                multiPart("itineraries["+endDate+"][0]",new JavaHelpers().getAlphaNumericString(10)).
                when()
                .post(APIEndPoints.EventAdd);
        AddResponse response = eventResponse.as(AddResponse.class);

        //Then
        Assert.assertEquals(eventResponse.getStatusCode(),201,"Status code doesn't matched");
        Assert.assertEquals(response.getMessage(),"Event has been created.","Message is not matched");
        Assert.assertNotNull(response.getData().getUuid(),"Uuid is not present");
        Assert.assertEquals(response.getData().getUuid().length(), 36, "Uuid format doesn't matched");
    }

    @Test (priority = 1)
    public void Event_POST_JSONRequest_ArrayResponse_CancelEvent_AlreadyCancelled_UnprocessableEntity()
    {
        ApiHelpers helper = new ApiHelpers();

        Reporter.log("Step -1: To get a uuid of new added event from List");
        //Given-When
        ListRequest requestData = new ListRequest();
        requestData.setTake(Constants.take);
        requestData.setSkip(Constants.skip);
        Response listResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(requestData).
                when().post(APIEndPoints.EventList);
        ListResponse responseListData = listResponse.as(ListResponse.class);

        //Then
        String uuid = responseListData.getData().stream().filter(el -> el.getStatusName().equals("Cancelled")).findFirst().get().getUuid();

        Reporter.log("Step-2: To cancel event because of some reason");
        //Given
        CancelEventRequest cancelRequest = new CancelEventRequest();
        cancelRequest.setEventUuid(uuid);
        cancelRequest.setRemarks(new JavaHelpers().getAlphaNumericString(100));

        //When
        Response responseCancelEvent = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationWithoutAuth()).body(cancelRequest).
                when().post(APIEndPoints.EventCancel);
        CancelValidationResponse validationMessage = responseCancelEvent.as(CancelValidationResponse.class);

        //Then
        Assert.assertEquals(responseCancelEvent.getStatusCode(),422,"Status code doesn't matched");
        Assert.assertEquals(validationMessage.getEventUuid().get(0),"This event has already been cancelled.","Validation message of cancel event doesn't matched");
        Assert.assertEquals(validationMessage.getEventUuid().toString(),"[This event has already been cancelled.]","Validation message of cancel event doesn't matched");
        Assert.assertNotEquals(validationMessage.getEventUuid().get(0),"[This event has already been cancelled.]","Wrong data matched");

        /*SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(validationMessage.getEventUuid().get(0),"[This event has already been cancelled.]");
        softAssert.assertEquals(validationMessage.getEventUuid().get(0),"This event has already been cancelled.");
        softAssert.assertEquals(validationMessage.getEventUuid().get(0),"his event has already been cancelled.");
        softAssert.assertAll();*/
    }
}

