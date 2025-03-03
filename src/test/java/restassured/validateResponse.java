package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.responseItem;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class validateResponse {

    responseItem responseItem = new responseItem();
    String json = "{\n" + //
                            "   \"name\": \"Apple MacBook Pro Rosa\",\n" + //
                            "   \"data\": {\n" + //
                            "      \"year\": 2019,\n" + //
                            "      \"price\": 1849,\n" + //
                            "      \"CPU model\": \"Intel Core i9\",\n" + //
                            "      \"Hard disk size\": \"1 TB\"\n" + //
                            "   }\n" + //
                            "}";
        
        @Test
        public void addProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        Response response = RestAssured.given()
                                        .contentType("application/json")
                                        .log().all()
                                        .pathParam("path", "objects")
                                        .body(json)
                                        .when()
                                        .post("/{path}");
        System.out.println(response.getBody().asString()); 
    
        JsonPath jsonPath = response.jsonPath();
            // //Assert.assertEquals(jsonPath.get("name"), "Apple MacBook Pro Rosa");
            
            // String name = jsonPath.get("name");
            // int year = jsonPath.get("data.year");
            // int price = jsonPath.get("data.price");
            // String cpuModel = jsonPath.get("data.'CPU model'");
            // String hardDiskSize = jsonPath.get("data.'Hard disk size'");
            
            // Assert.assertEquals(response.statusCode(), 200);
            // Assert.assertEquals(name, "Apple MacBook Pro Rosa");
            // Assert.assertEquals(year, 2019);
            // Assert.assertEquals(price, 1849);
            // Assert.assertEquals(cpuModel, "Intel Core i9");
            // Assert.assertEquals(hardDiskSize, "1 TB");
    
            responseItem = jsonPath.getObject("", responseItem.class);
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(responseItem.name, "Apple MacBook Pro Rosa");
            Assert.assertEquals(responseItem.dataItem.year, 2019);
            Assert.assertEquals(responseItem.dataItem.price, 1849);
            Assert.assertEquals(responseItem.dataItem.cpuModel, "Intel Core i9");
            Assert.assertEquals(responseItem.dataItem.hardDiskSize, "1 TB");
    
    }
    
    @Test
    public void updateProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        Response response = RestAssured.given()
                                            .contentType("application/json")
                                            .log().all()
                                            .pathParam("path", "objects")
                                            .pathParam("idProduct", "ff808181932badb60195575b6ad758c1")
                                            .body(json)
                                            .when()
                                            .put("/{path}/{idProduct}");
        System.out.println(response.getBody().asString()); 

        JsonPath jsonPath = response.jsonPath();
       
        responseItem = jsonPath.getObject("", responseItem.class);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseItem.name, "Apple MacBook Pro Rosa Update");
        Assert.assertEquals(responseItem.dataItem.year, 2019);
        Assert.assertEquals(responseItem.dataItem.price, 1849);
        Assert.assertEquals(responseItem.dataItem.cpuModel, "Intel Core i9");
        Assert.assertEquals(responseItem.dataItem.hardDiskSize, "1 TB");

    }
    @Test
    public void getSingleProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        // Kirim GET request ke API
        Response response = RestAssured.given()
                                            .pathParam("idProduct", "ff808181932badb601955e06e2496539")
                                            .log().all()
                                            .get("/objects/{idProduct}");        
                                     
        System.out.println(response.getBody().asString());  
        JsonPath jsonPath = response.jsonPath();
       
        responseItem = jsonPath.getObject("", responseItem.class);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseItem.name, "Apple MacBook Pro Rosa");
        Assert.assertEquals(responseItem.dataItem.year, 2019);
        Assert.assertEquals(responseItem.dataItem.price, 1849);
        Assert.assertEquals(responseItem.dataItem.cpuModel, "Intel Core i9");
        Assert.assertEquals(responseItem.dataItem.hardDiskSize, "1 TB");
    }

   @Test
    public void deleteProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        Response response = RestAssured.given()
                                            .contentType("application/json")
                                            .log().all()
                                            .pathParam("path", "objects")
                                            .pathParam("idProduct", "ff808181932badb601955e08ed8c653c")
                                            .when()
                                            .delete("/{path}/{idProduct}");
        System.out.println(response.getBody().asString());
        
        Assert.assertEquals(response.statusCode(), 404);
    }
    


   
}

    
    


