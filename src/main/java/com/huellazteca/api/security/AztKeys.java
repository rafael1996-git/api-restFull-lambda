package com.huellazteca.api.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class AztKeys implements Serializable {

	private static final long serialVersionUID = 1L;
	//	private String mainKey = "HuLlAT3ncryPt.K3Y.AztK3ncryPtEd+";
	//	private String pwdKey = "HuLlAT3ncryPt.K3Y.AztK3ncryPtEd+";

	private String mainKey;
	private String pwdKey;
}
