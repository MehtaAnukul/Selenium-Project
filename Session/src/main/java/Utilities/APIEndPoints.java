package Utilities;

public class APIEndPoints
{
    //Base-URI
    public static final String BaseUri = "https://apidev.roamy.earth";

    //Options
    public static final String Artists = "/option/artists";
    public static final String Locations = "/option/locations";
    public static final String Genres = "/option/genres";
    public static final String Inclusions = "/option/inclusions";

    //Admin Auth
    public static final String AdminClientToken = "/oauth/token";
    public static final String AdminLogin = "/admin/login";

    //Event
    public static final String EventSearch = "/event/";
    public static final String EventAdd = "/event";
    public static final String EventList = "/events";
    public static final String EventDelete = "/event/";
    public static final String EventCancel = "/cancel-event";

    //CustomerWithOutLogin
    public static final String CustomerLogin = "/customer/login";

    //Bookings
    public static final String Bookings = "/customer/bookings";

    //Country
    public static final String CountryUpdate = "/country/";
    public static final String CountryAdd = "/country";
    public static final String CountryList = "/countries";

    //CustomerWithLogin
    public static final String EditEmail = "/customer/edit-email";
}
