package com.huellazteca.api.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class DBConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	//	private String url = "jdbc:oracle:thin:@SANDBOX-DEV-ORACLE.crmfzgvtcfyy.us-east-1.rds.amazonaws.com:1521:SANDBDEV";
	//	private String user = "TSTHUELLITAS";
	//	private String pass = "TsTHU3l1iTAa52220";

	private String url;
	private String user;
	private String pass;
}
