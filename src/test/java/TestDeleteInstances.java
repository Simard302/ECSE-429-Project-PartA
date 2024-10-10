
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import dto.TodoResponseDTO;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class TestDeleteInstances extends TestBase {

    @Test
    public void testDeleteInstance() {
        Response response1 = given()
                .delete("/todos/1");

        assertEquals(200, response1.statusCode());

        Response response2 = given()
                .get("/todos");

        assertEquals(200, response2.statusCode());
        TodoResponseDTO[] todos = response2.jsonPath().getObject("todos", TodoResponseDTO[].class);

        assertEquals(1, todos.length);

        this.verifyNoSideEffects(1);
    }

    @Test
    public void testDeleteInstanceNotFound() {
        Response response = given()
                .delete("/todos/3");

        assertEquals(404, response.statusCode());

        this.verifyNoSideEffects(2);
    }
}
