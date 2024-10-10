
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import dto.TodoRequestDTO;
import dto.TodoRequestInvalidBDTO;
import dto.TodoResponseDTO;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestCreateInstances extends TestBase {

    @Test
    public void testCreateInstance() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("title1", false, "");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos");

        assertEquals(201, response.statusCode());

        TodoResponseDTO todoResponseDTO = response.as(TodoResponseDTO.class);

        assertEquals(todoRequestDTO.getTitle(), todoResponseDTO.getTitle());
        assertEquals(todoRequestDTO.getDoneStatus() ? "true" : "false", todoResponseDTO.getDoneStatus());
        assertEquals(todoRequestDTO.getDescription(), todoResponseDTO.getDescription());

        this.verifyNoSideEffects(3);
    }

    @Test
    public void testCreateInstanceNoTitle() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(null, false, "");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos");

        assertEquals(400, response.statusCode());
    }

    @Test
    public void testCreateInstanceInvalidDoneStatus() {
        TodoRequestInvalidBDTO todoRequestDTO = new TodoRequestInvalidBDTO(null, "false", "");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos");

        assertEquals(400, response.statusCode());
    }

    @Test
    public void testCreateInstanceSameTitle() {
        TodoRequestDTO todoRequestDTO1 = new TodoRequestDTO("title1", false, "");
        TodoRequestDTO todoRequestDTO2 = new TodoRequestDTO("title1", false, "");

        Response response1 = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO1)
                .post("/todos");

        assertEquals(201, response1.statusCode());

        Response response2 = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO2)
                .post("/todos");

        assertEquals(201, response2.statusCode());

        TodoResponseDTO todoResponseDTO1 = response1.as(TodoResponseDTO.class);

        assertEquals(todoRequestDTO1.getTitle(), todoResponseDTO1.getTitle());
        assertEquals(todoRequestDTO1.getDoneStatus() ? "true" : "false", todoResponseDTO1.getDoneStatus());
        assertEquals(todoRequestDTO1.getDescription(), todoResponseDTO1.getDescription());

        TodoResponseDTO todoResponseDTO2 = response2.as(TodoResponseDTO.class);

        assertEquals(todoRequestDTO2.getTitle(), todoResponseDTO2.getTitle());
        assertEquals(todoRequestDTO2.getDoneStatus() ? "true" : "false", todoResponseDTO2.getDoneStatus());
        assertEquals(todoRequestDTO2.getDescription(), todoResponseDTO2.getDescription());

        this.verifyNoSideEffects(4);
    }
}
