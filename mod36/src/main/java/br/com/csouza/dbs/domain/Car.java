package br.com.csouza.dbs.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cars")
public class Car extends DatabaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_seq")
	@SequenceGenerator(name = "cars_seq", sequenceName = "sq_cars", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "manufacturer", nullable = false)
	private String manufacturer;
	
	@ManyToOne()
	@JoinColumn(
			name = "user_id",
			foreignKey = @ForeignKey(name = "fk_id_users"),
			nullable = false
	)
	private User user;
	
	@Column(name = "actived", nullable = false)
	private boolean actived;
	
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;
	
	@PrePersist
	private void prePersist() {
		this.actived = true;
		this.createdAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
}
