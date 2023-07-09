package dataobjects.roamy.admin.country.update;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency_uuid",
        "dialing_code",
        "name"
})

public class UpdateCountryRequest {

    @JsonProperty("currency_uuid")
    private String currencyUuid;
    @JsonProperty("dialing_code")
    private List<String> dialingCode = null;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("currency_uuid")
    public String getCurrencyUuid() {
        return currencyUuid;
    }

    @JsonProperty("currency_uuid")
    public void setCurrencyUuid(String currencyUuid) {
        this.currencyUuid = currencyUuid;
    }

    @JsonProperty("dialing_code")
    public List<String> getDialingCode() {
        return dialingCode;
    }

    @JsonProperty("dialing_code")
    public void setDialingCode(List<String> dialingCode) {
        this.dialingCode = dialingCode;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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