package com.godoineto.simplepipe.service.mapper;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.domain.Lead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = NoteMapper.class)
public interface LeadMapper {

    @Mapping(target = "responsibleId", source = "responsible.id")
    LeadDTO toDTO(Lead lead);

    @Mapping(target = "responsible", ignore = true)
    Lead toEntity(LeadDTO leadDTO);
}
