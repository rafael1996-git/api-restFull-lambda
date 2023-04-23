package com.huellazteca.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huellazteca.core.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefugeModel extends BaseModel {

	@JsonProperty
	private Long idRefuge;

	@JsonProperty
	private Long idUser;

	@JsonProperty
	private String address;

	@JsonProperty
	private String craeationDate;

	@JsonProperty
	private String name;

	@JsonProperty
	private String photographyContact;

	@JsonProperty
	private String phone;

	@JsonProperty
	private String receivePet;

	@JsonProperty
	private String qr;

	@JsonProperty
	private String logo;

	@JsonProperty
	private String photo;

	@JsonProperty
	private String email;

	@JsonProperty
	private String donee;

	@JsonProperty
	private Boolean isSameContact;
	
	@JsonProperty
	private Boolean isAddressConfidential;
	
	@JsonProperty
	private Boolean isAuthorizedDonor;
	
	@JsonProperty
	private String urlDocument;

	@JsonProperty
	private String constitutiveAct;
	
	@JsonProperty
	private Integer idTypeRefuge;
 
	@JsonProperty
	private Integer idStatusRequest;

	@JsonProperty
	private List<Long> activities;

	@JsonProperty
	private List<ContactRefugeModel> contacts;

}
