import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import dto.TodoRequestInvalidADTO;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestCreateInstancesFail extends TestBase {

    @Test
    public void testUpdateInstancePostInvalidDatatype() {
        boolean[] wrongTitle = {false, false, false};
        boolean[] wrongDescription = {true, true, true};
        TodoRequestInvalidADTO todoRequestDTO = new TodoRequestInvalidADTO(wrongTitle, false, wrongDescription);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos");

        assertEquals(400, response.statusCode());
    }
}
