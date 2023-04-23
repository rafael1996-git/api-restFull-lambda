package com.huellazteca.api.dao.implement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huellazteca.api.dao.RefugioDao;
import com.huellazteca.api.domain.response.ListCatActivity;
import com.huellazteca.api.models.Activity;
import com.huellazteca.api.models.ActivityRefugeModel;
import com.huellazteca.api.models.CasaResfuge;
import com.huellazteca.api.models.ContactDetail;
import com.huellazteca.api.models.ContactRefugeModel;
import com.huellazteca.api.models.RefugeModel;
import com.huellazteca.api.security.Credentials;
import com.huellazteca.api.utils.Constantes;
import com.huellazteca.api.utils.ConstantesSQL;
import com.huellazteca.api.utils.Exceptions.HuellAztException;
import com.huellazteca.core.OracleDBPool;

import oracle.jdbc.OracleTypes;

@Repository
public class RefugioDaoImplement implements RefugioDao{

	
	private static final Logger log = LoggerFactory.getLogger(RefugioDaoImplement.class);

	static {
		try {
			if (Credentials.DB_CONFIG == null) {
				throw new HuellAztException(Constantes.FAILED_GET_DB_CONFIG,
						Constantes.CODIGO_FAILED_GET_DB_CONFIG );
			}

			OracleDBPool.initSingletonConnectionCredentials(Credentials.DB_CONFIG.getUrl(),
					Credentials.DB_CONFIG.getUser(),
					Credentials.DB_CONFIG.getPass());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public Long saveRefugio(RefugeModel data) throws Exception {
		CallableStatement cs = null;
		try {

			Connection conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.INSERT_REFUGIO_REGISTRATION);
			cs = conn.prepareCall(ConstantesSQL.INSERT_REFUGIO_REGISTRATION);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setLong(2, data.getIdUser());
			cs.setLong(3, data.getIdTypeRefuge());
			cs.setString(4,data.getName());
			cs.setString(5, data.getCraeationDate());
			cs.setString(6, data.getPhoto());
			cs.setString(7, data.getPhone());
			cs.setString(8, data.getEmail());
			cs.setString(9, data.getAddress());
			cs.setLong(10, data.getIsAddressConfidential()? 1 : 0);
			cs.setLong(11, data.getIsAuthorizedDonor()? 1 : 0);
			cs.setString(12, data.getConstitutiveAct());
			cs.setString(13, data.getUrlDocument());
			cs.setLong(14, data.getIsSameContact()? 1 : 0);
			cs.execute();
			return cs.getLong(1);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(new StringBuilder("RefugioDaoImpl.saveRefugio , ").append(e.getMessage()));
			throw new Exception(e.getMessage());
		} finally {
			if (cs != null) { try { cs.close(); } catch (Exception e) {} }
		}
	}

	@Override
	public Long saveActivityRefuge(ActivityRefugeModel data) throws Exception {
		CallableStatement cs = null;
		try {
			Connection conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.INSERT_REFUGIO_ACTIVITIES);
			cs = conn.prepareCall(ConstantesSQL.INSERT_REFUGIO_ACTIVITIES);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setLong(2, data.getIdRefuge());
			cs.setLong(3, data.getIdActivity());
			cs.execute();
			return cs.getLong(1);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(new StringBuilder("RefugioDaoImpl.saveActivityRefuge , ").append(e.getMessage()));
			throw new Exception(e.getMessage());
		} finally {
			if (cs != null) { try { cs.close(); } catch (Exception e) {} }
		}
	}

	@Override
	public Long saveContacRefuge(ContactRefugeModel data) throws Exception {
		CallableStatement cs = null;
		try {
			Connection conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.INSERT_REFUGIO_CONTACTS);
			cs = conn.prepareCall(ConstantesSQL.INSERT_REFUGIO_CONTACTS);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setString(2, data.getName());
			cs.setString(3, data.getBirthDate());
			cs.setString(4, data.getEmail());
			cs.setString(5, data.getPhone());
			cs.setString(6, data.getUrlProfilePhoto());
			cs.setLong(7, data.getIdRefuge());
			cs.setLong(8, data.getIdCatTypeContact());
			cs.execute();
			return cs.getLong(1);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(new StringBuilder("RefugioDaoImpl.saveContacRefuge , ").append(e.getMessage()));
			throw new Exception(e.getMessage());
		} finally {
			if (cs != null) { try { cs.close(); } catch (Exception e) {} }
		}
	}
	
	 @Override
	    public CasaResfuge RefugioDetail(Long dataId) throws SQLException {
		 CasaResfuge listado =new CasaResfuge();	        
			Connection conn =null;
			CallableStatement ps =null;
	        try {
	        	conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.REFUGIO_GET);
	        	ps = conn.prepareCall(ConstantesSQL.REFUGIO_GET);
	        	ps.registerOutParameter(1, OracleTypes.CURSOR);
	        	ps.setLong(2, dataId);
	        	ps.execute();
	            ResultSet rs = (ResultSet) ps.getObject(1);

	            if(rs != null) {
	            	
	                while (rs.next()) {
                    Date fechaActual = new Date();
	                    //Calcular la diferencia entre las dos fechas en milisegundos
	                    long diferenciaEnMilisegundos = fechaActual.getTime() - rs.getDate(5).getTime();
//	                    //Calcular la diferencia en segundos
	                    long diferenciaEnSegundos = diferenciaEnMilisegundos / 1000;
//	                    //Calcular la diferencia en minutos
	                    long diferenciaEnMinutos = diferenciaEnSegundos / 60;
//	                    //Calcular la diferencia en horas
	                    long diferenciaEnHoras = diferenciaEnMinutos / 60;
//	                    //Calcular la diferencia en dias
	                    long diferenciaEnDias = diferenciaEnHoras / 24;
//	                    //Calcular la diferencia en meses
	                    long diferenciaEnMeses = diferenciaEnDias / 30;
//	                    //Calcular la diferencia en años
	                    long diferenciaEnAños = diferenciaEnMeses / 12;
	                	String antiguedad=null;

	                    //Imprimir los resultados
		            	if (diferenciaEnAños>0) {
							antiguedad=diferenciaEnAños+" año";
							
						}else if(diferenciaEnMeses>0) {
							antiguedad=diferenciaEnMeses+" mes";
							
						}
	                	listado.setActivity(rs.getString(7));
	                	listado.setAntiquity(antiguedad);
	                	listado.setContact(rs.getString(1));
	                	listado.setEmail(rs.getString(3));
	                	listado.setDudasEmailContact("fundacion_azteca@gmail.com");
	                	listado.setLocation(rs.getString(2));
	                	listado.setNumTotal_cat(rs.getInt(9));
	                	listado.setNumTotal_dog(rs.getInt(10));
	                	listado.setPhone(rs.getInt(4));
	                	listado.setRecibe_huellitas(false);
	                	listado.setRepresentative(rs.getString(6));
	                	listado.setIdUser(rs.getLong(8));
	                	listado.setIdRefuge(rs.getLong(11));
	                }

	                rs.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error("RefugioDaoImpl.RefugioDetail: ",e.getMessage());
	        }finally {
				if (ps != null) { try { ps.close(); } catch (Exception e) {} }
			}

	        return listado;
	    }
	 @Override
		public ListCatActivity getCatActivity() throws Exception {
			CallableStatement cs =null;
			ResultSet rs = null;
			List<Activity> activities = new ArrayList<>();
			try {
				Connection conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.GET_CAT_ACTIVITY_REFUGE);
				cs = conn.prepareCall(ConstantesSQL.GET_CAT_ACTIVITY_REFUGE);
				cs.registerOutParameter(1, OracleTypes.CURSOR);
				cs.execute();
				rs = (ResultSet) cs.getObject(1);
				while (rs.next()) {
					activities.add(new Activity(
							rs.getInt("FIIDACTIVITYREFUGE"),
							rs.getString("FCNAME")
					));
				}
				return new ListCatActivity(activities);
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("Error method RefugioDaoImpl.getCatActivity: " + e.getMessage());
				throw new Exception(e.getMessage());
			}finally {
				if (cs != null) { try { cs.close(); } catch (Exception e) {} }
				if (rs!= null) { try { rs.close(); } catch (Exception e) {}}
			}
		}

	@Override
	public ContactDetail RefugioContactDetail(Long dataId) throws Exception {
		ContactDetail listado =new ContactDetail();	        
		Connection conn =null;
		CallableStatement ps =null;
        try {
        	conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.REFUGIOCONTACTDETAIL_GET);
        	ps = conn.prepareCall(ConstantesSQL.REFUGIOCONTACTDETAIL_GET);
        	ps.registerOutParameter(1, OracleTypes.CURSOR);
        	ps.setLong(2, dataId);
        	ps.execute();
            ResultSet rs = (ResultSet) ps.getObject(1);
            if(rs != null) {
                while (rs.next()) {
                	listado.setName(rs.getString(1));
                	listado.setContact(rs.getString(2));
                	listado.setFecha(rs.getDate(3));
                	listado.setEmail(rs.getString(4));
                	listado.setTelefono(rs.getString(5));
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ServiceRepository.InfoPretender: ",e.getMessage());
        }finally {
			if (ps != null) { try { ps.close(); } catch (Exception e) {} }
		}

        return listado;
	}
	

}
