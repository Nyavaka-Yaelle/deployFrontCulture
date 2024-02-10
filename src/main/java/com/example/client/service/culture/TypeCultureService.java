package com.example.client.service.culture;

import com.example.client.model.culture.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.client.service.connection.EConnection;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TypeCultureService {

    @Autowired
    private EConnection dbConnect;

    private TypeCulture typeCulture;

    public TypeCultureService(TypeCulture typeCulture)
    {
        this.setTypeCulture(typeCulture);
    }

    public TypeCulture getTypeCulture() {
        return typeCulture;
    }
    @Autowired 
    public void setTypeCulture(TypeCulture typeCulture) {
        this.typeCulture = typeCulture;
    }
    public List<TypeCulture> findAll(Statement statement) throws Exception
    {           
        List<TypeCulture> resultats = new ArrayList<>();
        try{
            String query = "select * from TypeCulture order by nomTypeCulture";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                TypeCulture t = new TypeCulture(resultSet.getString(1), resultSet.getString(2));
                resultats.add(t);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<TypeCulture> find(Statement statement) throws Exception
    {           
        List<TypeCulture> resultats = new ArrayList<>();
        try{
            String query = "select * from TypeCulture";
            if(getTypeCulture().getIdTypeCulture() != null)query = query + " where idTypeCulture ilike '"+getTypeCulture().getIdTypeCulture()+"'";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                
                TypeCulture t = new TypeCulture(resultSet.getString(1), resultSet.getString(2));
                resultats.add(t);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<TypeCulture> findAll() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<TypeCulture> resultats = new ArrayList<>();
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
    public List<TypeCulture> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<TypeCulture> resultats = new ArrayList<>();
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
        String query = "insert into typeCulture (idTypeCulture, nomTypeCulture) values ('Type'||nextval('seq_TypeCulture'), '"+this.getTypeCulture().getNomTypeCulture()+"')";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void update(Statement statement) throws Exception
    {           
        String query = "update typeCulture set nomTypeCulture = '"+this.getTypeCulture().getNomTypeCulture()+"' where idTypeCulture  ilike '"+this.getTypeCulture().getIdTypeCulture()+"'";
        System.out.println(query); 
        int line=statement.executeUpdate(query);
    }
    public void delete(Statement statement) throws Exception
    {           
        String query = "delete from typeCulture where idTypeCulture  ilike '"+this.getTypeCulture().getIdTypeCulture()+"'";
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
