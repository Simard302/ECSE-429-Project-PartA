
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import dto.TodoResponseDTO;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class TestBase {

    public Process jarProcess;
    public TodoResponseDTO defaultTodo1;
    public TodoResponseDTO defaultTodo2;

    @BeforeEach
    public void startJar() throws Exception {
        int port = findAvailablePort();
        // Start the JAR file in a separate process
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "runTodoManagerRestAPI-1.5.5.jar", "-port=" + port);
        processBuilder.redirectErrorStream(true);
        try {
            jarProcess = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jarProcess.getInputStream()));

            int lineCount = 0;
            while ((reader.readLine()) != null) {
                lineCount++;
                if (lineCount >= 16) {
                    RestAssured.baseURI = "http://localhost:" + port;
                    Thread.sleep(100); // Wait for the server to start
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        defaultTodo1 = new TodoResponseDTO("1", "scan paperwork", "false", "");
        defaultTodo2 = new TodoResponseDTO("2", "file paperwork", "false", "");
    }

    @AfterEach
    public void stopJar() {
        jarProcess.destroy();
    }

    public void verifyNoSideEffects(int length) {
        Response response = given()
                .get("/todos");

        assertEquals(200, response.statusCode());

        TodoResponseDTO[] todos = response.jsonPath().getObject("todos", TodoResponseDTO[].class);

        assertEquals(length, todos.length);
    }

    public int findAvailablePort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            return socket.getLocalPort();
        }
    }
}
