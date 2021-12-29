package ReqresAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class PUT_Test {
    private static String requestBody =
            "{\n" +
                    "  \"name\": \"morpheus2\",\n" +
                    "  \"job\": \"zion resident2\" \n}";

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                build();
    }


    @Test
    public void putRequest() {
        Response response = given().
                spec(requestSpec)
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("api/users/2")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("morpheus2", response.jsonPath().getString("name"));
        Assertions.assertEquals("zion resident2", response.jsonPath().getString("job"));

    }
}
