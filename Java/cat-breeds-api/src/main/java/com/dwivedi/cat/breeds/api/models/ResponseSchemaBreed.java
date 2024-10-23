package com.dwivedi.cat.breeds.api.models;

import java.util.List;
import java.util.UUID;

public record ResponseSchemaBreed(UUID id, String breed, String origin, String type, String type_info,
        List<String> body_types, String coat_type_and_length, String coat_pattern) {

}