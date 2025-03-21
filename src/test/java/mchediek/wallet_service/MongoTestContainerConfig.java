package mchediek.wallet_service;

import io.cucumber.java.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class MongoTestContainerConfig {

    static final MongoDBContainer mongoContainer = new MongoDBContainer(
            DockerImageName.parse("mongo:6.0") // Podés cambiar la versión
    );

    static {
        mongoContainer.start();
        System.setProperty("MONGO_URI", mongoContainer.getReplicaSetUrl());
    }

    @AfterAll
    static void stopContainer() {
        mongoContainer.stop();
    }

}