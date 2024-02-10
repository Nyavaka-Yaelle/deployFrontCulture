package com.example.client.model.parcelle;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.model.culture.*;
import com.example.client.service.connection.EConnection;
import org.springframework.stereotype.Component;

@Component
public class ParcelleCulture extends ParcelleModele{
    String idParcelleCulture;
    Culture culture;
    Date dateDebut;

    public ParcelleCulture(String idParcelleModele, String nomParcelleModele, TypeCulture typeCulture, String idTerrainParcelle) {
        super(idParcelleModele, nomParcelleModele, typeCulture, idTerrainParcelle);
    }
    public ParcelleCulture(Culture culture, Date dateDebut) {
        this.setCulture(culture);
        this.setDateDebut(dateDebut);
    }
    public ParcelleCulture(String idParcelleCulture, Culture culture, Date dateDebut) {
        this(culture, dateDebut);
        this.setIdParcelleCulture(idParcelleCulture);
    }
    public ParcelleCulture(String idParcelleCulture) {
        this.setIdParcelleCulture(idParcelleCulture);
    }
    public ParcelleCulture() {
    }
    
    public String getIdParcelleCulture() {
        return idParcelleCulture;
    }
    public void setIdParcelleCulture(String idParcelleCulture) {
        this.idParcelleCulture = idParcelleCulture;
    }

    public Culture getCulture() {
        return culture;
    }
    public void setCulture(Culture culture) {
        this.culture = culture;
    }

    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void init(String idParcelleCulture, Culture culture, Date dateDebut) {
        this.setIdParcelleCulture(idParcelleCulture);
        this.setCulture(culture);
        this.setDateDebut(dateDebut);
    }
    public boolean isDeletable() {
        return this != null && super.getIdTerrainParcelle() != null && this.getIdParcelleCulture() != null && !this.getIdParcelleCulture().isEmpty();
    }
    public boolean isUpdatable() {
        boolean response = this != null && super.isUpdatable();
        return response;
    }
    public boolean isInsertable() {
        boolean response = this != null && super.getIdTerrainParcelle() != null && !super.getIdTerrainParcelle().isEmpty() && this.getCulture() != null && this.getCulture().getIdCulture() != null && !this.getCulture().getIdCulture().isEmpty() && getDateDebut() != null;
        return response;
    }
}