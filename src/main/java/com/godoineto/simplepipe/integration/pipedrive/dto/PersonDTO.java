package com.godoineto.simplepipe.integration.pipedrive.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.godoineto.simplepipe.api.dto.LeadDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class PersonDTO {

    @JsonIgnore
    private Integer id;
    private String name;
    private String email;

    public PersonDTO(LeadDTO leadDTO) {
        name = leadDTO.getName();
        email = leadDTO.getEmail();
    }

    public PersonDTO withId(Optional<Integer> maybeId) {
        maybeId.ifPresent(this::setId);
        return this;
    }
}
