package com.example.client.model.culture;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.service.connection.EConnection;
import org.springframework.stereotype.Component;

@Component
public class TypeCulture {
    String idTypeCulture;
    String nomTypeCulture;

    public TypeCulture(String idTypeCulture, String nomTypeCulture) {
        this.setIdTypeCulture(idTypeCulture);
        this.setNomTypeCulture (nomTypeCulture);
    }

    public TypeCulture(String idTypeCulture) {
        this.setIdTypeCulture(idTypeCulture);
    }

    public TypeCulture() {
    }
    
    public String getIdTypeCulture() {
        return idTypeCulture;
    }

    public void setIdTypeCulture(String idTypeCulture) {
        this.idTypeCulture = idTypeCulture;
    }

    public String getNomTypeCulture() {
        return nomTypeCulture;
    }

    public void setNomTypeCulture(String nomTypeCulture) {
        if(nomTypeCulture !=null ) this.nomTypeCulture = nomTypeCulture.trim();
    }
    public boolean isDeletable() {
        return this != null && this.getIdTypeCulture() != null && !this.getIdTypeCulture().isEmpty();
    }
    public boolean isUpdatable() {
        return this != null && this.getNomTypeCulture() != null && !this.getNomTypeCulture().isEmpty() && this.getIdTypeCulture() != null && !this.getIdTypeCulture().isEmpty();
    }
    public boolean isInsertable() {
        return this != null && this.getNomTypeCulture() != null && !this.getNomTypeCulture().isEmpty();
    }
}