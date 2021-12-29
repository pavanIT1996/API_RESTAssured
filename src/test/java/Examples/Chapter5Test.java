package Examples;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class Chapter5Test {
//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule(options().port(9876));

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("https://2q9od.mocklab.io/ca").
                then().
                assertThat().
                body("response.places.place_name", equalTo("Beverly Hills"));
    }

    @Test
    public void requestDeZipCode24848_checkThirdPlaceNameInResponseBody_expectKropp() {

        given().
                when().
                get("https://2q9od.mocklab.io/de").
                then().
                assertThat().
                body("response.places.place[2].place_name", equalTo("Kropp"));
    }

    @Test
    public void requestDeZipCode24848_checkLastPlaceNameInResponseBody_expectKleinBennebek() {

        given().
                when().
                get("https://2q9od.mocklab.io/de").
                then().
                assertThat().
                body("response.places.place[-1].place_name", equalTo("Klein Bennebek"));
    }

    @Test
    public void requestDeZipCode24848_checkLatitudeForSecondPlaceInResponseBody_expect5445() {

        given().
                when().
                get("https://2q9od.mocklab.io/de").
                then().
                assertThat().
                body("response.places.place[1].latitude", equalTo("54.45"));
    }

    @Test
    public void requestDeZipCode24848_checkNumberOfPlacesWithStateAbbreviationSH_expect4() {

        given().
                when().
                get("https://2q9od.mocklab.io/de").
                then().
                assertThat().
                body("response.places.place.findAll{it.state_abbreviation=='SH'}", hasSize(4));
    }
}
