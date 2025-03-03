package scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.responseItem;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestE2ETest {

    @Test
    public void scenarioE2E() {
        String json = "{\n" +
                "   \"name\": \"Apple MacBook Pro Rosa\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";

        // Add Product
        RestAssured.baseURI = "https://api.restful-api.dev";
        Response response = given()
                                .contentType("application/json")
                                .log().all()
                                .pathParam("path", "objects")
                                .body(json)
                            .when()
                                .post("/{path}");

        System.out.println(response.getBody().asString());

        // Deserialize response directly
        responseItem responseItem = response.as(responseItem.class);

        // Assertions for POST response
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseItem.name, "Apple MacBook Pro Rosa");
        Assert.assertEquals(responseItem.dataItem.year, 2019);
        Assert.assertEquals(responseItem.dataItem.price, 1849);
        Assert.assertEquals(responseItem.dataItem.cpuModel, "Intel Core i9");
        Assert.assertEquals(responseItem.dataItem.hardDiskSize, "1 TB");

        String id = responseItem.id;

        // Get Product
        Response response2 = given()
                                .log().all()
                                .pathParam("id", id)
                            .when()
                                .get("/objects/{id}");

        System.out.println("response2: " + response2.asPrettyString());

        // Deserialize GET response
        responseItem responseItem2 = response2.as(responseItem.class);

        // Assertions for GET response
        Assert.assertEquals(response2.statusCode(), 200);
        Assert.assertEquals(responseItem2.name, responseItem.name);
        Assert.assertEquals(responseItem2.dataItem.year, responseItem.dataItem.year);
        Assert.assertEquals(responseItem2.dataItem.price, responseItem.dataItem.price);
        Assert.assertEquals(responseItem2.dataItem.cpuModel, responseItem.dataItem.cpuModel);
        Assert.assertEquals(responseItem2.dataItem.hardDiskSize, responseItem.dataItem.hardDiskSize);

        // Delete Product
        Response response3 = given()
                                .contentType("application/json")
                                .log().all()
                                .pathParam("id", id)
                            .when()
                                .delete("/objects/{id}");

        System.out.println("response3: " + response3.asPrettyString());
        Assert.assertEquals(response3.statusCode(), 200);

        // Get Product after DELETE
        Response response4 = given()
                                .log().all()
                                .pathParam("id", id)
                            .when()
                                .get("/objects/{id}");

        System.out.println("response4: " + response4.asPrettyString());

        // Assertion should be on response4, not response2
        Assert.assertEquals(response4.statusCode(), 404);
    }
}
