package API;

import Util.BaseClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import serialization.CartResponse;

import java.util.List;

public class FirstAPI extends BaseClass {


    @Test (groups = {"regression"})
    //Get all products
    public void firstGet(){
        Response resp = httpRequest.get("/products");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.prettyPrint());
    }

    @Test
    //Get a single product
    public void secondGet(){
        Response resp = httpRequest.pathParam("id", 2).get("/products/{id}");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.prettyPrint());
    }

    @Test
    //Limit results
    public void limitResults(){
        Response resp = httpRequest.get("/products?limit=5");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.prettyPrint());
    }

    @Test
    //Sort results
    public void sortResults(){
        Response resp = httpRequest.get("/products?sort=desc");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.prettyPrint());
    }

    @Test
    //Get all categories
    public void getCategories(){
        Response resp = httpRequest.get("/products/categories");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.prettyPrint());
    }


    @Test
    //Get all products in one category
    public void getJeweleryProducts(){
        Response resp = httpRequest.pathParam("category", "jewelery").get("/products/category/{category}");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.prettyPrint());
    }


    @Test
    public void firstPost(){
        String newJson = "{\n" +
                "   \"title\":\"test product\",\n" +
                "   \"price\":13.5,\n" +
                "   \"description\":\"lorem ipsum set\",\n" +
                "   \"image\":\"https://i.pravatar.cc\",\n" +
                "   \"category\":\"electronic\"\n" +
                "}";
        Response resp = httpRequest.body(newJson).contentType(ContentType.JSON).post("/products");
        System.out.println(resp.prettyPrint());
        System.out.println(resp.getStatusCode());
    }

    @Test
    public void firstPut(){
     //   RestAssured.baseURI = "https://fakestoreapi.com";
        String newJson = "{\n" +
                "   \"title\":\"test product\",\n" +
                "   \"price\":13.5,\n" +
                "   \"description\":\"lorem ipsum set\",\n" +
                "   \"image\":\"https://i.pravatar.cc\",\n" +
                "   \"category\":\"electronic\"\n" +
                "}";
        Response resp = httpRequest.pathParam("id", 2).body(newJson).contentType(ContentType.JSON).put("/products/{id}");
        System.out.println(resp.prettyPrint());
        System.out.println(resp.getStatusCode());
    }

    @Test
    public void firstDelete() {
      //  RestAssured.baseURI = "https://fakestoreapi.com";
        Response resp = httpRequest.pathParam("id", 6).delete("/products/{id}");
        System.out.println(resp.prettyPrint());
        System.out.println(resp.getStatusCode());

    }

    @Test
    public void getCartById(){
        Response resp = httpRequest.pathParam("id", 1).get("/carts/{id}");
        List listaProductos = resp.jsonPath().getList("products");
        listaProductos.forEach( product -> System.out.println(product) );
    }

    @Test
    public void getCartByidSerialization() throws JsonProcessingException {
        Response resp = httpRequest.pathParam("id", 5).get("/carts/{id}");
        ObjectMapper ObjectMapper = new ObjectMapper();
        CartResponse response = ObjectMapper.readValue(resp.getBody().asString(), CartResponse.class);
        System.out.println(response.getProducts());

        //        ObjectMapper ObjectMapper = new ObjectMapper();
        //        ProductsResponse response = ObjectMapper.readValue(resp.getBody().asString(), ProductsResponse.class);
        //
        //        System.out.println(response.getTitle());
        //        Assert.assertEquals(response.getTitle(), "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
    }


}
