package com.huellazteca.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huellazteca.core.models.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRefugio extends BaseModel{
	
	@JsonProperty
	    private List<CasaResfuge> informacion;
}
