package com.huellazteca.api.domain.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huellazteca.api.models.ContactDetail;
import com.huellazteca.core.models.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListContactDetail extends BaseModel{
	
    @JsonProperty
    private List<ContactDetail> ContactDetail;

	

}
