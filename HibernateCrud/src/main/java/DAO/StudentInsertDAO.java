package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DTO.StudentDTO;

public class StudentInsertDAO {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("hibernConfig");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
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

			StudentDTO student1 = new StudentDTO();
			student1.setId(1);
			student1.setName("Naruto");
			student1.setAddress("Japan");
			student1.setSalary(100.00);

			manager.persist(student1);

			StudentDTO student2 = new StudentDTO();
			student2.setId(2);
			student2.setName("Sasuke");
			student2.setAddress("Japan");
			student2.setSalary(90.00);

			manager.persist(student2);

			transaction.commit();
		} finally {
			closeConnection();
		}

	}

}
