package com.huellazteca.api.utils;

public class ConstantesSQL {

	public static final int DB_TIMEOUT = 7;

	public static final String INSERT_REFUGIO_REGISTRATION = "{? = call  HUELLITAS.PAUSER.FNINSERTREFUGEREGISTRATION(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	public static final String INSERT_REFUGIO_ACTIVITIES = "{? = call  HUELLITAS.PAUSER.FNINSERTACTIVITYREFUGE(?,?)}";

	public static final String INSERT_REFUGIO_CONTACTS = "{? = call  HUELLITAS.PAUSER.FNINSERTCONTACTREFUGE(?,?,?,?,?,?,?)}";
	
	public static final String REFUGIO_GET = "{? = call  HUELLITAS.PAUSER.FNGETREFUGE(?)}";
	
	public static final String REFUGIOCONTACTDETAIL_GET = "{? = call  HUELLITAS.PAUSER.FNGETREFUGECONTACTDETAIL(?)}";
	
	public static final String GET_CAT_ACTIVITY_REFUGE = "{? = call  HUELLITAS.PAUSER.FNGETCATACTIVITYREFUGE()}";


}
