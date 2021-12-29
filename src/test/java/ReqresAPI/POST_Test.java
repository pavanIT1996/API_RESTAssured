package ReqresAPI;

import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class POST_Test {

    private static String requestBody =
            "{\n" +
            "  \"name\": \"test\",\n" +
            "  \"job\": \"test123\" \n}";

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                build();
    }


    @Test
    public void postRequest() {
        Response response = given().
                spec(requestSpec)
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("api/users")
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("test", response.jsonPath().getString("name"));
        Assertions.assertEquals("test123", response.jsonPath().getString("job"));

    }
}
