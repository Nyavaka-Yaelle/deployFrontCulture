package com.example.client.model.terrain;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.model.parcelle.*;
import org.springframework.stereotype.Component;

@Component
public class Terrain {
    String idTerrain;
    String nomTerrain;
    Integer nbParcelle;
    String geolocalisation;
    Boolean isValide;
    List<ParcelleCulture> parcelleCulture;
    Date dateAcquis;

    public Terrain(String idTerrain, String nomTerrain, Integer nbParcelle, String geolocalisation, Boolean isValide, Date dateAcquis) {
        this(idTerrain, nomTerrain, nbParcelle, geolocalisation, isValide);
        this.setDateAcquis(dateAcquis);
    }
    public Terrain(String idTerrain, String nomTerrain, Integer nbParcelle, String geolocalisation, Boolean isValide) {
        this(nomTerrain, nbParcelle, geolocalisation, isValide);
        this.setIdTerrain(idTerrain);
    }
    public Terrain(String nomTerrain, Integer nbParcelle, String geolocalisation, Boolean isValide) {
        this.setNomTerrain(nomTerrain);
        this.setNbParcelle(nbParcelle);
        this.setGeolocalisation(geolocalisation);
        this.setIsValide(isValide);
    }
    public Terrain(String idTerrain) {
        this.setIdTerrain(idTerrain);
    }
    public Terrain() {
    }

    public String getIdTerrain() {
        return idTerrain;
    }
    public void setIdTerrain(String idTerrain) {
        this.idTerrain = idTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }
    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain.trim();
    }

    public Integer getNbParcelle() {
        return nbParcelle;
    }
    public void setNbParcelle(Integer nbParcelle) {
        this.nbParcelle = nbParcelle;
    }

    public String getGeolocalisation() {
        return geolocalisation;
    }
    public void setGeolocalisation(String geolocalisation) {
        this.geolocalisation = geolocalisation;
    }

    public Boolean getIsValide() {
        return isValide;
    }
    public void setIsValide(Boolean isValide) {
        this.isValide = isValide;
    }

    public List<ParcelleCulture> getParcelleCulture() {
        return parcelleCulture;
    }
    public void setParcelleCulture(List<ParcelleCulture> parcelleCulture) {
        this.parcelleCulture = parcelleCulture;
    }
    public void addParcelleCulture(ParcelleCulture parcelleCulture) {
        if(this.getParcelleCulture() == null) this.setParcelleCulture( new ArrayList<>() );
        this.getParcelleCulture().add(parcelleCulture);
    }

    public Date getDateAcquis() {
        return dateAcquis;
    }
    public void setDateAcquis(Date dateAcquis) {
        this.dateAcquis = dateAcquis;
    }
    public boolean isDeletable() {
        return this != null && this.getIdTerrain() != null && !this.getIdTerrain().isEmpty();
    }
    public boolean isUpdatable() {
        boolean response = this != null && this.getIdTerrain() != null && !this.getIdTerrain().isEmpty() && ( (this.getNomTerrain() != null && !this.getNomTerrain().isEmpty()) || (this.getNbParcelle() != null) || (this.getIsValide() != null) ||
        (this.getGeolocalisation() != null && !this.getGeolocalisation().isEmpty()) );
        return response;
    }
    public boolean isInsertable() {
        boolean response = this != null && this.getNomTerrain() != null && !this.getNomTerrain().isEmpty() && this.getNbParcelle() != null && this.getGeolocalisation() != null && !this.getGeolocalisation().isEmpty() && this.getIsValide() != null;
        return response;
    }
}