package test;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.RickAndMortySteps;

import static org.hamcrest.Matchers.*;

public class RickAndMortyTest {

    @Test
    @DisplayName("Получение информации о Морти и его последнем эпизоде")
    public void testGetMortyLastEpisode() {
        ValidatableResponse mortyResponse = RickAndMortySteps.findMorty();
        
        mortyResponse
                .statusCode(200)
                .body("results[0].name", equalTo("Morty Smith"));

        System.out.println("\nИнформация о Морти Смит");
        System.out.println("Имя: Morty Smith");

        ValidatableResponse episodeResponse = RickAndMortySteps.getLastEpisode();
        String episodeName = episodeResponse.extract().path("name");
        String episode = episodeResponse.extract().path("episode");
        
        System.out.println("\nИнформация о последнем эпизоде Морти:");
        System.out.println("Название эпизода: " + episodeName);
        System.out.println("Номер эпизода: " + episode);
        
        episodeResponse
                .statusCode(200)
                .body("name", notNullValue())
                .body("episode", notNullValue());
    }

    @Test
    @DisplayName("Получение информации о последнем персонаже из эпизода")
    public void testGetLastCharacterLastEpisode() {
        ValidatableResponse response = RickAndMortySteps.getLastCharacterLastEpisode();

        String characterName = response.extract().path("name");

        System.out.println("\nИнформация о последнем персонаже из эпизода:");
        System.out.println("Имя персонажа: " + characterName);
        
        response
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test
    @DisplayName("Получение информации о расе и местоположении последнего персонажа")
    public void testGetLastCharacterInfo() {
        ValidatableResponse response = RickAndMortySteps.getLastCharacterInfo();
        
        String characterName = response.extract().path("name");
        String species = response.extract().path("species");
        String location = response.extract().path("location.name");
        
        System.out.println("\nИнформация о последнем персонаже:");
        System.out.println("Имя: " + characterName);
        System.out.println("Раса: " + species);
        System.out.println("Местонахождение: " + location);
        
        response
                .statusCode(200)
                .body("species", notNullValue())
                .body("location.name", notNullValue());
    }

    @Test
    @DisplayName("Сравнение расы и местоположения последнего персонажа с Морти")
    public void testCompareWithMorty() {
        ValidatableResponse mortyResponse = RickAndMortySteps.getMortyById();

        String mortyName = mortyResponse.extract().path("results[0].name");
        String mortySpecies = mortyResponse.extract().path("results[0].species");
        String mortyLocation = mortyResponse.extract().path("results[0].location.name");

        ValidatableResponse lastCharacterResponse = RickAndMortySteps.getLastCharacterInfo();
        String lastCharacterSpecies = lastCharacterResponse.extract().path("species");
        String lastCharacterLocation = lastCharacterResponse.extract().path("location.name");

        System.out.println("\nСравнение с Морти");
        System.out.println("Имя: " + mortyName);
        System.out.println("Раса Морти: " + mortySpecies);
        System.out.println("Раса последнего персонажа: " + lastCharacterSpecies);
        System.out.println("Местоположение Морти: " + mortyLocation);
        System.out.println("Местоположение последнего персонажа: " + lastCharacterLocation);
        
        boolean sameSpecies = mortySpecies.equals(lastCharacterSpecies);
        boolean sameLocation = mortyLocation.equals(lastCharacterLocation);
        
        System.out.println("\nРезультаты сравнения:");
        System.out.println("Раса совпадает: " + sameSpecies);
        System.out.println("Местоположение совпадает: " + sameLocation);
    }
}
