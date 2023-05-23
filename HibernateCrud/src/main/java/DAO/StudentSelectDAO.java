package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DTO.StudentDTO;

public class StudentSelectDAO {

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

			StudentDTO student = manager.find(StudentDTO.class, 1);
			System.out.println("Student ID : " + student.getId());
			System.out.println("Name : " + student.getName());
			System.out.println("Address : " + student.getAddress());
			System.out.println("Salary : " + student.getSalary());

			transaction.commit();
		} finally {
			closeConnection();
		}

	}

}
