package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetSequence {

    @Test
    public void createPet(){

        Assert.assertEquals(getPet(1732).statusCode(),404);
        String newJSON = "{\n" +
                "\"id\": 1732,\n" +
                "\"name\": \"ricardo\",\n" +
                "\"category\": {\n" +
                "\"id\": 1,\n" +
                "\"name\": \"Dogs\"\n" +
                "},\n" +
                "\"photoUrls\": [\n" +
                "\"string\"\n" +
                "],\n" +
                "\"tags\": [\n" +
                "{\n" +
                "\"id\": 0,\n" +
                "\"name\": \"string\"\n" +
                "}\n" +
                "],\n" +
                "\"status\": \"available\"\n" +
                "}";
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        Response response = RestAssured.given().body(newJSON).contentType(ContentType.JSON).post("/pet/");
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getStatusCode());

        Response responseGetPet = getPet(1732);
        Assert.assertEquals(responseGetPet.getStatusCode(),200);
        System.out.println(responseGetPet.jsonPath().getString("name"));

        Response responseUpdatePet = renamePet(1732, "ricardito");
        Assert.assertEquals(responseUpdatePet.getStatusCode(), 200);
        System.out.println(responseUpdatePet.jsonPath().getString("name"));

        Response responseDeletePet = deletePet(1732);
        System.out.println(responseDeletePet.prettyPrint());

        Response responseFinalGet = getPet(1732);
        Assert.assertEquals(responseFinalGet.getStatusCode(), 404);
        System.out.println(responseFinalGet.getStatusCode());
    }

    public Response getPet(int id){
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        Response response = RestAssured.given().pathParam("petId", id).get("/pet/{petId}");
        return response;
    }

    public Response renamePet(int id, String newName){
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        Response response = RestAssured.given().pathParam("petId", id).queryParam("name", newName).post("/pet/{petId}");
        return response;
    }

    public Response deletePet(int id){
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        Response response = RestAssured.given().pathParam("petId", id).delete("/pet/{petId}");
        return response;
    }
}
