package steps;

import io.restassured.response.ValidatableResponse;
import api.RickAndMortyApi;
import config.Props;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

public class RickAndMortySteps extends RickAndMortyApi {

    private static ValidatableResponse makeGetRequest(String url) {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(url)
                .then()
                .log().all();
    }

    public static ValidatableResponse findMorty() {
        Props props = ConfigFactory.create(Props.class);
        return given()
                .spec(getBaseSpec())
                .queryParam("name", props.nameCharacterByRickAndMorty())
                .when()
                .get(props.urlRickAndMorty() + "/character")
                .then()
                .log().all()
                .assertThat()
                .body("results.name", hasItem(props.nameCharacterByRickAndMorty()));
    }

    public static ValidatableResponse getLastEpisodeDetails() {
        ValidatableResponse mortyResponse = findMorty();
        String[] episodeUrls = mortyResponse.extract().path("results[0].episode").toString().replace("[", "").replace("]", "").split(",");

        if (episodeUrls.length > 0) {
            String lastEpisodeUrl = episodeUrls[episodeUrls.length - 1].trim();
            return makeGetRequest(lastEpisodeUrl);
        }

        return null;
    }

    public static ValidatableResponse getLastCharacterFromLastEpisode() {
        ValidatableResponse lastEpisodeResponse = getLastEpisodeDetails();

        if (lastEpisodeResponse == null) {
            return null;
        }

        String[] characterUrls = lastEpisodeResponse.extract().path("characters").toString().replace("[", "").replace("]", "").split(",");
        if (characterUrls.length > 0) {
            String lastCharacterUrl = characterUrls[characterUrls.length - 1].trim();
            return makeGetRequest(lastCharacterUrl);
        }

        return null;
    }

}