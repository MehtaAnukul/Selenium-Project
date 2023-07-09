package dataobjectsapi;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "company",
        "url",
        "text"
})

public class ChildAdObjectOfRegisterUser
{
    @JsonProperty("company")
    private String company;
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("company")
        public String getCompany() {
            return company;
        }

        @JsonProperty("company")
        public void setCompany(String company) {
            this.company = company;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }
        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("text")
        public String getText() {
            return text;
        }

        @JsonProperty("text")
        public void setText(String text) {
            this.text = text;
        }

}
