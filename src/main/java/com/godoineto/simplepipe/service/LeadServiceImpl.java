package com.godoineto.simplepipe.service;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.domain.Lead;
import com.godoineto.simplepipe.domain.LeadStatus;
import com.godoineto.simplepipe.integration.CRMIntegrate;
import com.godoineto.simplepipe.repository.LeadRepository;
import com.godoineto.simplepipe.service.mapper.LeadMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository repository;
    private final LeadMapper mapper;
    private final CRMIntegrate processor;

    public LeadServiceImpl(LeadRepository repository, LeadMapper mapper, CRMIntegrate processor) {
        this.repository = repository;
        this.mapper = mapper;
        this.processor = processor;
    }

    @Override
    public LeadDTO create(LeadDTO leadDTO) {
        leadDTO.setStatus(LeadStatus.OPEN);
        Optional<Lead> byEmail = repository.findByEmail(leadDTO.getEmail());
        if (byEmail.isPresent()) {
            Lead another = byEmail.get();
            if (another.isFinished()) {
                return mapper.toDTO(updateLeadInfo(another.getId(), leadDTO));
            } else {
                return mapper.toDTO(another);
            }
        }
        return mapper.toDTO(repository.save(mapper.toEntity(leadDTO)));
    }

    @Override
    public Optional<LeadDTO> update(String id, LeadDTO leadDTO) {
        if (repository.existsById(id)) {
            return Optional.ofNullable(mapper.toDTO(updateLeadInfo(id, leadDTO)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<LeadDTO> get(String id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public List<LeadDTO> listAll() {
        return repository.findAll()
                .stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.findById(id)
                .ifPresent(repository::delete);
    }

    @Override
    public Optional<LeadDTO> finish(String id, LeadStatus status) {
        return repository.findById(id)
                .filter(Lead::isOpen)
                .map(lead -> {
                    lead.setStatus(status);
                    if (LeadStatus.WON.equals(status)) {
                        processor.wonLead(mapper.toDTO(lead));
                    }
                    return mapper.toDTO(repository.save(lead));
                });
    }

    private Lead updateLeadInfo(String id, LeadDTO leadDTO) {
        Lead lead = mapper.toEntity(leadDTO);
        lead.setId(id);
        return repository.save(lead);
    }
}
