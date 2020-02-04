package com.godoineto.simplepipe.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.godoineto.simplepipe.model.LeadStatus.OPEN;

@Data
@Document("lead")
public class Lead {
    @Id
    private String id;
    private String name;
    private String email;
    private String company;
    private String site;
    private List<String> phones;
    private LeadStatus status;
    private List<Note> notes;
    private String responsible;

    public boolean isOpen() {
        return OPEN.equals(status);
    }

    public boolean isFinished() {
        return !isOpen();
    }
}
