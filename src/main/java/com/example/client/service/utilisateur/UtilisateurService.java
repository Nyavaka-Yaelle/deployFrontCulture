package com.example.client.service.utilisateur;

import com.example.client.model.utilisateur.*;
import com.example.client.model.terrain.*;
import com.example.client.model.parcelle.*;
import com.example.client.model.culture.*;
import com.example.client.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.client.service.connection.EConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private Utilisateur utilisateur;

    public UtilisateurService(Utilisateur utilisateur)
    {
        this.setUtilisateur(utilisateur);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    @Autowired 
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur exist(Statement statement) throws Exception
    {
        List<Utilisateur> resultats = find(statement);
        if(resultats.size()>0 && resultats.get(0) != null) return resultats.get(0);
        return null;
    }
    public List<Utilisateur> find(Statement statement) throws Exception
    {           
        List<Utilisateur> resultats = new ArrayList<>();
        try{
            String query = "select * from Utilisateur";
            if(getUtilisateur().getIdUtilisateur() != null) query = query + " where idUtilisateur ilike '"+getUtilisateur().getIdUtilisateur()+"'";
            else if(getUtilisateur().getEmail() != null && getUtilisateur().getMotDePasse() != null) query = query + " where email ilike '"+getUtilisateur().getEmail()+"' and motDePasse = '"+getUtilisateur().getMotDePasse()+"'";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                Utilisateur u = new Utilisateur( resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6) );
                resultats.add(u);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public Utilisateur exist() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        Utilisateur resultat = new Utilisateur();
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            resultat = exist(statement);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           if(statement!=null) statement.close();
           if(connection!=null) connection.close();
       }
       return resultat;
    }
    public List<Utilisateur> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Utilisateur> resultats = new ArrayList<>();
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            resultats = find(statement);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           if(statement!=null) statement.close();
           if(connection!=null) connection.close();
       }
       return resultats;
    }
    public void insert(Statement statement) throws Exception
    { 
        if(this.exist(statement)==null)
        {          
            String query = "insert into Utilisateur (idUtilisateur, nomUtilisateur, email, motDePasse, dateNaissance, codeSecret) values ('U'||nextval('seq_Utilisateur'), '"+this.getUtilisateur().getNomUtilisateur()+"', '"+this.getUtilisateur().getEmail()+"', '"+this.getUtilisateur().getMotDePasse()+"', '"+this.getUtilisateur().getDateNaissance()+"', '"+this.getUtilisateur().getCodeSecret()+"')";
            System.out.println(query); 
            int line=statement.executeUpdate(query);
        }
    }
    public void update(Statement statement) throws Exception
    {    
        String condition = "";
        condition = generaliteService.condition(condition, this.getUtilisateur().getNomUtilisateur(), "nomUtilisateur");
        condition = generaliteService.condition(condition, this.getUtilisateur().getEmail(), "email");
        condition = generaliteService.condition(condition, this.getUtilisateur().getMotDePasse(), "motDePasse");
        condition = generaliteService.condition(condition, this.getUtilisateur().getDateNaissance(), "dateNaissance");
        condition = generaliteService.condition(condition, this.getUtilisateur().getCodeSecret(), "codeSecret");
        String query = "update Utilisateur "+condition+" where idUtilisateur  ilike '"+this.getUtilisateur().getIdUtilisateur()+"'";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void delete(Statement statement) throws Exception
    {           
        String query = "delete from Utilisateur where idUtilisateur  ilike '"+this.getUtilisateur().getIdUtilisateur()+"'";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void insert() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            this.insert(statement);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           if(statement!=null) statement.close();
           if(connection!=null) connection.close();
       }
    }  
    public void update() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            this.update(statement);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           if(statement!=null) statement.close();
           if(connection!=null) connection.close();
       }
    }  
    public void delete() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            this.delete(statement);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           if(statement!=null) statement.close();
           if(connection!=null) connection.close();
       }
    }    
   
 
    //find avec indice
}
