package Util;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

    protected RequestSpecification httpRequest;

    @BeforeMethod (groups = {"regression"})
    public void setUp(){

        RestAssured.baseURI = "https://fakestoreapi.com";
        httpRequest = RestAssured.given();

    }

}
