package mchediek.wallet_service.bdd;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
class CucumberTestRunner {

    private static LocalStackContainer localstack;
    private static MongoDBContainer mongoContainer;

    @BeforeAll
    public static void beforeAll() {

        localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"))
                .withServices(LocalStackContainer.Service.SNS);
        localstack.start();

        mongoContainer = new MongoDBContainer(DockerImageName.parse("mongo:5.0.7"));
        mongoContainer.start();

        System.setProperty("MONGO_URI", mongoContainer.getReplicaSetUrl());

        System.setProperty("AWS_ACCESS_KEY_ID", localstack.getAccessKey());
        System.setProperty("AWS_SECRET_ACCESS_KEY", localstack.getSecretKey());
        System.setProperty("AWS_REGION", localstack.getRegion());

        System.setProperty(
                "SNS_ENDPOINT_OVERRIDE",
                localstack.getEndpointOverride(LocalStackContainer.Service.SNS).toString()
        );


    }

    @AfterAll
    public static void afterAll() {
        mongoContainer.stop();
        localstack.stop();
    }
}