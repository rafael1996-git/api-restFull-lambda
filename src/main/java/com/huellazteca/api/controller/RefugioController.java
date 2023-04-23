package com.huellazteca.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.huellazteca.api.models.CasaResfuge;
import com.huellazteca.api.models.IdRegistro;
import com.huellazteca.api.models.RefugeModel;
import com.huellazteca.api.service.RefugioService;
import com.huellazteca.api.utils.SecurityUtils;
import com.huellazteca.core.controllers.BaseController;
import com.huellazteca.core.domain.request.Request;
import com.huellazteca.core.utils.ConstantsUtils;
import com.huellazteca.core.utils.JsonConstantsUtils;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET })
@EnableWebMvc
@RestController
@RequestMapping("/enrollment")
public class RefugioController extends BaseController {

	private RefugioService refugioService;

	@Autowired
	public RefugioController(RefugioService refugioService) {
		this.refugioService = refugioService;
	}

	@PostMapping(value = "/refuge", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object saveTuning(@RequestBody final Request request) {
		Object obj = SecurityUtils.decrypt(request.getData(), RefugeModel.class);

		System.out.println("Entro a controlador");
		if (obj == null) {
			obj = errorParsingRequest();
		}
		if (obj instanceof HashMap) {
			return obj;
		}
		final RefugeModel data = (RefugeModel) obj;

		return SecurityUtils.parseResponse(refugioService.saveRefugio(data), false);
	}

	@PostMapping(value = "/refuge-detail", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object refuge_detail(@RequestBody final Request request) {
		Object obj = SecurityUtils.decrypt(request.getData(), CasaResfuge.class);

		System.out.println("Entro a controlador refuge_detail");
		if (obj == null) {
			obj = errorParsingRequest();
		}
		if (obj instanceof HashMap) {
			return obj;
		}
		final CasaResfuge data = (CasaResfuge) obj;

		return SecurityUtils.parseResponse(refugioService.RefugioDetail(data), false);
	}
	
	
	@PostMapping(value = "/contact-detail", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object contact_detail(@RequestBody final Request request) {
		Object obj = SecurityUtils.decrypt(request.getData(), IdRegistro.class);

		System.out.println("Entro a controlador contact_detail");
		if (obj == null) {
			obj = errorParsingRequest();
		}
		if (obj instanceof HashMap) {
			return obj;
		}
		final IdRegistro data = (IdRegistro) obj;

		return SecurityUtils.parseResponse(refugioService.RefugioDetailContact(data), false);
	}

	@GetMapping(value = "/catalogue/activity")
	public Object getCatActivityRefuge() {
		return SecurityUtils.parseResponse(refugioService.getCatActivity(), false);
	}

	@Override
	@RequestMapping(ConstantsUtils.ENDPOINT_HEALTH_CHECK)
	public HashMap healthCheck() {
		return getStatus(true);
	}

	@Override
	public HashMap getStatus(boolean withDB) {
		HashMap map = new HashMap();
		map.put(JsonConstantsUtils.PROPERTY_NAME_SUCCESS, Boolean.TRUE);
		map.put(JsonConstantsUtils.PROPERTY_NAME_MESSAGE, ConstantsUtils.HEALTH_CHECK_OK);
		return map;
	}
}
