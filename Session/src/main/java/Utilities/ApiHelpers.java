package Utilities;

import dataobjects.roamy.admin.adminauth.login.AdminLoginRequest;
import dataobjects.roamy.customer.customerWithoutLogin.login.LoginRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelpers
{
    public RequestSpecification requestSpecificationBaseURI()
    {
        return new RequestSpecBuilder().setBaseUri(APIEndPoints.BaseUri).build();
    }

    public RequestSpecification requestSpecificationWithoutAuth()
    {
        return new RequestSpecBuilder().
                setBaseUri(APIEndPoints.BaseUri).
                addHeader("Content-Type", "application/json").
                build();
    }

    public RequestSpecification requestSpecificationWithoutAuthMultiPart()
    {
        return new RequestSpecBuilder().
                setBaseUri(APIEndPoints.BaseUri).
                addHeader("Content-Type", "multipart/form-data").
                build();
    }

    public RequestSpecification getClientToken()
    {

        Response response = given().spec(requestSpecificationWithoutAuthMultiPart()).
                multiPart("grant_type","client_credentials").
                multiPart("client_id",Constants.client_id).
                multiPart("client_secret",Constants.client_secret).
                when()
                .post(APIEndPoints.AdminClientToken).then().extract().response();

        String accessToken =response.path("access_token");

        return new RequestSpecBuilder().
                addRequestSpecification(requestSpecificationWithoutAuth()).
                addHeader("Authorization", "Bearer " + accessToken).
                build();
    }

    public RequestSpecification getAdminToken()
    {

        AdminLoginRequest requestData = new AdminLoginRequest();
        requestData.setEmail(Constants.AdminEmail);
        requestData.setPassword(Constants.AdminPassword);

        //When

        Response response = given().spec(getClientToken()).body(requestData).when()
                .post(APIEndPoints.AdminLogin).then().extract().response();

        String accessToken =response.path("access_token");

        return new RequestSpecBuilder().
                addHeader("Authorization", "Bearer " + accessToken).
                build();
    }

    public RequestSpecification getCustomerToken()
    {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(Constants.CustomerEmail);
        loginRequest.setPassword(Constants.CustomerPassword);

        Response response =
                given().
                        spec(requestSpecificationWithoutAuth()).
                        body(loginRequest).
                        when().
                        post(APIEndPoints.CustomerLogin).
                        then().
                        extract().response();

        String accessToken =response.path("access_token");

        return new RequestSpecBuilder().
                addHeader("Authorization", "Bearer " + accessToken).
                build();
    }
}
