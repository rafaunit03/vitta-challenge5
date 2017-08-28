package br.com.rafael.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConnection {
	private static EntityManager em;
	
	private JpaConnection(){}
	
	public static EntityManager getEntityManager(){
		if(em == null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("banco");
			em = emf.createEntityManager();
		}
		return em;
	}
}