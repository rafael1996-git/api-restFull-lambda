package com.huellazteca.api.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CasaResfuge {
	
	@JsonProperty
	private Long  idUser;
	
	@JsonProperty
	private String email;
	@JsonProperty
	private String activity;
	@JsonProperty
	private int phone;
	@JsonProperty
	private String location;
	@JsonProperty
	private String contact;
	@JsonProperty
	private boolean recibe_huellitas;
	@JsonProperty
	private int  numTotal_cat;
	@JsonProperty
	private int numTotal_dog;
	@JsonProperty
	private String antiquity;
	@JsonProperty
	private String representative;
	@JsonProperty
	private String dudasEmailContact;
	@JsonProperty
	private Long IdRefuge;
}
