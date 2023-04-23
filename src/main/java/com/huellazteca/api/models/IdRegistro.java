package com.huellazteca.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huellazteca.core.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdRegistro extends BaseModel {
    @JsonProperty
    private Long idRegistro;
}
