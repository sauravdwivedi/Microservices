package com.dwivedi.cat.breeds.api.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Kitten {

    @ManyToOne
    private Breed breed;

    public String name;
    public UUID breed_id;

    public Kitten(String name, UUID breedId) {
        this.name = name;
        this.breed_id = breedId;
    }

}
