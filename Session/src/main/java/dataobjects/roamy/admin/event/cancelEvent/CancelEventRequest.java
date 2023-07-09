package dataobjects.roamy.admin.event.cancelEvent;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "event_uuid",
        "remarks"
})
public class CancelEventRequest {

    @JsonProperty("event_uuid")
    private String eventUuid;
    @JsonProperty("remarks")
    private String remarks;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("event_uuid")
    public String getEventUuid() {
        return eventUuid;
    }

    @JsonProperty("event_uuid")
    public void setEventUuid(String eventUuid) {
        this.eventUuid = eventUuid;
    }

    @JsonProperty("remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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