package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "todo")
public class TodoRequestInvalidADTO {

    private boolean[] title;
    private boolean doneStatus;
    private boolean[] description;

    public TodoRequestInvalidADTO() {

    }

    public TodoRequestInvalidADTO(boolean[] title, boolean doneStatus, boolean[] description) {
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
    public boolean getDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(boolean doneStatus) {
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
