package br.com.csouza;

import java.util.*;

public class Grupos {
    private Set<Pessoa> masculino = new HashSet<>();
    private Set<Pessoa> feminino = new HashSet<>();
    private Map<String, Set<Pessoa>> grupos = new HashMap<>();

    public Grupos() {
        this.grupos.put("masculino", this.masculino);
        this.grupos.put("feminino", this.feminino);
    }

    public void add(Pessoa pessoa) {
        Character pessoaSex = pessoa.getSexChar();
        if (pessoaSex.equals('M')) {
            this.grupos.get("masculino").add(pessoa);
        } else if (pessoaSex.equals('F')) {
            this.grupos.get("feminino").add(pessoa);
        }
    }

    public void addAll(Set<Pessoa> pessoas) {
        for (Pessoa p : pessoas) {
            this.add(p);
        }
    }

    public Map<String, Set<Pessoa>> getGrupos() {
        return this.grupos;
    }
}
