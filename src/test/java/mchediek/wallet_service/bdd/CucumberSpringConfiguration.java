package mchediek.wallet_service.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import mchediek.wallet_service.MongoTestContainerConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@Import(MongoTestContainerConfig.class)
@SpringBootTest
public class CucumberSpringConfiguration {

}