package br.com.csouza.abstracts;

import java.util.Objects;

/**
 * Classe responsável por garantir que todos os veículos possuam um código.
 * @author Caique Souza
 * @version 1.0
 */
public abstract class Persistence {
    private long code;

    public long getCode() {
        return this.code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Persistence p = (Persistence) o;
        return Objects.equals(p.getCode(), this.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getCode());
    }
}
