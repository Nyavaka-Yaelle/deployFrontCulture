package com.example.client.model.discussion;

import com.example.client.model.utilisateur.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.service.connection.EConnection;
import org.springframework.stereotype.Component;

@Component
public class Discussion {
    String idDiscussion;
    Utilisateur membre1;
    Utilisateur membre2;

    public Discussion(String idDiscussion, Utilisateur membre1,Utilisateur membre2) {
        this.setIdDiscussion(idDiscussion);
        this.setMembre1 (membre1);
        this.setMembre2 (membre2);
    }
    public Discussion(Utilisateur membre1,Utilisateur membre2) {
        this.setMembre1 (membre1);
        this.setMembre2 (membre2);
    }
    public Discussion(String idDiscussion) {
        this.setIdDiscussion(idDiscussion);
    }
    public Discussion() {
    }
    
    public String getIdDiscussion() {
        return idDiscussion;
    }
    public void setIdDiscussion(String idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    public Utilisateur getMembre1() {
        return membre1;
    }
    public void setMembre1(Utilisateur membre1) {
        this.membre1 = membre1;
    }

    public Utilisateur getMembre2() {
        return membre2;
    }
    public void setMembre2(Utilisateur membre2) {
        this.membre2 = membre2;
    }

    public boolean isDeletable() {
        return false;
    }
    public boolean isUpdatable() {
        return false;
    }
    public boolean isInsertable() {
        boolean response = this != null && this.getMembre1() != null && this.getMembre1().getIdUtilisateur() != null && !this.getMembre1().getIdUtilisateur().isEmpty() && this.getMembre2() != null && this.getMembre2().getIdUtilisateur() != null && !this.getMembre2().getIdUtilisateur().isEmpty();
        return response;
    }  
}