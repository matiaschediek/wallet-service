package mchediek.wallet_service.bdd;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import mchediek.wallet_service.bdd.stepdefs.TestContext;

@CucumberContextConfiguration
@SpringBootTest
public class CucumberSpringConfiguration {
}