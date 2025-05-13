package steps;

import api.RickAndMortyApi;
import io.restassured.response.ValidatableResponse;
import util.TestProperties;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RickAndMortySteps {

    public static ValidatableResponse findMorty() {
        return given()
                .spec(RickAndMortyApi.getBaseSpec())
                .queryParam("name", TestProperties.getProperty("nameCharacterByRickAndMorty"))
                .when()
                .get(TestProperties.getProperty("character.endpoint"))
                .then();
    }

    public static ValidatableResponse getMortyById() {
        ValidatableResponse response = findMorty();

        System.out.println("\nИнформация по Морти");
        System.out.println("Имя: " + response.extract().path("results[0].name"));
        System.out.println("Местоположение: " + response.extract().path("results[0].location.name"));
        System.out.println("Раса: " + response.extract().path("results[0].species"));
        
        return response;
    }

    public static ValidatableResponse getLastEpisode() {
        List<String> episodes = findMorty()
                .extract()
                .path("results[0].episode");

        String lastEpisodeUrl = episodes.get(episodes.size() - 1);

        return given()
                .spec(RickAndMortyApi.getBaseSpec())
                .when()
                .get(lastEpisodeUrl)
                .then();
    }

    public static ValidatableResponse getLastCharacterLastEpisode() {
        ValidatableResponse episodeResponse = getLastEpisode();
        List<String> characters = episodeResponse.extract().path("characters");
        String lastCharacterUrl = characters.get(characters.size() - 1);
        String characterId = lastCharacterUrl.substring(lastCharacterUrl.lastIndexOf("/") + 1);

        return given()
                .spec(RickAndMortyApi.getBaseSpec())
                .when()
                .get(TestProperties.getProperty("character.endpoint") + "/" + characterId)
                .then();
    }

    public static ValidatableResponse getLastCharacterInfo() {
        return getLastCharacterLastEpisode();
    }
}