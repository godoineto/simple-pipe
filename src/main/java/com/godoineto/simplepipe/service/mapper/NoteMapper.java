package com.godoineto.simplepipe.service.mapper;

import com.godoineto.simplepipe.api.dto.NoteDTO;
import com.godoineto.simplepipe.model.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDTO toDTO(Note note);

    Note toEntity(NoteDTO noteDTO);
}
