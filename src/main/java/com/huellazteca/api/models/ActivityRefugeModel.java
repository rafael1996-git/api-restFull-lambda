package com.huellazteca.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huellazteca.core.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRefugeModel extends BaseModel{

	@JsonProperty
	private Long idActivityRefuge;

	@JsonProperty
	private Long idRefuge;

	@JsonProperty
	private Long idActivity;
}
