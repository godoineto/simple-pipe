package com.godoineto.simplepipe.service;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.model.LeadStatus;

import java.util.List;
import java.util.Optional;

public interface LeadService {

    LeadDTO create(LeadDTO leadDTO);
    Optional<LeadDTO> update(String id, LeadDTO leadDTO);
    Optional<LeadDTO> get(String id);
    List<LeadDTO> listAll();
    void delete(String id);
    Optional<LeadDTO> finish(String id, LeadStatus status);
}
