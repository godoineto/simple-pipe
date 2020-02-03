package com.godoineto.simplepipe.integration.pipedrive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godoineto.simplepipe.api.dto.LeadDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DealDTO {

    private String title;
    private String status;
    @JsonProperty("person_id")
    private Integer personId;
    @JsonProperty("org_id")
    private Integer orgId;

    public DealDTO(LeadDTO leadDTO, PersonDTO personDTO, OrganizationDTO organizationDTO) {
        title = leadDTO.getCompany();
        status = leadDTO.getStatus().toString().toLowerCase();
        personId = personDTO.getId();
        orgId = organizationDTO.getId();
    }
}
