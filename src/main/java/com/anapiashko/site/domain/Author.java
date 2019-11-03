package com.anapiashko.site.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "author")
@Data
public class Author {
    @Id
    private String id;

    private String name;

    private String email;

}
