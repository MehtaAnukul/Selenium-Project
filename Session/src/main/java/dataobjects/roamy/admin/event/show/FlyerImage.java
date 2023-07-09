package dataobjects.roamy.admin.event.show;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uuid",
        "image",
        "sort_order",
        "is_cover_image"
})
public class FlyerImage {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("image")
    private String image;
    @JsonProperty("sort_order")
    private Integer sortOrder;
    @JsonProperty("is_cover_image")
    private Object isCoverImage;
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

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("sort_order")
    public Integer getSortOrder() {
        return sortOrder;
    }

    @JsonProperty("sort_order")
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @JsonProperty("is_cover_image")
    public Object getIsCoverImage() {
        return isCoverImage;
    }

    @JsonProperty("is_cover_image")
    public void setIsCoverImage(Object isCoverImage) {
        this.isCoverImage = isCoverImage;
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