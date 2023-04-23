package com.huellazteca.api.dao;

import com.huellazteca.api.domain.response.ListCatActivity;
import com.huellazteca.api.models.ActivityRefugeModel;
import com.huellazteca.api.models.CasaResfuge;
import com.huellazteca.api.models.ContactDetail;
import com.huellazteca.api.models.ContactRefugeModel;
import com.huellazteca.api.models.RefugeModel;

public interface RefugioDao {

	Long saveRefugio(RefugeModel data) throws Exception;
	
	Long saveActivityRefuge(ActivityRefugeModel data) throws Exception;

	Long saveContacRefuge(ContactRefugeModel data) throws Exception;
	
	CasaResfuge RefugioDetail(Long dataId) throws Exception;
	
	ContactDetail RefugioContactDetail(Long dataId) throws Exception;
	
	ListCatActivity getCatActivity() throws Exception;

}
