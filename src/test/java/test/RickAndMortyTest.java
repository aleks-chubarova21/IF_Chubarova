package test;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.RickAndMortySteps;
import config.Props;
import org.aeonbits.owner.ConfigFactory;

public class RickAndMortyTest {
    private static final Props props = ConfigFactory.create(Props.class);

    @Test
    @DisplayName("1. Найти информацию по персонажу Морти Смит")
    public void testFindMorty() {
        ValidatableResponse response = RickAndMortySteps.findMorty();
        String mortyName = response.extract().path("results[0].name");
        
        Assertions.assertEquals(props.nameCharacterByRickAndMorty(), mortyName, "Имя персонажа должно совпадать с Морти");
    }

    @Test
    @DisplayName("2. Получить последний эпизод с Морти")
    public void testGetLastEpisodeDetails() {
        ValidatableResponse response = RickAndMortySteps.getLastEpisodeDetails();
        Assertions.assertNotNull(response, "Ответ не должен быть null");

        String episodeName = response.extract().path("name");
        String episodeNumber = response.extract().path("episode");

        Assertions.assertFalse(episodeName.isEmpty(), "Имя эпизода не должно быть пустым");
        Assertions.assertFalse(episodeNumber.isEmpty(), "Номер эпизода не должен быть пустым");
    }

    @Test
    @DisplayName("3. Получить последнего персонажа из последнего эпизода")
    public void testGetLastCharacterFromLastEpisode() {
        ValidatableResponse response = RickAndMortySteps.getLastCharacterFromLastEpisode();
        Assertions.assertNotNull(response, "Ответ не должен быть null");

        String characterName = response.extract().path("name");
        String species = response.extract().path("species");
        String location = response.extract().path("location.name");

        Assertions.assertFalse(characterName.isEmpty(), "Имя персонажа не должно быть пустым");
        Assertions.assertFalse(species.isEmpty(), "Раса персонажа не должна быть пустой");
        Assertions.assertFalse(location.isEmpty(), "Локация персонажа не должна быть пустой");
    }

    @Test
    @DisplayName("4. Проверить расу и локацию последнего персонажа")
    public void testCompareLastCharacterWithMorty() {
        ValidatableResponse mortyResponse = RickAndMortySteps.findMorty();
        ValidatableResponse lastCharacterResponse = RickAndMortySteps.getLastCharacterFromLastEpisode();
        Assertions.assertNotNull(lastCharacterResponse, "Ответ не должен быть null");

        String mortySpecies = mortyResponse.extract().path("results[0].species");
        String mortyLocation = mortyResponse.extract().path("results[0].location.name");
        String lastCharacterSpecies = lastCharacterResponse.extract().path("species");
        String lastCharacterLocation = lastCharacterResponse.extract().path("location.name");

        Assertions.assertEquals(mortySpecies, lastCharacterSpecies, "Расы персонажей должны совпадать");
        Assertions.assertNotEquals(mortyLocation, lastCharacterLocation, "Локации персонажей не должны совпадать");
    }
}
