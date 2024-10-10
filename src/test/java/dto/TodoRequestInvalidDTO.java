package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "todo")
public class TodoRequestInvalidDTO {

    private boolean[] title;
    private String doneStatus;
    private boolean[] description;

    public TodoRequestInvalidDTO() {

    }

    public TodoRequestInvalidDTO(boolean[] title, String doneStatus, boolean[] description) {
        this.title = title;
        this.doneStatus = doneStatus;
        this.description = description;
    }

    @XmlElement
    public boolean[] getTitle() {
        return title;
    }

    public void setTitle(boolean[] title) {
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
    public boolean[] getDescription() {
        return description;
    }

    public void setDescription(boolean[] description) {
        this.description = description;
    }
}
