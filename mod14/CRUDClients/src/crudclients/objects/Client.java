package crudclients.objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Objects;

/**
 * Objeto Client.
 * @author caique
 */
public class Client {
    private String name;
    private String surname;
    private String cpf;
    private String email;
    private String telephone;
    
    /**
     * Método construtor.
     */
    public Client() {}
    
    /**
     * Método construtor.
     */
    public Client(String name) {
        this.name = name;
    }
    
    /**
     * Método construtor.
     */
    public Client(String name, String surname) {
        this(name);
        this.surname = surname;
    }
    
    /**
     * Método construtor.
     */
    public Client(String name, String surname, String cpf) {
        this(name, surname);
        this.cpf = cpf;
    }
    
    /**
     * Método construtor.
     */
    public Client(String name, String surname, String cpf, String email) {
        this(name, surname, cpf);
        this.email = email;
    }
    
    /**
     * Método construtor.
     */
    public Client(String name, String surname, String cpf, String email, String telephone) {
        this(name, surname, cpf, email);
        this.telephone = telephone;
    }
    
    /**
     * Método para obter o nome do cliente.
     * @return String name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Método para obter o sobrenome do cliente.
     * @return String surname.
     */
    public String getSurname() {
        return this.surname;
    }
    
    /**
     * Método para obter o CPF do cliente.
     * @return String cpf.
     */
    public String getCPF() {
        return this.cpf;
    }
    
    /**
     * Método para obter o e-mail do cliente.
     * @return String email.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Método para obter o telefone do cliente.
     * @return String telephone.
     */
    public String getTelephone() {
        return this.telephone;   
    }
    
    /**
     * Método para definir o nome do cliente.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Método para definir o sobrenome do cliente.
     * @param surname 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * Método para definir o CPF do cliente.
     * @param cpf 
     */
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
    
    /**
     * Método para definir o e-mail do cliente.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Método para definir o telefone do cliente.
     * @param telephone 
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
        
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        final Client client = (Client) o;
        return Objects.equals(client.getCPF(), this.getCPF());
    }

//    @Override
//    public boolean equalss(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Client other = (Client) obj;
//        return Objects.equals(this.cpf, other.cpf);
//    }
    
    
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getCPF());
    }
    
    @Override
    public String toString() {
        return String.format(
                "Client {'name': '%s', 'surname': '%s', 'cpf': '%s', 'email': '%s', 'telephone': '%s'}",
                this.getName(), this.getSurname(), this.getCPF(), this.getEmail(), this.getTelephone());
    }
}
