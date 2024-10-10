
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestXMLEncoding extends TestBase {

    @Test
    public void testCreateInstanceFromXMLEncoding() throws JAXBException {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("title1", false, "description1");

        Response response = given()
                .contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body(todoRequestDTO)
                .post("/todos");

        assertEquals(201, response.statusCode());

        JAXBContext jaxbContext = JAXBContext.newInstance(TodoResponseDTO.class);

        Unmarshaller Unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(response.getBody().asString());
        TodoResponseDTO todo = (TodoResponseDTO) Unmarshaller.unmarshal(reader);

        assertEquals(todoRequestDTO.getTitle(), todo.getTitle());
        assertEquals(todoRequestDTO.getDoneStatus() ? "true" : "false", todo.getDoneStatus());
        assertEquals(todoRequestDTO.getDescription(), todo.getDescription());
    }

    @Test
    public void testRetrieveInstancesFromXMLEncoding() throws JAXBException {

        Response response = given()
                .contentType(ContentType.XML)
                .accept(ContentType.XML)
                .get("/todos");

        assertEquals(200, response.statusCode());

        JAXBContext jaxbContext = JAXBContext.newInstance(TodoResponseListDTO.class);

        Unmarshaller Unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(response.getBody().asString());
        TodoResponseListDTO todos = (TodoResponseListDTO) Unmarshaller.unmarshal(reader);

        assertEquals(2, todos.getTodos().size());
    }
}
