package com.example.client.model;
public class Users 
{
    private int id;
    private String nom;
    private int age;

    public Users(int id, String nom,int age)
    {
        this.setId(id);
        this.setNom(nom);
        this.setAge(age);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
