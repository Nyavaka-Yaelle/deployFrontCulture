package com.example.client.model.utilisateur;
import com.example.client.model.parcelle.*;
import com.example.client.model.terrain.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.model.terrain.*;
import org.springframework.stereotype.Component;

@Component
public class Simulation {
    String idHistoriqueSimulation;
    Utilisateur utilisateur;
    Terrain terrain;
    ParcelleCulture parcelleCulture;
    Date dateSimulation;
    Integer etatEvolution;

    public Simulation(String idHistoriqueSimulation, Utilisateur utilisateur, Terrain terrain, ParcelleCulture parcelleCulture, Date dateSimulation, Integer etatEvolution) {
        this.setIdHistoriqueSimulation(idHistoriqueSimulation);
        this.setUtilisateur(utilisateur);
        this.setTerrain(terrain);
        this.setParcelleCulture(parcelleCulture);
        this.setDateSimulation(dateSimulation);
        this.setEtatEvolution(etatEvolution);
    }
    public Simulation() {
    }

    public String getIdHistoriqueSimulation() {
        return idHistoriqueSimulation;
    }
    public void setIdHistoriqueSimulation(String idHistoriqueSimulation) {
        this.idHistoriqueSimulation = idHistoriqueSimulation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public ParcelleCulture getParcelleCulture() {
        return parcelleCulture;
    }
    public void setParcelleCulture(ParcelleCulture parcelleCulture) {
        this.parcelleCulture = parcelleCulture;
    }

    public Date getDateSimulation() {
        return dateSimulation;
    }
    public void setDateSimulation(Date dateSimulation) {
        this.dateSimulation = dateSimulation;
    }

    public Integer getEtatEvolution() {
        return etatEvolution;
    }
    public void setEtatEvolution(Integer etatEvolution) {
        this.etatEvolution = etatEvolution;
    }
    public boolean isInsertable() {
        boolean response = this != null && 
        this.getUtilisateur() != null && this.getUtilisateur().getIdUtilisateur() != null && !this.getUtilisateur().getIdUtilisateur().isEmpty() &&
        this.getParcelleCulture() != null && this.getParcelleCulture().getIdParcelleCulture() != null && !this.getParcelleCulture().getIdParcelleCulture().isEmpty() &&
        this.getDateSimulation() != null;
        return response;
    }
}