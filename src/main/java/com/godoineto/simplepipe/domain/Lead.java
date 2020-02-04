package com.godoineto.simplepipe.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.godoineto.simplepipe.domain.LeadStatus.OPEN;

@Data
@Document("lead")
public class Lead {
    @Id
    private String id;
    private String name;
    @Indexed
    private String email;
    private String company;
    private String site;
    private List<String> phones;
    private LeadStatus status;
    private List<Note> notes;
    @DBRef
//    @NotNull
    private User responsible;

    public boolean isOpen() {
        return OPEN.equals(status);
    }

    public boolean isFinished() {
        return !isOpen();
    }
}
