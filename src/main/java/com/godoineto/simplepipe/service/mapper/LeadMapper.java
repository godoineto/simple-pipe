package com.godoineto.simplepipe.service.mapper;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.model.Lead;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = NoteMapper.class)
public interface LeadMapper {

    LeadDTO toDTO(Lead lead);

    Lead toEntity(LeadDTO leadDTO);
}
