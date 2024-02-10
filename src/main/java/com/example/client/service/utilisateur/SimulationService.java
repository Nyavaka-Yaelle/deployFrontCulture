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
public class SimulationService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private Simulation simulation;

    public SimulationService(Simulation simulation)
    {
        this.setSimulation(simulation);
    }

    public Simulation getSimulation() {
        return simulation;
    }
    @Autowired 
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public List<Simulation> find(Statement statement) throws Exception
    {           
        List<Simulation> resultats = new ArrayList<>();
        try{
            String query = "select * from v_HistoriqueEvolution";
            if(getSimulation().getUtilisateur() != null && getSimulation().getUtilisateur().getIdUtilisateur() != null && !getSimulation().getUtilisateur().getIdUtilisateur().isEmpty()) query = query + " where idUtilisateur ilike '"+getSimulation().getUtilisateur().getIdUtilisateur()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) 
            {
                Utilisateur u = new Utilisateur( resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7) );
                
                TypeCulture tc = new TypeCulture(resultSet.getString(14), resultSet.getString(15));
                
                Terrain t = new Terrain( resultSet.getString(8), resultSet.getString(9), null, resultSet.getString(10), resultSet.getBoolean(11), null);

                ParcelleCulture pc = new ParcelleCulture( resultSet.getString(12), resultSet.getString(13), tc, null );

                pc.init(resultSet.getString(19), new Culture( resultSet.getString(16), resultSet.getString(17), tc, resultSet.getInt(18) ), resultSet.getDate(20));

                Simulation s = new Simulation( resultSet.getString(1),  u, t, pc, resultSet.getDate(21), resultSet.getInt(24));
                resultats.add(s);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Simulation> find() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Simulation> resultats = new ArrayList<>();
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
        // if(this.exist(statement)==null)
        // {          
            String query = "insert into HistoriqueSimulation (idHistoriqueSimulation, idUtilisateur, idParcelleCulture, dateSimulation, etatEvolution) values ('Histo'||nextval('seq_HistoriqueSimulation'), '"+this.getSimulation().getUtilisateur().getIdUtilisateur()+"', '"+this.getSimulation().getParcelleCulture().getIdParcelleCulture()+"', '"+this.getSimulation().getDateSimulation()+"', '"+this.getSimulation().getEtatEvolution()+"')";
            System.out.println(query); 
            int line=statement.executeUpdate(query);
        // }
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
}
