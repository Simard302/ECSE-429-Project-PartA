import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import dto.TodoRequestDTO;
import dto.TodoRequestInvalidDTO;
import dto.TodoResponseDTO;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestUpdateInstancesFail extends TestBase {
    @Test
    public void testUpdateInstancePutNoTitle() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(null, false, "test");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .put("/todos/1");

        assertEquals(200, response.statusCode());

        TodoResponseDTO todo = response.jsonPath().getObject("", TodoResponseDTO.class);

        assertEquals(todoRequestDTO.getDescription(), todo.getDescription());

        this.verifyNoSideEffects(2);
    }

    @Test
    public void testUpdateInstancePostInvalidDatatype() {
        boolean[] wrongTitle = {false, false, false};
        boolean[] wrongDescription = {true, true, true};
        TodoRequestInvalidDTO todoRequestDTO = new TodoRequestInvalidDTO(wrongTitle, null, wrongDescription);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos/1");

        assertEquals(400, response.statusCode());
    }
}
