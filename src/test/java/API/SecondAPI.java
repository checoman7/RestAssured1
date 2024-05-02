package API;

import Util.BaseClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import serialization.CreateProduct;
import serialization.ProductsResponse;

import java.util.List;

public class SecondAPI extends BaseClass {

    @Test
    public void firstGet() {
        Response resp = httpRequest.get("/products");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.prettyPrint());

    }

    @Test
    public void getProductsById() throws JsonProcessingException {
        Response resp = httpRequest.pathParam("id", 2).get("/products/{id}");
        // System.out.println(resp.prettyPrint());
        ObjectMapper ObjectMapper = new ObjectMapper();
        ProductsResponse response = ObjectMapper.readValue(resp.getBody().asString(), ProductsResponse.class);

        System.out.println(response.getTitle());
        Assert.assertEquals(response.getTitle(), "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
    }

    @Test
    public void firstPost() throws JsonProcessingException {
        CreateProduct product = new CreateProduct("Chaqueta Magica", 10.0, "Las mejores chaquetas di", "men's clothes", "test");
        ObjectMapper objectMapper = new ObjectMapper();
        String newJson = objectMapper.writeValueAsString(product);
        System.out.println(newJson);
        Response resp = httpRequest.body(newJson).contentType(ContentType.JSON).post("/products");
        System.out.println(resp.prettyPrint());
        //System.out.println(resp.getStatusCode());
    }

}