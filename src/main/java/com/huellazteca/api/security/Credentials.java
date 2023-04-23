package com.huellazteca.api.security;

import com.huellazteca.core.utils.EnvironmentData;
import com.huellazteca.core.utils.GsonParserUtils;
import com.huellazteca.core.utils.SecretManagerAWSUtils;
public class Credentials {
	public static final DBConfig DB_CONFIG = initCredentials("OraCredKey", DBConfig.class);
	//public static final DBConfig DB_CONFIG = new DBConfig();
	
	public static final AztKeys ENCRYPTION_KEYS = initCredentials2("EncryptionKey", AztKeys.class);
	//public static final AztKeys ENCRYPTION_KEYS  = new AztKeys();
	
	private static <TType> TType initCredentials(String keyName, Class<TType> clasType)  {
		try {
			String encryption = SecretManagerAWSUtils.getParameter("com/upax/huella-azteca/db/oracle/usrhuellitas");

			return GsonParserUtils.getGson().fromJson(encryption, clasType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private static <TType> TType initCredentials2(String keyName, Class<TType> clasType)  {
		try {
			String encryption = SecretManagerAWSUtils.getParameter("com/upax/huella-azteca/security/encryption");

			return GsonParserUtils.getGson().fromJson(encryption, clasType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
