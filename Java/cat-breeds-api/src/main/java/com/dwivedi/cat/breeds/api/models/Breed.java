package com.dwivedi.cat.breeds.api.models;

import java.util.UUID;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Breed {
    @OneToMany(mappedBy = "breed")
    private Collection<Kitten> kittens;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String breed;
    public String origin;
    public String type;
    public String type_info;
    public List<String> body_types;
    public String coat_type_and_length;
    public String coat_pattern;

    public Breed() {
    }

}
