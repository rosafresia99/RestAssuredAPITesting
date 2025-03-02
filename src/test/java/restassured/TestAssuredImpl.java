package restassured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestAssuredImpl {
    public static void main(String[] args) {
        //getAllProducts();
        //getSingleProduct();
        //getByID();
        //addProduct();
        //updateProduct();
        //deleteProduct();
        //partiallyUpdate();
        //auth();
        getProduct();
    }

    public static String auth() {
        RestAssured.baseURI = "https://dummyjson.com/";

        String token;
        String jason = "{\n" + //
                        "   \"username\": \"emilys\",\n" + //
                        "   \"password\": \"emilyspass\",\n" + //
                        "   \"expiresInMins\": 30\n" + //
                        "}";
        
        Response response = RestAssured.given()
                                            .log().all()
                                            .body(jason)
                                            .contentType("application/json")
                                            .pathParam("path", "auth")
                                            .pathParam("section", "login")
                                        .when()
                                            .post("/{path}/{section}");
        System.out.println(response.getBody().asPrettyString());

        JsonPath jsonPath = response.jsonPath();
        System.out.println("Token: " + jsonPath.get("accessToken"));
        token = jsonPath.get("accessToken");

        return token;
        
    }

    public static void getProduct() {
        RestAssured.baseURI = "https://dummyjson.com/";

        String token;
        token = auth();
        // Kirim GET request ke API
        Response response = RestAssured.given()
                                            .log().all()
                                            .pathParam("path", "products")
                                            .pathParam("idProduct", 1)
                                            .header("Authorization", "Bearer " + token)
                                        .when()
                                            .get("/{path}/{idProduct}");        
                                     
        System.out.println(response.getBody().asString());  
    }

    public static void getAllProducts() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        // Kirim GET request ke API
        Response response = RestAssured.given().get("/objects");
            System.out.println(response.getBody().asString());
    }
     

    public static void getSingleProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        // Kirim GET request ke API
        Response response = RestAssured.given()
                                            .pathParam("idProduct", "7")
                                            .log().all()
                                            .get("/objects/{idProduct}");        
                                     
        System.out.println(response.getBody().asString());  
    }

    public static void getByID() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        // Kirim GET request ke API
        Response response = RestAssured.given()
                                            .queryParam("id", "3")
                                            .queryParam("id", "5")
                                            .queryParam("id", "10")
                                            .log().all()
                                            .get("/objects");        
                                     
        System.out.println(response.getBody().asString());  
    }

    public static void addProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        String json = "{\n" + //
                        "   \"name\": \"Apple MacBook Pro 12\",\n" + //
                        "   \"data\": {\n" + //
                        "      \"year\": 2019,\n" + //
                        "      \"price\": 1849.99,\n" + //
                        "      \"CPU model\": \"Intel Core i9\",\n" + //
                        "      \"Hard disk size\": \"1 TB\"\n" + //
                        "   }\n" + //
                        "}";

        Response response = RestAssured.given()
                                            .contentType("application/json")
                                            .log().all()
                                            .pathParam("path", "objects")
                                            .body(json)
                                            .when()
                                            .post("/{path}");
        System.out.println(response.getBody().asString()); 
    }
    
    public static void updateProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        String json = "{\n" + //
                        "   \"name\": \"Apple MacBook 12\",\n" + //
                        "   \"data\": {\n" + //
                        "      \"year\": 2019,\n" + //
                        "      \"price\": 1849.99,\n" + //
                        "      \"CPU model\": \"Intel Core i9\",\n" + //
                        "      \"Hard disk size\": \"1 TB\"\n" + //
                        "   }\n" + //
                        "}";

        Response response = RestAssured.given()
                                            .contentType("application/json")
                                            .log().all()
                                            .pathParam("path", "objects")
                                            .pathParam("idProduct", "ff808181932badb6019556a8db6757ec")
                                            .body(json)
                                            .when()
                                            .put("/{path}/{idProduct}");
        System.out.println(response.getBody().asString());  
    }

    public static void deleteProduct() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        Response response = RestAssured.given()
                                            .contentType("application/json")
                                            .log().all()
                                            .pathParam("path", "objects")
                                            .pathParam("idProduct", "ff808181932badb6019556a8db6757ec")
                                            .when()
                                            .delete("/{path}/{idProduct}");
        System.out.println(response.getBody().asString());  
    }

    public static void partiallyUpdate() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        String json = "{\n" + //
                        "   \"name\": \"Apple MacBook Pro 16 (Updated Name)\"\n" + //
                        "}";

        Response response = RestAssured.given()
                                            .contentType("application/json")
                                            .log().all()
                                            .pathParam("path", "objects")
                                            .pathParam("idProduct", "ff808181932badb6019556b743a657f9")
                                            .body(json)
                                            .when()
                                            .patch("/{path}/{idProduct}");
        System.out.println(response.getBody().asString());  
    }

}

