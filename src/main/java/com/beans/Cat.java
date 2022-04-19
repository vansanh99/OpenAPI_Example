package com.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "cat aminal")
public class Cat {
    @NotBlank
    @Size(min = 0, max = 20)
    @Schema(description = "id animal Cat")
    String id;
    @Schema(description = "name animal Cat")
    String nameCat;
}