package ReqresAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class DELETE_Test {
    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                build();
    }


    @Test
    public void deleteRequest() {
        Response response = given().
                spec(requestSpec)
                .header("Content-type", "application/json")
                .when()
                .delete("api/users/2")
                .then()
                .extract().response();

        Assertions.assertEquals(204, response.statusCode());

    }
}
