package br.com.csouza.mod33.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_carros")
public class Carro {
    public Carro() {
        this.acessorios = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carros_seq")
    @SequenceGenerator(name = "carros_seq", sequenceName = "sq_carros", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "model", nullable = false, length = 200)
    private String model;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @ManyToMany()
    @JoinTable(
        name = "tb_carros_acessorios",
            joinColumns = {
                    @JoinColumn(
                            name = "carro_id",
                            foreignKey = @ForeignKey(name = "fk_id_carros"),
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "acessorio_id",
                            foreignKey = @ForeignKey(name = "fk_id_acessorios"),
                            nullable = false
                    )
            }
    )
    private Set<Acessorio> acessorios;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Set<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }

    public void addAcessorio(Acessorio acessorio) {
        this.acessorios.add(acessorio);
    }
}
