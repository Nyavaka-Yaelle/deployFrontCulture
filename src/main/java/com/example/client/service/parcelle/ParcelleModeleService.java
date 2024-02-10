package com.example.client.service.parcelle;

import com.example.client.model.parcelle.*;
import com.example.client.model.culture.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.client.service.connection.EConnection;
import com.example.client.service.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParcelleModeleService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private ParcelleModele parcelleModele;
    private ParcelleCulture parcelleCulture;
    public ParcelleModeleService()
    {}
    public ParcelleModeleService(ParcelleModele parcelleModele)
    {
        this.setParcelleModele(parcelleModele);
    }

    public ParcelleModele getParcelleModele() {
        return parcelleModele;
    }
    @Autowired 
    public void setParcelleModele(ParcelleModele parcelleModele) {
        this.parcelleModele = parcelleModele;
    }

    public ParcelleModeleService(ParcelleCulture parcelleCulture)
    {
        this.setParcelleCulture(parcelleCulture);
    }

    public ParcelleCulture getParcelleCulture() {
        return parcelleCulture;
    }
    @Autowired 
    public void setParcelleCulture(ParcelleCulture parcelleCulture) {
        this.parcelleCulture = parcelleCulture;
    }

    public List<ParcelleModele> find(Statement statement) throws Exception
    {           
        List<ParcelleModele> resultats = new ArrayList<>();
        try{
            String query = "select * from v_ParcelleModele";
            if(getParcelleModele().getIdParcelleModele() != null) query = query + " where idParcelleModele ilike '"+getParcelleModele().getIdParcelleModele()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                ParcelleModele p = new ParcelleModele( resultSet.getString(1), resultSet.getString(2), new TypeCulture( resultSet.getString(3), resultSet.getString(4) ));
                resultats.add(p);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<ParcelleModele> findAll(Statement statement) throws Exception
    {           
        List<ParcelleModele> resultats = new ArrayList<>();
        try{
            String query = "select * from v_ParcelleModele";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                ParcelleModele p = new ParcelleModele( resultSet.getString(1), resultSet.getString(2), new TypeCulture( resultSet.getString(3), resultSet.getString(4) ));
                resultats.add(p);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<ParcelleModele> findAll() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<ParcelleModele> resultats = new ArrayList<>();
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
    public List<ParcelleModele> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<ParcelleModele> resultats = new ArrayList<>();
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
    public void ajouterCulture(Statement statement) throws Exception
    {           
        String query = "insert into ParcelleModele(idParcelleModele, idTerrainParcelle, idCulture, dateDebut) values ('ParCult'||nextval('seq_ParcelleModele'), '"+this.getParcelleModele().getIdTerrainParcelle()+"','"+this.getParcelleCulture().getCulture().getIdCulture()+"'"+this.getParcelleCulture().getDateDebut()+"')";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void insert(Statement statement) throws Exception
    {           
        String query = "insert into ParcelleModele (idParcelleModele, nomParcelleModele, idTypeCulture) values ('PM'||nextval('seq_ParcelleModele'), '"+this.getParcelleModele().getNomParcelleModele()+"','"+this.getParcelleModele().getTypeCulture().getIdTypeCulture()+"')";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void update(Statement statement) throws Exception
    {    
        String condition = "";
        condition = generaliteService.condition(condition, this.getParcelleModele().getNomParcelleModele(), "nomParcelleModele");
        condition = generaliteService.condition(condition, this.getParcelleModele().getTypeCulture().getIdTypeCulture(), "idTypeCulture");
        String query = "update ParcelleModele "+condition+" where idParcelleModele  ilike '"+this.getParcelleModele().getIdParcelleModele()+"'";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void delete(Statement statement) throws Exception
    {           
        String query = "delete from ParcelleModele where idParcelleModele  ilike '"+this.getParcelleModele().getIdParcelleModele()+"'";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void ajouterCulture() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            this.ajouterCulture(statement);
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
}
