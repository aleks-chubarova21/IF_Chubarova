package cucumber;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import steps.RickAndMortySteps;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.ValidatableResponse;
import config.Props;
import org.aeonbits.owner.ConfigFactory;

@Story("Rick and Morty API Tests")
public class RickAndMortyCucumberSteps {
    private static final Props props = ConfigFactory.create(Props.class);
    private ValidatableResponse apiResponse;
    private String mortySpecies;
    private String mortyLocation;
    private String lastCharacterSpecies;
    private String lastCharacterLocation;

    @Дано("информация о персонаже {string} получена")
    @Step("Получение информации о персонаже: {name}")
    @Description("Получение информации о персонаже Морти через API")
    public void getCharacterInfo(String name) {
        apiResponse = RickAndMortySteps.findMorty();
        Assertions.assertNotNull(apiResponse);
    }

    @Когда("пользователь получает детали последнего эпизода с этим персонажем")
    @Step("Получение деталей последнего эпизода с персонажем")
    @Description("Получение информации о последнем эпизоде, в котором участвовал персонаж")
    public void getUserLastEpisodeDetails() {
        apiResponse = RickAndMortySteps.getLastEpisodeDetails();
        Assertions.assertNotNull(apiResponse);
    }

    @Когда("пользователь получает информацию о последнем персонаже из этого эпизода")
    @Step("Получение информации о последнем персонаже из эпизода")
    @Description("Получение информации о последнем персонаже, который появился в эпизоде")
    public void getUserLastCharacterFromLastEpisode() {
        apiResponse = RickAndMortySteps.getLastCharacterFromLastEpisode();
        Assertions.assertNotNull(apiResponse);
    }

    @Тогда("имя персонажа в ответе совпадает с {string}")
    @Step("Проверка имени персонажа в ответе: ожидаем {expectedName}")
    @Description("Проверка соответствия имени персонажа в ответе API")
    public void verifyCharacterNameInResponse(String expectedName) {
        String actualName = apiResponse.extract().path("results[0].name");
        Assertions.assertEquals(props.nameCharacterByRickAndMorty(), actualName, "Имя персонажа должно совпадать с Морти");
    }

    @Тогда("ответ содержит непустое имя эпизода и номер эпизода")
    @Step("Проверка имени и номера эпизода в ответе")
    @Description("Проверка наличия и корректности данных эпизода в ответе")
    public void verifyEpisodeDetailsNotEmpty() {
        String episodeName = apiResponse.extract().path("name");
        String episodeNumber = apiResponse.extract().path("episode");
        Assertions.assertFalse(episodeName.isEmpty(), "Имя эпизода не должно быть пустым");
        Assertions.assertFalse(episodeNumber.isEmpty(), "Номер эпизода не должен быть пустым");
    }

    @Тогда("ответ содержит непустое имя персонажа, расу и локацию")
    @Step("Проверка деталей персонажа в ответе (имя, раса, локация)")
    @Description("Проверка наличия и корректности данных персонажа в ответе")
    public void verifyCharacterDetailsNotEmpty() {
        String characterName = apiResponse.extract().path("name");
        String species = apiResponse.extract().path("species");
        String location = apiResponse.extract().path("location.name");
        Assertions.assertFalse(characterName.isEmpty(), "Имя персонажа не должно быть пустым");
        Assertions.assertFalse(species.isEmpty(), "Раса персонажа не должна быть пустой");
        Assertions.assertFalse(location.isEmpty(), "Локация персонажа не должна быть пустой");
    }

    @Тогда("раса последнего персонажа совпадает с расой Морти")
    @Step("Проверка расы последнего персонажа")
    @Description("Проверка соответствия расы последнего персонажа расе Морти")
    public void verifyLastCharacterSpecies() {
        ValidatableResponse mortyResponse = RickAndMortySteps.findMorty();
        mortySpecies = mortyResponse.extract().path("results[0].species");
        lastCharacterSpecies = apiResponse.extract().path("species");
        Assertions.assertEquals(mortySpecies, lastCharacterSpecies, "Расы персонажей должны совпадать");
    }

    @Тогда("локация последнего персонажа не совпадает с локацией Морти")
    @Step("Проверка локации последнего персонажа")
    @Description("Проверка отличия локации последнего персонажа от локации Морти")
    public void verifyLastCharacterLocation() {
        ValidatableResponse mortyResponse = RickAndMortySteps.findMorty();
        mortyLocation = mortyResponse.extract().path("results[0].location.name");
        lastCharacterLocation = apiResponse.extract().path("location.name");
        Assertions.assertNotEquals(mortyLocation, lastCharacterLocation, "Локации персонажей не должны совпадать");
    }
}