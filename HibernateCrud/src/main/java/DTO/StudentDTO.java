package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Student")
public class StudentDTO {
	
	@Id
	@Column(name = "StudentId")
	private int id;
	private String Name;
	private Double Salary;
	private String Address;

}
