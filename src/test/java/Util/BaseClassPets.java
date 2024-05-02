package Util;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseClassPets {

    protected RequestSpecification httpRequest;

    @BeforeMethod ()
    public void setUpPet(){
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        httpRequest = RestAssured.given();
    }
}
