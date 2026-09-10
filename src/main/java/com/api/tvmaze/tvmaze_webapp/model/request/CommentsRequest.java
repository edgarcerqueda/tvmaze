package com.api.tvmaze.tvmaze_webapp.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentsRequest {

    @NotBlank(message = "El comentario es obligatorio")
    private String comment;

    @NotNull(message = "El rating es obligatorio")
    @Min(value = 0, message = "El rating mínimo es 0")
    @Max(value = 5, message = "El rating máximo es 5")
    private Integer rating;
}
