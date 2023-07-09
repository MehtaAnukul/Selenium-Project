package dataobjects.roamy.admin.event.show;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "2021-10-02"

})

public class Itineraries {

    @JsonProperty("2021-10-02")
    private List<String> _20211002 = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("2021-10-02")
    public List<String> get20211002() {
        return _20211002;
    }

    @JsonProperty("2021-10-02")
    public void set20211002(List<String> _20211002) {
        this._20211002 = _20211002;
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

