
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class TestRetrieveInstances extends TestBase {

    @Test
    public void testRetrieveAllInstances() {
        Response response = given()
                .get("/todos");

        assertEquals(200, response.statusCode());

        TodoResponseDTO[] todos = response.jsonPath().getObject("todos", TodoResponseDTO[].class);

        assertEquals(2, todos.length);
    }

    @Test
    void testRetrieveOneInstance() {
        Response response = given()
                .get("/todos/1");

        assertEquals(200, response.statusCode());

        TodoResponseDTO[] todos = response.jsonPath().getObject("todos", TodoResponseDTO[].class);

        assertEquals(1, todos.length);
        assertEquals(defaultTodo1.getId(), todos[0].getId());
        assertEquals(defaultTodo1.getTitle(), todos[0].getTitle());
        assertEquals(defaultTodo1.getDoneStatus(), todos[0].getDoneStatus());
        assertEquals(defaultTodo1.getDescription(), todos[0].getDescription());
    }

    @Test
    void testRetrieveOneInstanceNotFound() {
        Response response = given()
                .get("/todos/3");

        assertEquals(404, response.statusCode());
    }

    @Test
    void testRetrieveFilteredInstances() {
        Response response = given()
                .get("/todos?title=scan paperwork");

        assertEquals(200, response.statusCode());

        TodoResponseDTO[] todos = response.jsonPath().getObject("todos", TodoResponseDTO[].class);

        assertEquals(1, todos.length);
        assertEquals("scan paperwork", todos[0].getTitle());
    }
}
