package com.portfolio.sso.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cities", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class City {

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=50)
    @NotBlank
    private String name;

    public City() {

    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
