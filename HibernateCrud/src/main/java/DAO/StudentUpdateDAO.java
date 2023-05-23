package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DTO.StudentDTO;

public class StudentUpdateDAO {

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
			student.setAddress("India");
			manager.persist(student);
			
			StudentDTO student1 = manager.find(StudentDTO.class, 1);
			System.out.println("Student ID : " + student1.getId());
			System.out.println("Name : " + student1.getName());
			System.out.println("Address : " + student1.getAddress());
			System.out.println("Salary : " + student1.getSalary());

			transaction.commit();
		} finally {
			closeConnection();
		}

	}

}
