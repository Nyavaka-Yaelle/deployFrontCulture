package com.example.client.model.parcelle;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.model.culture.*;
import com.example.client.service.connection.EConnection;
import org.springframework.stereotype.Component;

@Component
public class ParcelleModele {
    String idParcelleModele;
    String nomParcelleModele;
    TypeCulture typeCulture;
    String idTerrainParcelle;

    public ParcelleModele(String idParcelleModele, String nomParcelleModele, TypeCulture typeCulture, String idTerrainParcelle) {
        this(idParcelleModele, nomParcelleModele, typeCulture);
        this.setIdTerrainParcelle(idTerrainParcelle);
    }
    public ParcelleModele(String idParcelleModele, String nomParcelleModele, TypeCulture typeCulture) {
        this(nomParcelleModele,typeCulture);
        this.setIdParcelleModele(idParcelleModele);
    }
    public ParcelleModele(String nomParcelleModele, TypeCulture typeCulture) {
        this.setNomParcelleModele(nomParcelleModele);
        this.setTypeCulture(typeCulture);
    }
    public ParcelleModele(String idParcelleModele) {
        this.setIdParcelleModele(idParcelleModele);
    }
    public ParcelleModele() {
    }
    
    public String getIdParcelleModele() {
        return idParcelleModele;
    }
    public void setIdParcelleModele(String idParcelleModele) {
        this.idParcelleModele = idParcelleModele;
    }

    public String getNomParcelleModele() {
        return nomParcelleModele;
    }
    public void setNomParcelleModele(String nomParcelleModele) {
        if(nomParcelleModele != null) this.nomParcelleModele = nomParcelleModele.trim();
    }

    public TypeCulture getTypeCulture() {
        return typeCulture;
    }
    public void setTypeCulture(TypeCulture typeCulture) {
        this.typeCulture = typeCulture;
    }

    public String getIdTerrainParcelle() {
        return idTerrainParcelle;
    }
    public void setIdTerrainParcelle(String idTerrainParcelle) {
        this.idTerrainParcelle = idTerrainParcelle;
    }
    public boolean isDeletable() {
        return this != null && this.getIdParcelleModele() != null && !this.getIdParcelleModele().isEmpty();
    }
    public boolean isUpdatable() {
        boolean response = this != null && this.getIdParcelleModele() != null && !this.getIdParcelleModele().isEmpty() && ( (this.getNomParcelleModele() != null && !this.getNomParcelleModele().isEmpty()) || 
        (this.getTypeCulture() != null && this.getTypeCulture().getIdTypeCulture() != null && !this.getTypeCulture().getIdTypeCulture().isEmpty()));
        return response;
    }
    public boolean isInsertable() {
        boolean response = this != null && this.getNomParcelleModele() != null && !this.getNomParcelleModele().isEmpty() && this.getTypeCulture() != null && this.getTypeCulture().getIdTypeCulture() != null && !this.getTypeCulture().getIdTypeCulture().isEmpty();
        return response;
    }
}