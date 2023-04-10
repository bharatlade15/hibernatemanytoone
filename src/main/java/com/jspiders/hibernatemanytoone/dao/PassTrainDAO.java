package com.jspiders.hibernatemanytoone.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernatemanytoone.dto.PassengerDTO;
import com.jspiders.hibernatemanytoone.dto.TrainDTO;

public class PassTrainDAO {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void openConnection() {
		factory=Persistence.createEntityManagerFactory("manytoone");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
		
	}
	
	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	public static void main(String[] args) {
		try {
			
			openConnection();
			
			transaction.begin();
			
			TrainDTO trainDTO=new  TrainDTO();
			trainDTO.setId(1);
			trainDTO.setName("Chennai Express");
			trainDTO.setTrain_no(43551);
			manager.persist(trainDTO);
			
			PassengerDTO passenger1=new PassengerDTO();
			passenger1.setId(1);
			passenger1.setName("Nillamma");
			passenger1.setAge(20);
			passenger1.setSeat_no(10);
			passenger1.setTrainDTO(trainDTO);
			manager.persist(passenger1);
			
			PassengerDTO passenger2=new PassengerDTO();
			passenger2.setId(2);
			passenger2.setName("Minamma");
			passenger2.setAge(21);
			passenger2.setSeat_no(11);
			passenger2.setTrainDTO(trainDTO);
			manager.persist(passenger2);
			
			transaction.commit();
		} finally {
			closeConnection();
		}
	}
}
