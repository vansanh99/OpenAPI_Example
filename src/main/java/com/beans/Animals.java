package com.beans;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Animals<T> {
    @Schema(description = "id of animal")
    String id;
    T animal;
}
