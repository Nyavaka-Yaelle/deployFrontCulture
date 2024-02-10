package com.example.client.model.culture;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.service.connection.EConnection;
import org.springframework.stereotype.Component;

@Component
public class Culture {
    String idCulture;
    String nomCulture;
    TypeCulture typeCulture;
    Integer tempsCroissance;//jour

    public Culture(String idCulture, String nomCulture, TypeCulture typeCulture, Integer tempsCroissance) {
        this.setIdCulture(idCulture);
        this.setNomCulture (nomCulture);
        this.setTypeCulture (typeCulture);
        this.setTempsCroissance(tempsCroissance);
    }
    public Culture(String idCulture) {
        this.setIdCulture(idCulture);
    }
    public Culture() {
    }
    
    public String getIdCulture() {
        return idCulture;
    }
    public void setIdCulture(String idCulture) {
        this.idCulture = idCulture;
    }

    public String getNomCulture() {
        return nomCulture;
    }
    public void setNomCulture(String nomCulture) {
        if(nomCulture != null) this.nomCulture = nomCulture.trim();
    }

    public TypeCulture getTypeCulture() {
        return typeCulture;
    }
    public void setTypeCulture(TypeCulture typeCulture) {
        this.typeCulture = typeCulture;
    }

    public Integer getTempsCroissance() {
        return tempsCroissance;
    }
    public void setTempsCroissance(Integer tempsCroissance) {
        this.tempsCroissance = tempsCroissance;
    }
    public boolean isDeletable() {
        return this != null && this.getIdCulture() != null && !this.getIdCulture().isEmpty();
    }
    public boolean isUpdatable() {
        boolean response = this != null && this.getIdCulture() != null && !this.getIdCulture().isEmpty() && ( (this.getNomCulture() != null && !this.getNomCulture().isEmpty()) || (this.getTempsCroissance() != null) ||
        ((this.getTypeCulture() != null) && (this.getTypeCulture().getIdTypeCulture() != null && !this.getTypeCulture().getIdTypeCulture().isEmpty()) ));
        return response;
    }
    public boolean isInsertable() {
        boolean response = this != null && this.getNomCulture() != null && !this.getNomCulture().isEmpty() && this.getTempsCroissance() != null && this.getTypeCulture() != null && this.getTypeCulture().getIdTypeCulture() != null && !this.getTypeCulture().getIdTypeCulture().isEmpty();
        return response;
    }  
}