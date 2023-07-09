package dataobjects.roamy.admin.event.list;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "logic",
        "filters"
})
public class Filter {

    @JsonProperty("logic")
    private String logic;
    @JsonProperty("filters")
    private List<Filter__1> filters = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("logic")
    public String getLogic() {
        return logic;
    }

    @JsonProperty("logic")
    public void setLogic(String logic) {
        this.logic = logic;
    }

    @JsonProperty("filters")
    public List<Filter__1> getFilters() {
        return filters;
    }

    @JsonProperty("filters")
    public void setFilters(List<Filter__1> filters) {
        this.filters = filters;
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