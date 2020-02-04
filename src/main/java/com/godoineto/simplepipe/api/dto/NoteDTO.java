package com.godoineto.simplepipe.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NoteDTO {
    @NotNull(message = "{note.text.invalid}")
    private String text;
}
