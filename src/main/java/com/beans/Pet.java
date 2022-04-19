package com.beans;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "pet aminal")
public class Pet {
    @Schema(description = "the  pet id")
    String id;
    @Schema(description = "name pet animal")
    String name;
}