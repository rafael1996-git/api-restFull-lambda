package com.huellazteca.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huellazteca.core.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRefugeModel extends BaseModel{

	@JsonProperty
	private Long idContact;

	@JsonProperty
	private String name;

	@JsonProperty
	private String birthDate;

	@JsonProperty
	private String email;

	@JsonProperty
	private String phone;

	@JsonProperty
	private String urlProfilePhoto;

	@JsonProperty
	private Long idRefuge;

	@JsonProperty
	private Long idCatTypeContact;

}
