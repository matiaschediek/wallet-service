package mchediek.wallet_service.bdd.stepdefs;

import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class CucumberHooks {

    @Autowired
    private TestContext testContext;

    @Before
    public void beforeScenario() {
        testContext.clear();
    }

}
