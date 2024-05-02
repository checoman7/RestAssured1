package API;

import Util.BaseClassPets;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import serialization.Category;
import serialization.CreatePet;

public class PetApi extends BaseClassPets {

    @Test
    public void getPetById(){
        //RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        Response response = httpRequest.pathParam("petId", 1732).get("/pet/{petId}");
        System.out.println(response.prettyPrint());
    }

    @Test
    public void putPetById(){
        //RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        String newJSON = "{\n" +
                "\"id\": 1732,\n" +
                "\"name\": \"ricardito\",\n" +
                "  \"category\": {\n" +
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
        Response response = httpRequest.body(newJSON).contentType(ContentType.JSON).put("/pet/");
        System.out.println(response.prettyPrint());
    }

    @Test
    public void postPetbyId(){
        //RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        String newJson = "{\n" +
                "\"id\": 1732,\n" +
                "\"name\": \"ronald\",\n" +
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
        Response response = httpRequest.body(newJson).contentType(ContentType.JSON).post("/pet/");
        System.out.println(response.prettyPrint());
    }

    @Test
    public void deletePetById(){
        //RestAssured.baseURI = "https://petstore3.swagger.io/api/v3/";
        Response response = httpRequest.pathParam("petId", 1732).delete("/pet/{petId}");
        System.out.println(response.prettyPrint());
    }

    @Test
    public void getPetByIdSerial() throws JsonProcessingException {
        Response response = httpRequest.pathParam("petId", 1732).get("/pet/{petId}");
        System.out.println(response.prettyPrint());
        ObjectMapper objectMapper = new ObjectMapper();
        CreatePet x = objectMapper.readValue(response.getBody().asString(), CreatePet.class);
        System.out.println(x.getName());
    }

/*    public void postPetByIdSerial(){
        CreatePet pet = new CreatePet(1733, "ronaldito", Category() , "url1", Tag() , "available");

    }*/

    @Test
    public void postPetByPathParam(){
        Response response = httpRequest.pathParam("petId", 1732).queryParam("name", "hunter").queryParam("status", "unavailable").post("/pet/{petId}");
        System.out.println(response.prettyPrint());
    }

}
