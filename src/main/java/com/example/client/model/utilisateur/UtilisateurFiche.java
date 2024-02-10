package com.example.client.model.utilisateur;
import com.example.client.model.parcelle.*;
import com.example.client.model.culture.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.model.terrain.*;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurFiche {
    Utilisateur utilisateur;
    ParcelleModele parcelleModele;
    Culture culture;

    public UtilisateurFiche(Utilisateur utilisateur, ParcelleModele parcelleModele, Culture culture) {
        this.setUtilisateur(utilisateur);
        this.setParcelleModele(parcelleModele);
        this.setCulture(culture);
    }
    public UtilisateurFiche() {
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ParcelleModele getParcelleModele() {
        return parcelleModele;
    }
    public void setParcelleModele(ParcelleModele parcelleModele) {
        this.parcelleModele = parcelleModele;
    }

    public Culture getCulture() {
        return culture;
    }
    public void setCulture(Culture culture) {
        this.culture = culture;
    }
   
    public boolean isUsable() {
        boolean response = this != null && (
            (this.getUtilisateur() != null && this.getUtilisateur().getIdUtilisateur() != null && !this.getUtilisateur().getIdUtilisateur().isEmpty()) || 
            (this.getParcelleModele() != null && 
                ( 
                    (this.getParcelleModele().getIdParcelleModele() != null && !this.getParcelleModele().getIdParcelleModele().isEmpty()) || 
                    (this.getParcelleModele().getNomParcelleModele() != null && !this.getParcelleModele().getNomParcelleModele().isEmpty()) || 
                    (this.getParcelleModele().getTypeCulture() != null && (
                        (this.getParcelleModele().getTypeCulture().getIdTypeCulture() != null &&  !this.getParcelleModele().getTypeCulture().getIdTypeCulture().isEmpty()) || 
                        (this.getParcelleModele().getTypeCulture().getNomTypeCulture() != null &&  !this.getParcelleModele().getTypeCulture().getNomTypeCulture().isEmpty()) )
                    )
                )
            ) ||
            (this.getParcelleModele() != null && 
                (
                    (this.getCulture().getIdCulture() != null && !this.getCulture().getIdCulture().isEmpty()) ||
                    (this.getCulture().getNomCulture() != null && !this.getCulture().getNomCulture().isEmpty())
                )
            )
        );
        return response;
    }
}