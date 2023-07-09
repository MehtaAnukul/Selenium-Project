package dataobjectsapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "email",
        "first_name",
        "last_name",
        "avatar"
})
public class ChildDataObjectOfRegisterUser
{
        @JsonProperty("id")
        private Integer id;

        @JsonProperty("email")
        private String email;

        @JsonProperty("first_name")
        private String first_name;

        @JsonProperty("last_name")
        private String last_name;

        @JsonProperty("avatar")
        private String avatar;

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("email")
        public String getEmail() {
            return email;
        }

        @JsonProperty("email")
        public void setEmail(String email) {
            this.email = email;
        }

        @JsonProperty("first_name")
        public String getFirstName() {
            return first_name;
        }

        @JsonProperty("first_name")
        public void setFirstName(String first_name) {
            this.first_name = first_name;
        }

        @JsonProperty("last_name")
        public String getLastName() {
            return last_name;
        }

        @JsonProperty("last_name")
        public void setLastName(String last_name) {
            this.last_name = last_name;
        }

        @JsonProperty("avatar")
        public String getAvatar() {
            return avatar;
        }

        @JsonProperty("avatar")
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
}
