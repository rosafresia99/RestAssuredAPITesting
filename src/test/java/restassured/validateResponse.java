package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class validateResponse {
    public static void main(String[] args) {
        getSingleProduct();
    }

    public static void getSingleProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        // Kirim GET request ke API
        Response response = RestAssured.given()
                                            .pathParam("idProduct", "7")
                                            .log().all()
                                            .get("/objects/{idProduct}");        
                                     
        System.out.println(response.getBody().asPrettyString());  

        //ValidatableResponse validatableResponse = response.then().body("id", equalTo(operand:7));
    }

    

}
