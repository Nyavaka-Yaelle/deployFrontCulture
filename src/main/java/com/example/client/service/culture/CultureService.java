package com.example.client.service.culture;

import com.example.client.model.culture.*;
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
public class CultureService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private Culture culture;

    public CultureService(Culture culture)
    {
        this.setCulture(culture);
    }

    public Culture getCulture() {
        return culture;
    }
    @Autowired 
    public void setCulture(Culture culture) {
        this.culture = culture;
    }

    public List<Culture> findAll(Statement statement) throws Exception
    {           
        List<Culture> resultats = new ArrayList<>();
        try{
            String query = "select * from v_Culture";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                Culture c = new Culture( resultSet.getString(1), resultSet.getString(2), new TypeCulture( resultSet.getString(3), resultSet.getString(4) ), resultSet.getInt(5) );
                resultats.add(c);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Culture> find(Statement statement) throws Exception
    {           
        List<Culture> resultats = new ArrayList<>();
        try{
            String query = "select * from v_Culture";
            if(getCulture().getIdCulture() != null) query = query + " where idCulture ilike '"+getCulture().getIdCulture()+"'";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                Culture c = new Culture( resultSet.getString(1), resultSet.getString(2), new TypeCulture( resultSet.getString(3), resultSet.getString(4) ), resultSet.getInt(5) );
                resultats.add(c);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Culture> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Culture> resultats = new ArrayList<>();
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
    public List<Culture> findAll() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Culture> resultats = new ArrayList<>();
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
        String query = "insert into Culture (idCulture, nomCulture, idTypeCulture, tempsCroissance) values ('C'||nextval('seq_Culture'), '"+this.getCulture().getNomCulture()+"','"+this.getCulture().getTypeCulture().getIdTypeCulture()+"',"+this.getCulture().getTempsCroissance()+")";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void update(Statement statement) throws Exception
    {    
        String condition = "";
        condition = generaliteService.condition(condition, this.getCulture().getNomCulture(), "nomCulture");
        condition = generaliteService.condition(condition, this.getCulture().getTypeCulture().getIdTypeCulture(), "idTypeCulture");
        condition = generaliteService.condition(condition, this.getCulture().getTempsCroissance(), "tempsCroissance");
        String query = "update Culture "+condition+" where idCulture  ilike '"+this.getCulture().getIdCulture()+"'";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void delete(Statement statement) throws Exception
    {           
        String query = "delete from Culture where idCulture  ilike '"+this.getCulture().getIdCulture()+"'";
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
}
