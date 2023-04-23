package com.huellazteca.api.domain.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huellazteca.api.models.Activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCatActivity {
    @JsonProperty
    private List<Activity> activities;
}
