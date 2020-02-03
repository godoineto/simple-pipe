package com.godoineto.simplepipe.integration.pipedrive.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.godoineto.simplepipe.api.dto.LeadDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class OrganizationDTO {

    @JsonIgnore
    private Integer id;
    private String name;

    public OrganizationDTO(LeadDTO leadDTO) {
        name = leadDTO.getCompany();
    }

    public OrganizationDTO withId(Optional<Integer> maybeId) {
        maybeId.ifPresent(this::setId);
        return this;
    }
}
