package dataobjects.roamy.admin.event.show;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uuid",
        "name",
        "event_code",
        "location_uuid",
        "location_name",
        "country_uuid",
        "country_name",
        "genre_uuid",
        "genre_name",
        "prefix",
        "description",
        "start_date",
        "start_time",
        "end_date",
        "end_time",
        "publish_at",
        "sale_start_from",
        "sale_end_at",
        "thumbnail_image",
        "line_up_image",
        "url_slug",
        "seo_title",
        "seo_description",
        "status_uuid",
        "status_name",
        "remarks",
        "capacity",
        "tickets_sold",
        "sold",
        "created_by",
        "created_at",
        "updated_by",
        "updated_at",
        "flyer_images",
        "artists",
        "itineraries",
        "inclusions"
})
public class ShowResponseData {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("event_code")
    private String eventCode;
    @JsonProperty("location_uuid")
    private String locationUuid;
    @JsonProperty("location_name")
    private String locationName;
    @JsonProperty("country_uuid")
    private String countryUuid;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("genre_uuid")
    private String genreUuid;
    @JsonProperty("genre_name")
    private String genreName;
    @JsonProperty("prefix")
    private String prefix;
    @JsonProperty("description")
    private String description;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("start_time")
    private Object startTime;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("end_time")
    private Object endTime;
    @JsonProperty("publish_at")
    private String publishAt;
    @JsonProperty("sale_start_from")
    private String saleStartFrom;
    @JsonProperty("sale_end_at")
    private String saleEndAt;
    @JsonProperty("thumbnail_image")
    private String thumbnailImage;
    @JsonProperty("line_up_image")
    private String lineUpImage;
    @JsonProperty("url_slug")
    private String urlSlug;
    @JsonProperty("seo_title")
    private String seoTitle;
    @JsonProperty("seo_description")
    private String seoDescription;
    @JsonProperty("status_uuid")
    private String statusUuid;
    @JsonProperty("status_name")
    private String statusName;
    @JsonProperty("remarks")
    private Object remarks;
    @JsonProperty("capacity")
    private Integer capacity;
    @JsonProperty("tickets_sold")
    private Integer ticketsSold;
    @JsonProperty("sold")
    private String sold;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_by")
    private String updatedBy;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("flyer_images")
    private List<FlyerImage> flyerImages = null;
    @JsonProperty("artists")
    private List<Artist> artists = null;
    @JsonProperty("itineraries")
    private Itineraries itineraries;
    @JsonProperty("inclusions")
    private List<Inclusion> inclusions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("event_code")
    public String getEventCode() {
        return eventCode;
    }

    @JsonProperty("event_code")
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @JsonProperty("location_uuid")
    public String getLocationUuid() {
        return locationUuid;
    }

    @JsonProperty("location_uuid")
    public void setLocationUuid(String locationUuid) {
        this.locationUuid = locationUuid;
    }

    @JsonProperty("location_name")
    public String getLocationName() {
        return locationName;
    }

    @JsonProperty("location_name")
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @JsonProperty("country_uuid")
    public String getCountryUuid() {
        return countryUuid;
    }

    @JsonProperty("country_uuid")
    public void setCountryUuid(String countryUuid) {
        this.countryUuid = countryUuid;
    }

    @JsonProperty("country_name")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("country_name")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("genre_uuid")
    public String getGenreUuid() {
        return genreUuid;
    }

    @JsonProperty("genre_uuid")
    public void setGenreUuid(String genreUuid) {
        this.genreUuid = genreUuid;
    }

    @JsonProperty("genre_name")
    public String getGenreName() {
        return genreName;
    }

    @JsonProperty("genre_name")
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @JsonProperty("prefix")
    public String getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("start_time")
    public Object getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("end_date")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("end_time")
    public Object getEndTime() {
        return endTime;
    }

    @JsonProperty("end_time")
    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("publish_at")
    public String getPublishAt() {
        return publishAt;
    }

    @JsonProperty("publish_at")
    public void setPublishAt(String publishAt) {
        this.publishAt = publishAt;
    }

    @JsonProperty("sale_start_from")
    public String getSaleStartFrom() {
        return saleStartFrom;
    }

    @JsonProperty("sale_start_from")
    public void setSaleStartFrom(String saleStartFrom) {
        this.saleStartFrom = saleStartFrom;
    }

    @JsonProperty("sale_end_at")
    public String getSaleEndAt() {
        return saleEndAt;
    }

    @JsonProperty("sale_end_at")
    public void setSaleEndAt(String saleEndAt) {
        this.saleEndAt = saleEndAt;
    }

    @JsonProperty("thumbnail_image")
    public String getThumbnailImage() {
        return thumbnailImage;
    }

    @JsonProperty("thumbnail_image")
    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    @JsonProperty("line_up_image")
    public String getLineUpImage() {
        return lineUpImage;
    }

    @JsonProperty("line_up_image")
    public void setLineUpImage(String lineUpImage) {
        this.lineUpImage = lineUpImage;
    }

    @JsonProperty("url_slug")
    public String getUrlSlug() {
        return urlSlug;
    }

    @JsonProperty("url_slug")
    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    @JsonProperty("seo_title")
    public String getSeoTitle() {
        return seoTitle;
    }

    @JsonProperty("seo_title")
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    @JsonProperty("seo_description")
    public String getSeoDescription() {
        return seoDescription;
    }

    @JsonProperty("seo_description")
    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    @JsonProperty("status_uuid")
    public String getStatusUuid() {
        return statusUuid;
    }

    @JsonProperty("status_uuid")
    public void setStatusUuid(String statusUuid) {
        this.statusUuid = statusUuid;
    }

    @JsonProperty("status_name")
    public String getStatusName() {
        return statusName;
    }

    @JsonProperty("status_name")
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @JsonProperty("remarks")
    public Object getRemarks() {
        return remarks;
    }

    @JsonProperty("remarks")
    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("capacity")
    public Integer getCapacity() {
        return capacity;
    }

    @JsonProperty("capacity")
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @JsonProperty("tickets_sold")
    public Integer getTicketsSold() {
        return ticketsSold;
    }

    @JsonProperty("tickets_sold")
    public void setTicketsSold(Integer ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    @JsonProperty("sold")
    public String getSold() {
        return sold;
    }

    @JsonProperty("sold")
    public void setSold(String sold) {
        this.sold = sold;
    }

    @JsonProperty("created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("created_by")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonProperty("updated_by")
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("flyer_images")
    public List<FlyerImage> getFlyerImages() {
        return flyerImages;
    }

    @JsonProperty("flyer_images")
    public void setFlyerImages(List<FlyerImage> flyerImages) {
        this.flyerImages = flyerImages;
    }

    @JsonProperty("artists")
    public List<Artist> getArtists() {
        return artists;
    }

    @JsonProperty("artists")
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @JsonProperty("itineraries")
    public Itineraries getItineraries() {
        return itineraries;
    }

    @JsonProperty("itineraries")
    public void setItineraries(Itineraries itineraries) {
        this.itineraries = itineraries;
    }

    @JsonProperty("inclusions")
    public List<Inclusion> getInclusions() {
        return inclusions;
    }

    @JsonProperty("inclusions")
    public void setInclusions(List<Inclusion> inclusions) {
        this.inclusions = inclusions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}