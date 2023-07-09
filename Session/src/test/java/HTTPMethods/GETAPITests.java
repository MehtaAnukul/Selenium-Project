package HTTPMethods;

import Utilities.APIEndPoints;
import Utilities.ApiHelpers;
import Utilities.Constants;
import Utilities.JavaHelpers;
import dataobjects.roamy.admin.event.show.ShowInvalidInputResponse;
import dataobjects.roamy.admin.event.show.ShowResponse;
import dataobjects.roamy.admin.event.show.ShowValidationResponse;
import dataobjects.roamy.admin.event.list.ListRequest;
import dataobjects.roamy.admin.event.list.ListResponse;
import dataobjects.roamy.admin.options.artists.ArtistResponse;
import dataobjects.roamy.customer.booking.Bookings;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class GETAPITests
{
    @Test
    public void Option_GET_StartWithArrayResponse_Artist_Success()
    {
        ApiHelpers helper = new ApiHelpers();

        //Given-When
        Response artistsListResponse = given().spec(helper.requestSpecificationBaseURI()).when().get(APIEndPoints.Artists);

        //Then
        Assert.assertEquals(artistsListResponse.getStatusCode(),200,"Status code doesn't match");

        Assert.assertNotNull(artistsListResponse.getBody().jsonPath().getList("label"), "Label is null");
        Assert.assertFalse(artistsListResponse.getBody().jsonPath().getList("label").isEmpty(),"Label is null");
        Assert.assertTrue(artistsListResponse.getBody().jsonPath().getList("label").stream().noneMatch(el -> el.equals("NULL")),"Label is null");
        Assert.assertTrue(artistsListResponse.getBody().jsonPath().getList("label").stream().anyMatch(el -> el.equals("-Artist 182230")));
        String Label = artistsListResponse.getBody().jsonPath().getList("label").get(0).toString();
        System.out.println(Label);

        Assert.assertNotNull(artistsListResponse.getBody().jsonPath().getList("value"),"Value is null");
        Assert.assertFalse(artistsListResponse.getBody().jsonPath().getList("value").isEmpty(),"value is null");
        Assert.assertTrue(artistsListResponse.getBody().jsonPath().getList("value").stream().noneMatch(el -> el.equals("NULL")),"value is null");
        Assert.assertTrue(artistsListResponse.getBody().jsonPath().getList("value").stream().anyMatch(el -> el.equals("f5c11e44-ffdf-11eb-ae63-06060ef335ec")));
        String Value = artistsListResponse.getBody().jsonPath().getList("value").get(0).toString();
        System.out.println(Value);
    }

    @Test
    public void Event_GET_SingleLineMessage_Show_UnauthenticatedAdmin_Unauthorized()
    {
        ApiHelpers helper = new ApiHelpers();
        String Uuid = "d6836b73-b475-11eb-a293-06060ef335ec";

        //Given- When
        Response eventShowResponse = given().spec(helper.getClientToken()).spec(helper.requestSpecificationBaseURI()).when()
                .get(APIEndPoints.EventSearch+ Uuid);
        String validationMessage = eventShowResponse.path("message");
        ShowValidationResponse message = eventShowResponse.as(ShowValidationResponse.class);

        //Then
        Assert.assertEquals(eventShowResponse.getStatusCode(),401,"Status code doesn't matched");
        Assert.assertEquals(validationMessage,"Unauthorized","Admin unauthorized message is not matched");
        Assert.assertEquals(message.getMessage(),"Unauthorized","Admin unauthorized message is not matched through the class");
    }

    @Test
    public void Event_GET_ArrayMessage_Show_InvalidEventUuid_UnprocessableEntity()
    {
        ApiHelpers helper = new ApiHelpers();
        String Uuid = "d6836b73-b475-11eb-a293-06060ef335ea";
       // UUID randomUuid = UUID.randomUUID();

        //Given- When
        Response eventShowResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationBaseURI()).when()
                .get(APIEndPoints.EventSearch+ Uuid);
        ShowInvalidInputResponse validationMessage = eventShowResponse.as(ShowInvalidInputResponse.class);

        //Then
        Assert.assertEquals(eventShowResponse.getStatusCode(),422,"Status code doesn't matched");
        Assert.assertEquals(validationMessage.getEventUuid().get(0),"The event uuid does not exist.","Invalid Uuid validation message is not matched through Array index");
        Assert.assertEquals(validationMessage.getEventUuid().toString(),"[The event uuid does not exist.]","Invalid Uuid validation message is not matched through Array");
    }

    @Test
    public void Event_GET_ResponseWithChildData_Show_Success()
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

        Reporter.log("Step -3: To search Data");
        //Given-When
        Response eventShowResponse = given().spec(helper.getAdminToken()).spec(helper.requestSpecificationBaseURI()).when()
                .get(APIEndPoints.EventSearch+uuid);
        ShowResponse responseShowData = eventShowResponse.as(ShowResponse.class);

        //Then
        Assert.assertEquals(eventShowResponse.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getUuid(),"Uuid is null");
        Assert.assertEquals(responseShowData.getData().getUuid().length(), 36, "Uuid format doesn't matched");
        Assert.assertEquals(responseShowData.getData().getUuid(),uuid,"Uuid doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getName(),"Name is null");
        Assert.assertEquals(responseShowData.getData().getName(),name,"Name doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getEventCode(),"Event code is null");
        Assert.assertTrue(responseShowData.getData().getEventCode().startsWith("ROAMY"),"Event code doesn't follow the format");
        Assert.assertNotNull(responseShowData.getData().getLocationUuid(),"Location uuid is null");
        Assert.assertEquals(responseShowData.getData().getLocationUuid().length(), 36, "Location Uuid format doesn't matched");
        Assert.assertEquals(responseShowData.getData().getLocationUuid(),location,"Location doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getLocationName(),"Location name is null");
        Assert.assertNotNull(responseShowData.getData().getCountryUuid(),"Country uuid is null");
        Assert.assertEquals(responseShowData.getData().getCountryUuid().length(), 36, "Country Uuid format doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getCountryName(),"Country name is null");
        Assert.assertNotNull(responseShowData.getData().getGenreUuid(),"Genre uuid is null");
        Assert.assertEquals(responseShowData.getData().getGenreUuid().length(), 36, "Genre Uuid format doesn't matched");
        Assert.assertEquals(responseShowData.getData().getGenreUuid(),genre,"Genre doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getGenreName(),"Genre name is null");
        Assert.assertNotNull(responseShowData.getData().getPrefix(), "Prefix is null");
        Assert.assertEquals(responseShowData.getData().getPrefix(),prefix,"Prefix doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getDescription(),"Description is null");
        Assert.assertEquals(responseShowData.getData().getDescription(),valueAs100,"Description doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getStartDate(),"Start date is null");
        Assert.assertEquals(responseShowData.getData().getStartDate(),startDate,"Start date doesn't not matched with input");
        Assert.assertNotNull(responseShowData.getData().getStartTime(),"Start time is null");
        Assert.assertEquals(responseShowData.getData().getStartTime(),startTime+":00","Start time doesn't not matched with input");
        Assert.assertNotNull(responseShowData.getData().getEndDate(),"End date is null");
        Assert.assertEquals(responseShowData.getData().getEndDate(),endDate,"End date doesn't not matched with input");
        Assert.assertNotNull(responseShowData.getData().getEndTime(),"End time is null");
        Assert.assertEquals(responseShowData.getData().getEndTime(),endTime+":00","end time doesn't not matched with input");
        Assert.assertNotNull(responseShowData.getData().getPublishAt(),"Published at is null");
        Assert.assertEquals(responseShowData.getData().getPublishAt(),publishAt+":00","Published date doesn't not matched with input");
        Assert.assertNotNull(responseShowData.getData().getSaleStartFrom(),"Sale start from is null");
        Assert.assertEquals(responseShowData.getData().getSaleStartFrom(),saleStartFrom+":00","Sale start from doesn't not matched with input");
        Assert.assertNotNull(responseShowData.getData().getSaleEndAt(),"SaleEnd At is null");
        Assert.assertEquals(responseShowData.getData().getSaleEndAt(),saleEndAt+":00","SaleEnd At doesn't not matched with input");
        Assert.assertNotNull(responseShowData.getData().getThumbnailImage(),"Thumbnail Image is null");
        Assert.assertTrue(responseShowData.getData().getThumbnailImage().startsWith("https://"),"Thumbnail Image is not in url format");
        Assert.assertNotNull(responseShowData.getData().getLineUpImage(),"Line-up Image is null");
        Assert.assertTrue(responseShowData.getData().getLineUpImage().startsWith("https://"),"line-up Image is not in url format");
        Assert.assertNotNull(responseShowData.getData().getUrlSlug(),"UrlSlug is null");
        Assert.assertEquals(responseShowData.getData().getUrlSlug(),valueAs100.toLowerCase(),"UrlSlug doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getSeoTitle(),"SEO Title is null");
        Assert.assertEquals(responseShowData.getData().getSeoTitle(),valueAs100,"SEO Title doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getSeoDescription(),"SEO Description is null");
        Assert.assertEquals(responseShowData.getData().getSeoDescription(),valueAs100,"SEO Description doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getStatusUuid(),"Status uuid is null");
        Assert.assertEquals(responseShowData.getData().getStatusUuid().length(), 36, "Status Uuid format doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getStatusName(),"Status name is null");
        Assert.assertEquals(responseShowData.getData().getStatusName(),"Draft","Status name doesn't matched with expected");
        Assert.assertNull(responseShowData.getData().getRemarks(),"Remarks is not null");
        Assert.assertNotNull(responseShowData.getData().getCapacity(),"Capacity is null");
        Assert.assertEquals(responseShowData.getData().getCapacity().toString(),capacity,"Capacity doesn't matched with input");
        Assert.assertEquals((int) responseShowData.getData().getTicketsSold(), 0, "Tickets sold value is not 0");
        Assert.assertEquals(responseShowData.getData().getSold(), "0.00", "Sold value is not 0");
        Assert.assertNotNull(responseShowData.getData().getCreatedAt(),"Created At is null");
        Assert.assertNotNull(responseShowData.getData().getCreatedBy(),"Created By is null");
        Assert.assertNull(responseShowData.getData().getUpdatedAt(),"Updated At is null");
        Assert.assertNull(responseShowData.getData().getUpdatedBy(),"Updated By is null");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(0).getUuid(),"Flyer Image 0 uuid is null");
        Assert.assertEquals(responseShowData.getData().getFlyerImages().get(0).getUuid().length(), 36, "Flyer Image 0 Uuid format doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(0).getImage(),"Flyer Image 0 Image is null");
        Assert.assertTrue(responseShowData.getData().getFlyerImages().get(0).getImage().startsWith("https://"),"Flyer Image 0 Image is not in url format");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(0).getSortOrder(),"Flyer Image 0 Sort order is null");
        Assert.assertEquals((int) responseShowData.getData().getFlyerImages().get(0).getSortOrder(),1,"Flyer Image 0 Sort order doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(0).getIsCoverImage(),"Flyer Image 0 cover Image is null");
        Assert.assertEquals((int) responseShowData.getData().getFlyerImages().get(0).getIsCoverImage(),1,"Flyer Image 0 Cover Image doesn't matched with input");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(1).getUuid(),"Flyer Image 1 uuid is null");
        Assert.assertEquals(responseShowData.getData().getFlyerImages().get(1).getUuid().length(), 36, "Flyer Image 1 Uuid format doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(1).getImage(),"Flyer Image 1 Image is null");
        Assert.assertTrue(responseShowData.getData().getFlyerImages().get(1).getImage().startsWith("https://"),"Flyer Image 1 Image is not in url format");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(1).getSortOrder(),"Flyer Image 1 Sort order is null");
        Assert.assertEquals((int) responseShowData.getData().getFlyerImages().get(1).getSortOrder(),2,"Flyer Image 1 Sort order doesn't matched with input");
        Assert.assertNull(responseShowData.getData().getFlyerImages().get(1).getIsCoverImage(),"Flyer Image 1 cover Image is not null");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(2).getUuid(),"Flyer Image 2 uuid is null");
        Assert.assertEquals(responseShowData.getData().getFlyerImages().get(2).getUuid().length(), 36, "Flyer Image 1 Uuid format doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(2).getImage(),"Flyer Image 2 Image is null");
        Assert.assertTrue(responseShowData.getData().getFlyerImages().get(2).getImage().startsWith("https://"),"Flyer Image 2 Image is not in url format");
        Assert.assertNotNull(responseShowData.getData().getFlyerImages().get(2).getSortOrder(),"Flyer Image 2 Sort order is null");
        Assert.assertEquals((int) responseShowData.getData().getFlyerImages().get(2).getSortOrder(),3,"Flyer Image 2 Sort order doesn't matched with input");
        Assert.assertNull(responseShowData.getData().getFlyerImages().get(2).getIsCoverImage(),"Flyer Image 2 cover Image is not null");
        Assert.assertNotNull(responseShowData.getData().getArtists().get(0).getName(),"Artist name is null");
        Assert.assertNotNull(responseShowData.getData().getArtists().get(0).getUuid(),"Artist uuid 0 is null");
        Assert.assertEquals(responseShowData.getData().getArtists().get(0).getUuid().length(), 36, "Artist 0 Uuid format doesn't matched");
        Assert.assertNotNull(responseShowData.getData().getArtists().get(0).getArtistImage(),"Artist Image 0 Image is null");
        Assert.assertTrue(responseShowData.getData().getArtists().get(0).getArtistImage().startsWith("https://"),"Artist Image 0 Image is not in url format");
        Assert.assertNotNull(responseShowData.getData().getInclusions().get(0).getName(),"Inclusions name 0 is null");
        Assert.assertNotNull(responseShowData.getData().getInclusions().get(0).getUuid(),"Inclusions uuid 0 is null");
        Assert.assertEquals(responseShowData.getData().getInclusions().get(0).getUuid().length(), 36, "Inclusions 0 Uuid format doesn't matched");
        Assert.assertNotNull(Arrays.toString(responseShowData.getData().getItineraries().getAdditionalProperties().values().toArray()));
       // System.out.println(Arrays.toString(responseShowData.getData().getItineraries().getAdditionalProperties().values().toArray()));
    }

    @Test (invocationCount = 2)
    public void Booking_GET_QueryParamRequest_Bookings_Success()
    {
        ApiHelpers helper = new ApiHelpers();

        //Given-When
        Response responseList = given().spec(helper.getCustomerToken()).spec(helper.requestSpecificationWithoutAuth()).
                queryParam("type","past").
                when().get(APIEndPoints.Bookings);
        Bookings bookingResponse = responseList.as(Bookings.class);

        //Then
        Assert.assertEquals(responseList.getStatusCode(),200,"Status code doesn't matched");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getCoverImage()!=null && el.getCoverImage().trim().isEmpty()),"Cover Image is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getBookingId()!=null && el.getBookingId().trim().isEmpty()),"Booking ID is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getBookingUuid()!=null && el.getBookingUuid().trim().isEmpty()),"Booking Uuid is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getEventUuid()!=null && el.getEventUuid().trim().isEmpty()),"Event Uuid is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getEventName()!=null && el.getEventName().trim().isEmpty()),"Event Name is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getDescription()!=null && el.getDescription().trim().isEmpty()),"Description is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getEventStatus()!=null && el.getEventStatus().trim().isEmpty()),"Event Status is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getStartDate()!=null && el.getStartDate().trim().isEmpty()),"Start Date is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getEndDate()!=null && el.getEndDate().trim().isEmpty()),"End date is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getLocationName()!=null && el.getLocationName().trim().isEmpty()),"Location is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getCountryName()!=null && el.getCountryName().trim().isEmpty()),"Country is null");
        Assert.assertTrue(bookingResponse.getData().stream().noneMatch(el->el.getBookedAt()!=null && el.getBookedAt().trim().isEmpty()),"Booking Date is null");

    }

}
