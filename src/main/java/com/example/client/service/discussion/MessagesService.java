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
public class MessagesService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private Messages messages;

    public MessagesService(Messages messages)
    {
        this.setMessages(messages);
    }

    public Messages getMessages() {
        return messages;
    }
    @Autowired 
    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public List<Messages> find(Statement statement) throws Exception
    {           
        List<Messages> resultats = new ArrayList<>();
        try{
            String condition = "";
            condition = (getMessages().getDiscussion() != null && getMessages().getDiscussion().getIdDiscussion() != null && !getMessages().getDiscussion().getIdDiscussion().isEmpty()) ? generaliteService.strictFiltre(condition, getMessages().getDiscussion().getIdDiscussion(), "idDiscussion") : condition;
            String query = "select * from v_Messages"+condition;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                Messages m = new Messages( resultSet.getString(1), new Discussion(resultSet.getString(2)), new Utilisateur(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getString(8)), resultSet.getString(9), resultSet.getDate(10));
                resultats.add(m);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Messages> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Messages> resultats = new ArrayList<>();
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
        // if(new DiscussionService(new Discussion(this.getMessages().getDiscussion().getIdDiscussion())).exist() != null)  
        // {      
            String query = "insert into Messages (idMessages, idDiscussion, idSender, messages, dateEnvoie) values ('Mes'||nextval('seq_Messages'), '"+this.getMessages().getDiscussion().getIdDiscussion()+"','"+this.getMessages().getSender().getIdUtilisateur()+"','"+this.getMessages().getMessages()+"','"+this.getMessages().getDateEnvoie()+"')";
            System.out.println(query); 
            int line=statement.executeUpdate(query);
        // }
    }

    public void delete(Statement statement) throws Exception
    {           
        String query = "delete from Messages where idMessages  ilike '"+this.getMessages().getIdMessages()+"'";
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
