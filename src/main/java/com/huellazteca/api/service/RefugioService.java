package com.huellazteca.api.service;

import com.huellazteca.api.models.CasaResfuge;
import com.huellazteca.api.models.IdRegistro;
import com.huellazteca.api.models.RefugeModel;
import com.huellazteca.core.models.ResponseModel;

public interface RefugioService {

	ResponseModel saveRefugio(RefugeModel data);
	ResponseModel RefugioDetail(CasaResfuge data);
	ResponseModel RefugioDetailContact(IdRegistro data);
	ResponseModel<Object> getCatActivity();
}
