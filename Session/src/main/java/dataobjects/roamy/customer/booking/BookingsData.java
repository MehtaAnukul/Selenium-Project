package dataobjects.roamy.customer.booking;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cover_image",
        "booking_uuid",
        "booking_id",
        "event_uuid",
        "event_name",
        "description",
        "event_status",
        "previous_start_date",
        "previous_start_time",
        "previous_end_date",
        "previous_end_time",
        "start_date",
        "start_time",
        "end_date",
        "end_time",
        "remarks",
        "location_name",
        "country_name",
        "cancelled_at",
        "booked_at"
})
public class BookingsData {

    @JsonProperty("cover_image")
    private String coverImage;
    @JsonProperty("booking_uuid")
    private String bookingUuid;
    @JsonProperty("booking_id")
    private String bookingId;
    @JsonProperty("event_uuid")
    private String eventUuid;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("event_status")
    private String eventStatus;
    @JsonProperty("previous_start_date")
    private Object previousStartDate;
    @JsonProperty("previous_start_time")
    private Object previousStartTime;
    @JsonProperty("previous_end_date")
    private Object previousEndDate;
    @JsonProperty("previous_end_time")
    private Object previousEndTime;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("start_time")
    private Object startTime;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("end_time")
    private Object endTime;
    @JsonProperty("remarks")
    private Object remarks;
    @JsonProperty("location_name")
    private String locationName;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("cancelled_at")
    private Object cancelledAt;
    @JsonProperty("booked_at")
    private String bookedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cover_image")
    public String getCoverImage() {
        return coverImage;
    }

    @JsonProperty("cover_image")
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @JsonProperty("booking_uuid")
    public String getBookingUuid() {
        return bookingUuid;
    }

    @JsonProperty("booking_uuid")
    public void setBookingUuid(String bookingUuid) {
        this.bookingUuid = bookingUuid;
    }

    @JsonProperty("booking_id")
    public String getBookingId() {
        return bookingId;
    }

    @JsonProperty("booking_id")
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    @JsonProperty("event_uuid")
    public String getEventUuid() {
        return eventUuid;
    }

    @JsonProperty("event_uuid")
    public void setEventUuid(String eventUuid) {
        this.eventUuid = eventUuid;
    }

    @JsonProperty("event_name")
    public String getEventName() {
        return eventName;
    }

    @JsonProperty("event_name")
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("event_status")
    public String getEventStatus() {
        return eventStatus;
    }

    @JsonProperty("event_status")
    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @JsonProperty("previous_start_date")
    public Object getPreviousStartDate() {
        return previousStartDate;
    }

    @JsonProperty("previous_start_date")
    public void setPreviousStartDate(Object previousStartDate) {
        this.previousStartDate = previousStartDate;
    }

    @JsonProperty("previous_start_time")
    public Object getPreviousStartTime() {
        return previousStartTime;
    }

    @JsonProperty("previous_start_time")
    public void setPreviousStartTime(Object previousStartTime) {
        this.previousStartTime = previousStartTime;
    }

    @JsonProperty("previous_end_date")
    public Object getPreviousEndDate() {
        return previousEndDate;
    }

    @JsonProperty("previous_end_date")
    public void setPreviousEndDate(Object previousEndDate) {
        this.previousEndDate = previousEndDate;
    }

    @JsonProperty("previous_end_time")
    public Object getPreviousEndTime() {
        return previousEndTime;
    }

    @JsonProperty("previous_end_time")
    public void setPreviousEndTime(Object previousEndTime) {
        this.previousEndTime = previousEndTime;
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

    @JsonProperty("remarks")
    public Object getRemarks() {
        return remarks;
    }

    @JsonProperty("remarks")
    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("location_name")
    public String getLocationName() {
        return locationName;
    }

    @JsonProperty("location_name")
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @JsonProperty("country_name")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("country_name")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("cancelled_at")
    public Object getCancelledAt() {
        return cancelledAt;
    }

    @JsonProperty("cancelled_at")
    public void setCancelledAt(Object cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    @JsonProperty("booked_at")
    public String getBookedAt() {
        return bookedAt;
    }

    @JsonProperty("booked_at")
    public void setBookedAt(String bookedAt) {
        this.bookedAt = bookedAt;
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