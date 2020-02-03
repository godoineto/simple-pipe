package com.godoineto.simplepipe.integration.pipedrive.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultDTO {
    private boolean success;
    private DataDTO data;

    public Integer getId() {
        return data.getId();
    }

    @Data
    private static class DataDTO {
        private Integer id;
    }
}
