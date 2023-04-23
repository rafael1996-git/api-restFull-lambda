package com.huellazteca.api.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huellazteca.api.dao.RefugioDao;
import com.huellazteca.api.domain.response.ListCatActivity;
import com.huellazteca.api.domain.response.ListContactDetail;
import com.huellazteca.api.models.ActivityRefugeModel;
import com.huellazteca.api.models.CasaResfuge;
import com.huellazteca.api.models.ContactDetail;
import com.huellazteca.api.models.IdRegistro;
import com.huellazteca.api.models.ListRefugio;
import com.huellazteca.api.models.RefugeModel;
import com.huellazteca.api.service.RefugioService;
import com.huellazteca.core.OracleDBPool;
import com.huellazteca.core.enums.ResponseEnum;
import com.huellazteca.core.models.ResponseModel;
import com.huellazteca.core.utils.ResponseUtils;

@Service
public class RefugioServiceImpl implements RefugioService{

	
	private static final Logger log = LoggerFactory.getLogger(RefugioServiceImpl.class);

	@Autowired
	private RefugioDao refugioDao;

	@Override
	public ResponseModel saveRefugio(RefugeModel data) {
		ResponseModel response;
		try {
			// Guardar refugio
			System.out.println("Guardar refugio");
			Long idRegistro = refugioDao.saveRefugio(data);

			// Guardar actividades
			System.out.println("Guardar actividades");
			ActivityRefugeModel activity = new ActivityRefugeModel();
			activity.setIdRefuge(idRegistro);
			data.getActivities().forEach(a->{
				activity.setIdActivity(a);
				try {
					refugioDao.saveActivityRefuge(activity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			// Guardar contactos
			System.out.println("Guardar contactos");
			data.getContacts().forEach(c->{
				c.setIdRefuge(idRegistro);
				try {
					refugioDao.saveContacRefuge(c);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			response = ResponseUtils.createResponse(new IdRegistro(idRegistro),ResponseEnum.EXITO);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(new StringBuilder("UserServiceImpl.saveInfoPersonal  - : ").append(e.getMessage()).toString());
			response = ResponseUtils.createResponseWithMessage(null, ResponseEnum.ERROR,e.getMessage());
		} finally {
			OracleDBPool.closeSingletonConnection();
		}
		return response;
	}

	@Override
	public ResponseModel RefugioDetail(CasaResfuge data) {
		ResponseModel response;
		try {
			// Get listado refugio
			System.out.println("detalle refugio");
			CasaResfuge  dataUser = refugioDao.RefugioDetail(data.getIdUser());

			// procesar listado
			CasaResfuge user=new CasaResfuge();
			List<CasaResfuge> list = new ArrayList<CasaResfuge>();
			
			user.setActivity(dataUser.getActivity());
			user.setAntiquity(dataUser.getAntiquity());
			user.setContact(dataUser.getContact());
			user.setEmail(dataUser.getEmail());
			user.setDudasEmailContact(dataUser.getDudasEmailContact());
			user.setIdUser(dataUser.getIdUser());
			user.setLocation(dataUser.getLocation());
			user.setNumTotal_cat(dataUser.getNumTotal_cat());
			user.setNumTotal_dog(dataUser.getNumTotal_dog());
			user.setPhone(dataUser.getPhone());
			user.setRecibe_huellitas(true);
			user.setRepresentative(dataUser.getRepresentative());
			
			ListRefugio information=new ListRefugio();
			list.add(user);
			information.setInformacion(list);
			// return listado
			System.out.println("retorna listado");
			response = ResponseUtils.createResponse(Collections.singletonList(information.toJsonObject()),ResponseEnum.EXITO);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("UserServiceImpl.RefugioDetail: "+e.getMessage());
			System.out.println(new StringBuilder("UserServiceImpl.RefugioDetail  - : ").append(e.getMessage()).toString());
			response = ResponseUtils.createResponseWithMessage(null, ResponseEnum.ERROR,e.getMessage());
		} finally {
			OracleDBPool.closeSingletonConnection();
		}
		return response;
	}
	
	@Override
	public ResponseModel<Object> getCatActivity() {
		ResponseModel response;
		try {
			ListCatActivity listCatActivity = refugioDao.getCatActivity();
			response = ResponseUtils.createResponse(listCatActivity,ResponseEnum.EXITO);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error method UserServiceImpl.RefugioDetail: "+e.getMessage());
			response = ResponseUtils.createResponseWithMessage(null, ResponseEnum.ERROR,e.getMessage());
		}finally {
			OracleDBPool.closeSingletonConnection();
		}
		return response;
	}

	@Override
	public ResponseModel RefugioDetailContact(IdRegistro data) {
		ResponseModel response;
		try {
			// Get listado de pretendientes
			System.out.println("lista de detlle de contacto");
			ContactDetail info=new ContactDetail();
			List<ContactDetail> list = new ArrayList<ContactDetail>();
			ListContactDetail information=new ListContactDetail();
			ContactDetail  dataUser = refugioDao.RefugioContactDetail(data.getIdRegistro());
			dataUser.setName(dataUser.getName());
			int edad= calcularEdad(dataUser.getFecha());
			dataUser.setEdad(edad);
			dataUser.setEmail(dataUser.getEmail());
			dataUser.setContact(dataUser.getContact());;
			dataUser.setTelefono(dataUser.getTelefono());
			
			list.add(dataUser);
			information.setContactDetail(list);
			// return listado
			System.out.println("retorna listado");
            response = ResponseUtils.createResponse(Collections.singletonList(information.toJsonObject()),ResponseEnum.EXITO);
		}catch (Exception e) {
			log.error("UserServiceImpl.GetList: ",e);
			System.out.println(new StringBuilder("UserServiceImpl.GetList  - : ").append(e.getMessage()).toString());
			response = ResponseUtils.createResponseWithMessage(null, ResponseEnum.ERROR,e.getMessage());
		} finally {
			OracleDBPool.closeSingletonConnection();
		}
		return response;
	}


	public static int calcularEdad(java.sql.Date fechaNacimiento) {
	    Calendar fechaActual = Calendar.getInstance();
	    Calendar fechaNac = Calendar.getInstance();
	    fechaNac.setTime(fechaNacimiento);
	    int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
	    if (fechaActual.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH)) {
	        edad--;
	    } else if (fechaActual.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH) && fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH)) {
	        edad--;
	    }
	    return edad;
	}
	

}
