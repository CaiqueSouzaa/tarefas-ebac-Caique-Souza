package br.com.csouza.mod33.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_acessorios")
public class Acessorio {
    public Acessorio() {
        this.carros = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acessorios_seq")
    @SequenceGenerator(name = "acessorios_seq", sequenceName = "sq_acessorios", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne()
    @JoinColumn(
            name = "marca_id",
            foreignKey = @ForeignKey(name = "fk_id_marca"),
            nullable = false
    )
    private Marca marca;

    @ManyToMany(mappedBy = "acessorios")
    private Set<Carro> carros;

    @Column(name = "actived", nullable = false)
    private Boolean actived;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Set<Carro> getCarros() {
        return carros;
    }

    public void setCarros(Set<Carro> carros) {
        this.carros = carros;
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
