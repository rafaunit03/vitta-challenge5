package br.com.rafael.model;

import java.util.List;
import javax.persistence.EntityManager;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class QuadradoModel{
	private String msg_erro;
	public QuadradoModel() { }
	
	public List<Quadrado> findAll() {	       
        try{        		              
        	EntityManager em = JpaConnection.getEntityManager();
        	List<Quadrado> lista = em.createQuery("from Quadrado").getResultList();
        	return lista;
        }catch(Exception e){
        	return null;        	
        }                
    }
	
	public Quadrado find(int cod_quadrado) {
		EntityManager em = JpaConnection.getEntityManager();
		Quadrado obj = null;
		try {
			obj = (Quadrado) em
					.createQuery(
							"from Quadrado where cod_quadrado= :cod_quadrado")
					.setParameter("cod_quadrado", cod_quadrado)
					.getSingleResult();
			return obj;			
		} catch (Exception e) {
		}
		return obj;
	}
	
	public boolean paint(int x, int y) {
		EntityManager em = JpaConnection.getEntityManager();
		try {
			List<Quadrado> lista = this.findAll();
			boolean ind_pintou_algum = false;
			for(Quadrado q : lista){
				if(q.between(x, q.getX_inicio(), q.getX_fim()) && q.between(y, q.getY_inicio(), q.getY_fim())){
					//this.paintSquare(q);
					ind_pintou_algum = true;
					QuadradoPintado qp = new QuadradoPintado();
					qp.setX(x);
					qp.setY(y);
					qp.setQuadrado(q);
					em.getTransaction().begin();
					em.persist(qp);
					em.getTransaction().commit();	
				}
			}
			return ind_pintou_algum;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	public List<QuadradoPintado> findQuadradosPintados(int cod_quadrado) {	       
        try{        		              
        	EntityManager em = JpaConnection.getEntityManager();
        	List<QuadradoPintado> lista = em.createQuery("from QuadradoPintado WHERE cod_quadrado="+cod_quadrado).getResultList();
        	return lista;
        }catch(Exception e){
        	return null;        	
        }                
    }	
		
	public boolean addMap(Quadrado quadrado){	
		List<Quadrado> lista = this.findAll();
		Quadrado qvalido = new Quadrado();
		if(!qvalido.ehQuadradoValido(quadrado)){
			this.setMsg_erro("Dados não e de um quadrado válido!");
			return false;
		}
		for(Quadrado q : lista){			
			if(q.sobrepoe(quadrado)){
				this.setMsg_erro("'territórios / território superposto': este novo território supera outro território.");
				return false;
			}
		}
		EntityManager em = JpaConnection.getEntityManager();
		try {		
			em.getTransaction().begin();
			em.persist(quadrado);
			em.getTransaction().commit();			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String removeSquare(int id) {
		EntityManager em = JpaConnection.getEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("DELETE FROM QuadradoPintado WHERE cod_quadrado="+id).executeUpdate();
			Quadrado d = em.find(Quadrado.class,id);
			em.remove(d);
			em.getTransaction().commit();
			return "{\"error\":\"false\"}";
		} catch (Exception e) {
			em.getTransaction().rollback();
			return "{\"error\":\"true\"}";
		}
	}
	
	public String getStrList(){
		List<Quadrado> lista = this.findAll();
		JSONObject objAll = new JSONObject();
		objAll.put("count",lista.size());		
    	for(Quadrado quadrado : lista){
    		JSONObject obj = new JSONObject();    		   		
    		obj.put("name",quadrado.getNome());
    		obj.put("id",quadrado.getCod_quadrado()); 
    		JSONObject objAux = new JSONObject();
    		objAux.put("x",quadrado.getX_inicio());
    		objAux.put("y",quadrado.getY_inicio());
    		obj.put("start",objAux);
    		objAux = new JSONObject();
    		objAux.put("x",quadrado.getX_fim());
    		objAux.put("y",quadrado.getY_fim());
    		obj.put("end",objAux);    		
    		obj.put("painted_area",this.findQuadradosPintados(quadrado.getCod_quadrado()).size());     		
    		List<QuadradoPintado> listaPintados = this.findQuadradosPintados(quadrado.getCod_quadrado());
    		for(QuadradoPintado qp : listaPintados){
    			objAux = new JSONObject();
    			objAux.put("x",qp.getX());
    			objAux.put("y",qp.getY());
    			obj.append("painted_squares",objAux);
    		}
    		objAll.append("data",obj);
    	}
		return objAll.toString();
	}
	
	public String getStrJson(Quadrado quadrado){
		int painted_area = this.findQuadradosPintados(quadrado.getCod_quadrado()).size();
		return "{\"data\": {\"id\": "+quadrado.getCod_quadrado()+","
				+ "\"name\": \""+quadrado.getNome()+"\","
				+ "\"start\": {\"x\": "+quadrado.getX_inicio()+", \"y\": "+quadrado.getY_inicio()+"},"
				+ "\"end\": {\"x\": "+quadrado.getX_fim()+", \"y\": "+quadrado.getY_fim()+"},"
				+ "\"area\": "+quadrado.getArea(quadrado)+",\"painted_area\": "+painted_area+"},"
				+ "\"error\":\"false\"}";
	}

	public String getMsg_erro() {
		return msg_erro;
	}

	public void setMsg_erro(String msg_erro) {
		this.msg_erro = msg_erro;
	}
	
}