package com.godoineto.simplepipe.service;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.domain.Lead;
import com.godoineto.simplepipe.domain.LeadStatus;
import com.godoineto.simplepipe.integration.CRMIntegrate;
import com.godoineto.simplepipe.integration.pipedrive.PipedriveIntegrate;
import com.godoineto.simplepipe.repository.LeadRepository;
import com.godoineto.simplepipe.service.mapper.LeadMapper;
import fixture.LeadDTOFixture;
import fixture.LeadFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LeadServiceImplTest {

    LeadService service;
    LeadRepository repository;
    LeadMapper mapper;
    CRMIntegrate crmIntegrate;

    @BeforeEach
    void setup() {
        repository = mock(LeadRepository.class);
        mapper = mock(LeadMapper.class);
        crmIntegrate = mock(PipedriveIntegrate.class);
        service = new LeadServiceImpl(repository, mapper, crmIntegrate);
    }

    @Test
    void shouldCreateNewLeadWhenHasNotOtherWithSameEmail() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        Lead lead = LeadFixture.get();
        when(mapper.toEntity(leadDTO)).thenReturn(lead);
        when(repository.findByEmail(lead.getEmail())).thenReturn(Optional.empty());

        service.create(leadDTO);

        verify(repository, times(1)).save(any(Lead.class));
        verify(repository, times(1)).save(lead);
    }

    @Test
    void shouldUpdateLeadWhenHasAnotherWithSameEmailAndIsFinished() {
        LeadDTO leadDTO = LeadDTOFixture.withNoteDTO();
        Lead lead = LeadFixture.withNote();

        Lead anotherLead = LeadFixture.get();
        anotherLead.setId("Old Id");
        anotherLead.setStatus(LeadStatus.WON);
        when(repository.findByEmail(leadDTO.getEmail())).thenReturn(Optional.of(anotherLead));

        when(mapper.toEntity(leadDTO)).thenReturn(lead);
        when(repository.save(lead)).thenReturn(lead);


        service.create(leadDTO);

        assertEquals("Old Id", lead.getId());
        verify(repository, times(1)).findByEmail(leadDTO.getEmail());
        verify(repository, times(1)).save(any(Lead.class));
        verify(repository, times(1)).save(lead);
    }

    @Test
    void shouldNotCreateNorUpdateLeadWhenHasAnotherWithSameEmailAndIsOpen() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        Lead anotherLead = LeadFixture.get();
        when(repository.findByEmail(leadDTO.getEmail())).thenReturn(Optional.of(anotherLead));

        service.create(leadDTO);

        verify(mapper, times(1)).toDTO(anotherLead);
        verify(repository, never()).save(any(Lead.class));
    }

    @Test
    void shouldUpdateLeadCorrectly() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        Lead lead = LeadFixture.get();
        when(mapper.toEntity(leadDTO)).thenReturn(lead);
        when(repository.existsById(leadDTO.getId())).thenReturn(true);
        when(repository.save(lead)).thenReturn(lead);

        service.update(leadDTO.getId(), leadDTO);

        verify(repository, times(1)).save(any(Lead.class));
        verify(repository, times(1)).save(lead);
    }

    @Test
    void shouldNotUpdateLeadWheNotFoundId() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        when(repository.existsById(leadDTO.getId())).thenReturn(false);

        service.update(leadDTO.getId(), leadDTO);

        verify(repository, never()).save(any(Lead.class));
    }

    @Test
    void shouldGetLeadCorrectly() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        Lead lead = LeadFixture.get();
        when(repository.findById(leadDTO.getId())).thenReturn(Optional.of(lead));
        when(mapper.toDTO(lead)).thenReturn(leadDTO);

        service.get(leadDTO.getId());

        verify(repository, times(1)).findById(leadDTO.getId());
    }

    @Test
    void shouldListAllLeadsCorrectly() {
        service.listAll();

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldDeleteLeadCorrectly() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        Lead lead = LeadFixture.get();
        when(repository.findById(leadDTO.getId())).thenReturn(Optional.of(lead));

        service.delete(leadDTO.getId());

        verify(repository, times(1)).delete(lead);
    }

    @Test
    void shouldChangeStatusCorrectly() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        Lead lead = mock(Lead.class);
        when(lead.isOpen()).thenReturn(true);
        when(repository.findById(leadDTO.getId())).thenReturn(Optional.of(lead));
        when(mapper.toDTO(lead)).thenReturn(leadDTO);

        service.finish(leadDTO.getId(), LeadStatus.WON);

        verify(lead, times(1)).setStatus(LeadStatus.WON);
        verify(crmIntegrate, times(1)).wonLead(leadDTO);
        verify(repository, times(1)).save(lead);
    }

    @Test
    void shouldNotChangeStatusWhenHasNotFoundAnyLead() {
        LeadDTO leadDTO = LeadDTOFixture.get();
        when(repository.findById(leadDTO.getId())).thenReturn(Optional.empty());

        service.finish(leadDTO.getId(), LeadStatus.WON);

        verify(repository, never()).save(any(Lead.class));
    }
}