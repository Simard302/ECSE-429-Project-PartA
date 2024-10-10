
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import io.restassured.RestAssured;

public class TestBase {

    public Process jarProcess;
    public TodoResponseDTO defaultTodo1;
    public TodoResponseDTO defaultTodo2;

    @BeforeEach
    public void startJar() throws Exception {
        // Start the JAR file in a separate process
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "runTodoManagerRestAPI-1.5.5.jar");
        jarProcess = processBuilder.start();

        // Wait for the API to become available (use a better way for production code)
        try {
            Thread.sleep(3000);  // Wait for 5 seconds for the JAR to fully start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        defaultTodo1 = new TodoResponseDTO("1", "scan paperwork", "false", "");
        defaultTodo2 = new TodoResponseDTO("2", "file paperwork", "false", "");

        // Set the base URI for RestAssured
        RestAssured.baseURI = "http://localhost:4567";
    }

    @AfterEach
    public void stopJar() {
        jarProcess.destroy();
    }
}
