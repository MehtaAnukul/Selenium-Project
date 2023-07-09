package dataobjects.roamy.admin.event.list;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "take",
        "skip",
        "sort",
        "filter",
        "search"
})
public class ListRequest {

    @JsonProperty("take")
    private String take;
    @JsonProperty("skip")
    private String skip;
    @JsonProperty("sort")
    private List<Sort> sort = null;
    @JsonProperty("filter")
    private Filter filter;
    @JsonProperty("search")
    private Search search;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("take")
    public String getTake() {
        return take;
    }

    @JsonProperty("take")
    public void setTake(String take) {
        this.take = take;
    }

    @JsonProperty("skip")
    public String getSkip() {
        return skip;
    }

    @JsonProperty("skip")
    public void setSkip(String skip) {
        this.skip = skip;
    }

    @JsonProperty("sort")
    public List<Sort> getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(List<Sort> sort) {
        this.sort = sort;
    }

    @JsonProperty("filter")
    public Filter getFilter() {
        return filter;
    }

    @JsonProperty("filter")
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @JsonProperty("search")
    public Search getSearch() {
        return search;
    }

    @JsonProperty("search")
    public void setSearch(Search search) {
        this.search = search;
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