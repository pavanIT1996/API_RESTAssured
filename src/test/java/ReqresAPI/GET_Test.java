package ReqresAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GET_Test {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                build();
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void requestUser2_checkEmailResponseBody() {

        given().
                spec(requestSpec).
                when().
                get("api/users/2").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("data.'email'", equalTo("janet.weaver@reqres.in"));
    }
}
