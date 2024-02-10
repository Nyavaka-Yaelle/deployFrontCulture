package com.example.client.service.discussion;

import com.example.client.service.*;
import com.example.client.model.discussion.*;
import com.example.client.model.utilisateur.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.client.service.connection.EConnection;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscussionService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private Discussion discussion;

    public DiscussionService(Discussion discussion)
    {
        this.setDiscussion(discussion);
    }

    public Discussion getDiscussion() {
        return discussion;
    }
    @Autowired 
    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }
    public Discussion exist(Statement statement) throws Exception
    {
        List<Discussion> resultats = find(statement);
        if(resultats.size()>0 && resultats.get(0) != null) return resultats.get(0);
        return null;
    }
    public Discussion exist() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        Discussion resultat = new Discussion();
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
    public List<Discussion> find(Statement statement) throws Exception
    {           
        List<Discussion> resultats = new ArrayList<>();
        try{
            String condition = "";
            condition = (getDiscussion().getIdDiscussion() != null) ?generaliteService.strictFiltre(condition, getDiscussion().getIdDiscussion(), "idDiscussion") : condition;
            if(getDiscussion().getMembre1() != null && getDiscussion().getMembre1().getIdUtilisateur() != null && !getDiscussion().getMembre1().getIdUtilisateur().isEmpty() && getDiscussion().getMembre2() != null && getDiscussion().getMembre2().getIdUtilisateur() != null && !getDiscussion().getMembre2().getIdUtilisateur().isEmpty())
            {
                condition = " where (idMembre1 ilike '"+getDiscussion().getMembre1().getIdUtilisateur()+"' and idMembre2 ilike '"+getDiscussion().getMembre2().getIdUtilisateur()+") or (idMembre2 ilike '"+getDiscussion().getMembre1().getIdUtilisateur()+"' and idMembre1 ilike '"+getDiscussion().getMembre2().getIdUtilisateur()+")";
            }
            String query = "select * from v_Discussion"+condition;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                Discussion d = new Discussion( resultSet.getString(1), new Utilisateur(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7)) , new Utilisateur(resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getDate(12), resultSet.getString(13)));
                resultats.add(d);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Discussion> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Discussion> resultats = new ArrayList<>();
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
        if(exist()==null)
        {
            String query = "insert into Discussion (idDiscussion, idMembre1, idMembre2) values ('Dis'||nextval('seq_Discussion'), '"+this.getDiscussion().getMembre1().getIdUtilisateur()+"','"+this.getDiscussion().getMembre2().getIdUtilisateur()+"')";
            System.out.println(query); 
            int line=statement.executeUpdate(query);
        }      
    }

    public void delete(Statement statement) throws Exception
    {           
        String query = "delete from Discussion where idDiscussion  ilike '"+this.getDiscussion().getIdDiscussion()+"'";
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
