package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "todo")
public class TodoRequestInvalidBDTO {

    private String title;
    private String doneStatus;
    private String description;

    public TodoRequestInvalidBDTO() {

    }

    public TodoRequestInvalidBDTO(String title, String doneStatus, String description) {
        this.title = title;
        this.doneStatus = doneStatus;
        this.description = description;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(String doneStatus) {
        this.doneStatus = doneStatus;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
