package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "todos")
public class TodoResponseListDTO {

    private List<TodoResponseDTO> todos;

    @XmlElement(name = "todo")
    public List<TodoResponseDTO> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoResponseDTO> todos) {
        this.todos = todos;
    }
}
