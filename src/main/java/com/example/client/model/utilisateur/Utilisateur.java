package com.example.client.model.utilisateur;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.model.terrain.*;
import org.springframework.stereotype.Component;

@Component
public class Utilisateur {
    String idUtilisateur;
    String nomUtilisateur;
    String email;
    String motDePasse;
    Date dateNaissance;
    String codeSecret;
    Double budget;
    List<Terrain> terrain;

    public Utilisateur(String idUtilisateur, String nomUtilisateur, String email, String motDePasse, Date dateNaissance, String codeSecret) {
        this.setIdUtilisateur(idUtilisateur);
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setMotDePasse (motDePasse);
        this.setDateNaissance(dateNaissance);
        this.setCodeSecret(codeSecret);
    }
    public Utilisateur(String email, String motDePasse) {
        this.setEmail(email);
        this.setMotDePasse (motDePasse);
    }
    public Utilisateur(String idUtilisateur) {
        this.setIdUtilisateur(idUtilisateur);
    }
    public Utilisateur() {
    }
    
    public String getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }
    public void setNomUtilisateur(String nomUtilisateur) {
        if(nomUtilisateur!=null && !nomUtilisateur.trim().equals("")) this.nomUtilisateur = nomUtilisateur.trim();
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if(email!=null && !email.trim().equals("") && email.contains("@")) this.email = email.trim();
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        if(motDePasse!=null && !motDePasse.trim().equals("")) this.motDePasse = motDePasse.trim();
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodeSecret() {
        return codeSecret;
    }
    public void setCodeSecret(String codeSecret) {
        if(codeSecret!=null && !codeSecret.trim().equals("")) this.codeSecret = codeSecret.trim();
    }

    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<Terrain> getTerrain() {
        return terrain;
    }
    public void setTerrain(List<Terrain> terrain) {
        this.terrain = terrain;
    }
    public void addTerrain(Terrain terrain) {
        if(this.getTerrain() == null) this.setTerrain( new ArrayList<>() );
        this.getTerrain().add(terrain);
    }
    
    public boolean isDeletable() {
        return this != null && this.getIdUtilisateur() != null && !this.getIdUtilisateur().isEmpty();
    }
    public boolean isUpdatable() {
        boolean response = this != null && this.getIdUtilisateur() != null && !this.getIdUtilisateur().isEmpty() && ( (this.getNomUtilisateur() != null && !this.getNomUtilisateur().isEmpty()) || (this.getMotDePasse() != null && !this.getMotDePasse().isEmpty()) || 
        (this.getCodeSecret() != null && !this.getCodeSecret().isEmpty()) );
        return response;
    }
    public boolean isLogin() {
        boolean response = this != null && this.getEmail() != null && !this.getEmail().isEmpty() && this.getMotDePasse() != null && !this.getMotDePasse().isEmpty();
        return response;
    }
    public boolean isAdmin() {
        boolean response = this != null && this.getEmail().equals("admin@gmail.com") && this.getMotDePasse().equals("123");
        return response;
    }
    public String getError() {
        String response = "";
        response = ((this.getNomUtilisateur() != null && !this.getNomUtilisateur().isEmpty()) || (this.getEmail() != null && !this.getEmail().isEmpty()) )? "":"*Username or email not found";
        response = (this.getMotDePasse() != null && !this.getMotDePasse().isEmpty())? response:response+" *Invalide Password";
        return response;
    }
    public boolean isInsertable() {
        boolean response = this != null && this.getNomUtilisateur() != null && !this.getNomUtilisateur().isEmpty() && this.getEmail() != null && !this.getEmail().isEmpty() && this.getMotDePasse() != null && !this.getMotDePasse().isEmpty() && this.getDateNaissance() != null && this.getCodeSecret() != null && !this.getCodeSecret().isEmpty();
        return response;
    }
}