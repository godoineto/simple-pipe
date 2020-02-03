package com.godoineto.simplepipe.integration.pipedrive;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.integration.CRMIntegrate;
import com.godoineto.simplepipe.integration.pipedrive.dto.DealDTO;
import com.godoineto.simplepipe.integration.pipedrive.dto.OrganizationDTO;
import com.godoineto.simplepipe.integration.pipedrive.dto.PersonDTO;
import com.godoineto.simplepipe.integration.pipedrive.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PipedriveIntegrate implements CRMIntegrate {

    private RestTemplate restTemplate;

    @Value("${pipedrive.apikey}")
    private String pipedriveApiKey;

    @Value("${pipedrive.url}")
    private String pipedriveUrl;

    public PipedriveIntegrate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void wonLead(LeadDTO leadDTO) {
        PersonDTO personDTO = createPersonDTO(leadDTO);
        OrganizationDTO organizationDTO = createOrganizationDTO(leadDTO);
        DealDTO dealDTO = new DealDTO(leadDTO, personDTO, organizationDTO);
        String url = getDealsUrlWithApiToken();
        restTemplate.postForLocation(url, dealDTO);
    }

    private PersonDTO createPersonDTO(LeadDTO leadDTO) {
        PersonDTO personDTO = new PersonDTO(leadDTO);
        String url = getPersonUrlWithApiToken();
        ResultDTO result = restTemplate.postForObject(url, personDTO, ResultDTO.class);
        return personDTO.withId(
                Optional.ofNullable(result)
                        .map(ResultDTO::getId));
    }

    private OrganizationDTO createOrganizationDTO(LeadDTO leadDTO) {
        OrganizationDTO organizationDTO = new OrganizationDTO(leadDTO);
        String url = getOrganizationUrlWithApiToken();
        ResultDTO result = restTemplate.postForObject(url, organizationDTO, ResultDTO.class);
        return organizationDTO.withId(
                Optional.ofNullable(result)
                .map(ResultDTO::getId));
    }

    private String getOrganizationUrlWithApiToken() {
        return pipedriveUrl
                .concat("v1/organizations")
                .concat("?api_token=")
                .concat(pipedriveApiKey);
    }

    private String getPersonUrlWithApiToken() {
        return pipedriveUrl
                .concat("v1/persons")
                .concat("?api_token=")
                .concat(pipedriveApiKey);
    }

    private String getDealsUrlWithApiToken() {
        return pipedriveUrl
                .concat("v1/deals")
                .concat("?api_token=")
                .concat(pipedriveApiKey);
    }
}
