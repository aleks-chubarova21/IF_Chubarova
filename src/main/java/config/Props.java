package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/test/resources/test.properties"
})
public interface Props extends Config {
    @Key("urlRickAndMorty")
    String urlRickAndMorty();

    @Key("nameCharacterByRickAndMorty")
    String nameCharacterByRickAndMorty();

    @Key("baseUrlReqRes")
    String baseUrlReqRes();

    @Key("postUrlReqRes")
    String postUrlReqRes();

    @Key("newName")
    String newName();

    @Key("newJob")
    String newJob();

    @Key("statusCodeReqRes")
    String statusCodeReqRes();

    @Key("apiKeyReqRes")
    String apiKeyReqRes();

    @Key("apiKeyHeaderName")
    String apiKeyHeaderName();
}
