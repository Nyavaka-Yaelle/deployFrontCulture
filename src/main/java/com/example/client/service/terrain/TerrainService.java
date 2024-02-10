package com.example.client.service.terrain;

import com.example.client.model.terrain.*;
import com.example.client.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.client.service.connection.EConnection;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TerrainService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private Terrain terrain;

    public TerrainService(Terrain terrain)
    {
        this.setTerrain(terrain);
    }

    public Terrain getTerrain() {
        return terrain;
    }
    @Autowired 
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public List<Terrain> find(Statement statement) throws Exception
    {           
        List<Terrain> resultats = new ArrayList<>();
        try{
            String query = "select * from Terrain";
            if(getTerrain().getIdTerrain() != null) query = query + " where idTerrain ilike '"+getTerrain().getIdTerrain()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                Terrain t = new Terrain( resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getBoolean(5) );
                resultats.add(t);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Terrain> findAll(Statement statement) throws Exception
    {           
        List<Terrain> resultats = new ArrayList<>();
        try{
            String query = "select * from Terrain";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                Terrain t = new Terrain( resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getBoolean(5) );
                resultats.add(t);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Terrain> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Terrain> resultats = new ArrayList<>();
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
    public List<Terrain> findAll() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Terrain> resultats = new ArrayList<>();
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            resultats = findAll(statement);
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
        String query = "insert into Terrain (idTerrain, nomTerrain, nbParcelle, geolocalisation, isValide) values ('Ter'||nextval('seq_Terrain'), '"+this.getTerrain().getNomTerrain()+"', "+this.getTerrain().getNbParcelle()+", '"+this.getTerrain().getGeolocalisation()+"',"+this.getTerrain().getIsValide()+")";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void update(Statement statement) throws Exception
    {    
        String condition = "";
        condition = generaliteService.condition(condition, this.getTerrain().getNomTerrain(), "nomTerrain");
        condition = generaliteService.condition(condition, this.getTerrain().getNbParcelle(), "nbParcelle");
        String query = "update Terrain "+condition+" where idTerrain  ilike '"+this.getTerrain().getIdTerrain()+"'";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void delete(Statement statement) throws Exception
    {           
        String query = "delete from Terrain where idTerrain  ilike '"+this.getTerrain().getIdTerrain()+"'";
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
    //find rehetra valide/non valide, avec indice, avec list Indice
    //insert/update valide
}
