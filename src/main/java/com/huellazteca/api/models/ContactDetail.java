package com.huellazteca.api.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetail {
	
	@JsonProperty
	private String name;
	@JsonProperty
	private String Contact;
	@JsonIgnore
	private Date fecha;
	@JsonProperty("edad")
	@SerializedName("edad")
	private int edad;
	@JsonProperty
	private String email;
	@JsonProperty
	private String telefono;
	

}
