package br.com.csouza.entities;

import java.sql.Timestamp;
import java.util.Objects;

import br.com.csouza.annotations.TableName;

@TableName("tb_products")
public class Product extends DatabaseEntity {
	private String code;
	private String name;
	private String description;
	
	public Product() {}
	
	public Product(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public Product(Long id, String code, String name, String description) {
		super(id);
		this.code = code;
		this.name = name;
		this.description = description;
	}
	
	public Product(Long id, String code, String name, String description, Timestamp createdAt) {
		super(id, createdAt);
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(code, other.code);
	}
}
