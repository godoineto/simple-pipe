package com.godoineto.simplepipe.api.dto;

import com.godoineto.simplepipe.model.LeadStatus;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LeadDTO {

    private String id;
    @NotNull(message = "{lead.name.invalid}")
    private String name;
    @Email
    @NotNull(message = "{lead.email.invalid}")
    private String email;
    @NotNull(message = "{lead.company.invalid}")
    private String company;
    private String site;
    private List<String> phones;
    private LeadStatus status;
    private List<NoteDTO> notes;
    private String responsible;
}
