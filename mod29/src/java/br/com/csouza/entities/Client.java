package br.com.csouza.entities;

import java.sql.Timestamp;
import java.util.Objects;

import br.com.csouza.annotations.TableName;

@TableName("tb_clients")
public class Client extends DatabaseEntity {
	private String name;
	private String surname;
	private String cpf;
	private String telephone;
	private int age;
	
	public Client() {}
	
	public Client(String name, String surname, String cpf, String telephone, int age) {
		this.name = name;
		this.surname = surname;
		this.cpf = cpf;
		this.telephone = telephone;
		this.age = age;
	}
	
	public Client(Long id, String name, String surname, String cpf, String telephone, int age, Timestamp createdAt) {
		super(id, createdAt);
		this.name = name;
		this.surname = surname;
		this.cpf = cpf;
		this.telephone = telephone;
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public Integer getAge() {
		return this.age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, telephone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(telephone, other.telephone);
	}
}