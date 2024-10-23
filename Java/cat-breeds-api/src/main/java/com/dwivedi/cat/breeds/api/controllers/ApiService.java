package com.dwivedi.cat.breeds.api.controllers;

import java.util.UUID;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dwivedi.cat.breeds.api.models.Breed;
import com.dwivedi.cat.breeds.api.models.Kitten;
import com.dwivedi.cat.breeds.api.models.ResponseSchemaKitten;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/v1")
public class ApiService {

    public List<Breed> getBreedsFromJsonFile() throws IOException {
        // read JSON and load json
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Breed>> typeReference = new TypeReference<List<Breed>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/breeds.json");

        try {
            List<Breed> breeds = mapper.readValue(inputStream, typeReference);

            return breeds;

        } catch (IOException e) {
            System.out.println("Unable to get breeds: " + e.getMessage());

            return null;
        }
    }

    public void writeKittenToJsonFile(Kitten newKitten) throws IOException {
        // write kitten object to json
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("src/main/resources/kittens.json"), newKitten);

        } catch (IOException e) {
            System.out.println("Unable to save kittens in file: " + e.getMessage());

            throw e;
        }
    }

    @GetMapping("/breeds/")
    public @ResponseBody List<Breed> getAllBreeds() throws IOException {

        return getBreedsFromJsonFile();
    }

    @GetMapping("/breeds/{id}")
    public @ResponseBody Breed getBreedById(@PathVariable UUID id) throws IOException {

        Breed response = getBreedsFromJsonFile().stream()
                .filter(breed -> id.equals(breed.id))
                .findFirst()
                .orElse(null);

        return response;
    }

    @GetMapping("/breeds")
    public @ResponseBody List<Breed> getBreedByAttribute(@RequestParam(required = false) String breed,
            @RequestParam(required = false) String origin)
            throws IOException {

        if (breed != null && origin != null) {
            return getBreedsFromJsonFile().stream()
                    .filter(eachBreed -> breed.equals(eachBreed.breed))
                    .filter(eachBreed -> origin.equals(eachBreed.origin))
                    .collect(Collectors.toList());
        }

        if (breed != null && origin == null) {
            return getBreedsFromJsonFile().stream()
                    .filter(eachBreed -> breed.equals(eachBreed.breed))
                    .collect(Collectors.toList());
        }

        if (breed == null && origin != null) {
            return getBreedsFromJsonFile().stream()
                    .filter(eachBreed -> origin.equals(eachBreed.origin))
                    .collect(Collectors.toList());
        }

        return getBreedsFromJsonFile();
    }

    @PostMapping("/kittens")
    public @ResponseBody ResponseSchemaKitten createKitten(@RequestBody Kitten payload) throws IOException {
        Breed matchingBreed = getBreedsFromJsonFile().stream()
                .filter(breed -> payload.breed_id.equals(breed.id))
                .findFirst()
                .orElse(null);

        if (matchingBreed != null) {
            Kitten newKitten = new Kitten(payload.name, payload.breed_id);

            try {
                writeKittenToJsonFile(newKitten);

            } catch (IOException e) {
                System.out.println("Unable to save kittens in file: " + e.getMessage());

                return new ResponseSchemaKitten("An internal server error occurred", 500);
            }

            return new ResponseSchemaKitten("Breed found, new kitten created", 201);
        }

        return new ResponseSchemaKitten("No such breed", 400);
    }
}