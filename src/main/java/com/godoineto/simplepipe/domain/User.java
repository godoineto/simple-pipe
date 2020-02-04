package com.godoineto.simplepipe.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
}
