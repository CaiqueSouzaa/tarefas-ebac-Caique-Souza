package br.com.csouza.mod33.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "tb_marcas")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marcas_seq")
    @SequenceGenerator(name = "marcas_seq", sequenceName = "sq_marcas", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @OneToMany(mappedBy = "marca")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Set<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }
}
