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
public class UtilisateurFicheService {

    @Autowired
    private EConnection dbConnect;

    @Autowired
    private GeneraliteService generaliteService;

    private UtilisateurFiche utilisateurFiche;

    public UtilisateurFicheService(UtilisateurFiche utilisateurFiche)
    {
        this.setUtilisateurFiche(utilisateurFiche);
    }

    public UtilisateurFiche getUtilisateurFiche() {
        return utilisateurFiche;
    }
    @Autowired 
    public void setUtilisateurFiche(UtilisateurFiche utilisateurFiche) {
        this.utilisateurFiche = utilisateurFiche;
    }
    public String getCondition()
    {
        String condition = "";
        condition = (this.getUtilisateurFiche().getUtilisateur() != null && this.getUtilisateurFiche().getUtilisateur().getIdUtilisateur() != null && !this.getUtilisateurFiche().getUtilisateur().getIdUtilisateur().isEmpty())? condition = generaliteService.filtre(condition, this.getUtilisateurFiche().getUtilisateur().getIdUtilisateur(), "idUtilisateur"): condition;
        if(this.getUtilisateurFiche().getParcelleModele() != null )
        {
            condition = (this.getUtilisateurFiche().getParcelleModele().getIdParcelleModele() != null && !this.getUtilisateurFiche().getParcelleModele().getIdParcelleModele().isEmpty())? generaliteService.filtre(condition, this.getUtilisateurFiche().getParcelleModele().getIdParcelleModele(), "idParcelleModele"): condition;
            condition = (this.getUtilisateurFiche().getParcelleModele().getNomParcelleModele() != null && !this.getUtilisateurFiche().getParcelleModele().getNomParcelleModele().isEmpty())? generaliteService.filtre(condition, this.getUtilisateurFiche().getParcelleModele().getNomParcelleModele(), "nomParcelleModele"): condition;
            condition = (this.getUtilisateurFiche().getParcelleModele().getTypeCulture() != null && this.getUtilisateurFiche().getParcelleModele().getTypeCulture().getIdTypeCulture() != null &&  !this.getUtilisateurFiche().getParcelleModele().getTypeCulture().getIdTypeCulture().isEmpty())? generaliteService.filtre(condition, this.getUtilisateurFiche().getParcelleModele().getTypeCulture().getIdTypeCulture(), "idTypeCulture"): condition;
            condition = (this.getUtilisateurFiche().getParcelleModele().getTypeCulture() != null && this.getUtilisateurFiche().getParcelleModele().getTypeCulture().getNomTypeCulture() != null &&  !this.getUtilisateurFiche().getParcelleModele().getTypeCulture().getNomTypeCulture().isEmpty())?generaliteService.filtre(condition, this.getUtilisateurFiche().getParcelleModele().getTypeCulture().getNomTypeCulture(), "nomTypeCulture"): condition;
        }
        if(this.getUtilisateurFiche().getCulture() != null )
        {
            condition = (this.getUtilisateurFiche().getCulture().getIdCulture() != null && !this.getUtilisateurFiche().getCulture().getIdCulture().isEmpty())? generaliteService.filtre(condition, this.getUtilisateurFiche().getCulture().getIdCulture(), "idCulture"): condition;
            condition = (this.getUtilisateurFiche().getCulture().getNomCulture() != null && !this.getUtilisateurFiche().getCulture().getNomCulture().isEmpty())? generaliteService.filtre(condition, this.getUtilisateurFiche().getCulture().getNomCulture(), "nomCulture"): condition;
        }
        System.out.println(condition);
        return condition;
    }
    public List<Utilisateur> findParcelles(Statement statement) throws Exception
    {           
        List<Utilisateur> resultats = new ArrayList<>();
        try{
            String query = "select * from v_ParcelleCultureProprio "+getCondition()+" order by idUtilisateur, idTerrain, idParcelleModele";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            Integer iUser = 0;
            Integer iTerrain = 0;
            Integer iParcelle = 0;
            while (resultSet.next()) 
            {
                Utilisateur u = new Utilisateur( resultSet.getString(17), resultSet.getString(18), resultSet.getString(19), resultSet.getString(20), resultSet.getDate(21), resultSet.getString(22) );
                TypeCulture tc = new TypeCulture(resultSet.getString(3), resultSet.getString(4));
                Terrain t = ( resultSet.getString(6) != null )? new Terrain( resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getBoolean(10), resultSet.getDate(11) ):null;

                ParcelleCulture pc = ( resultSet.getString(1) != null )? new ParcelleCulture( resultSet.getString(1), resultSet.getString(2), tc, resultSet.getString(5) ):null;

                if(resultSet.getString(15) != null) pc.init(resultSet.getString(15), new Culture( resultSet.getString(12), resultSet.getString(13), tc, resultSet.getInt(14) ), resultSet.getDate(16));
                boolean newUser = iUser >=1 && !resultats.get(iUser-1).getIdUtilisateur().equals(u.getIdUtilisateur());
                boolean sameUser = iUser >=1 && resultats.get(iUser-1).getIdUtilisateur().equals(u.getIdUtilisateur());

                if( sameUser )
                {
                    boolean newTerrain = resultats.get(iUser-1).getTerrain() != null && !resultats.get(iUser-1).getTerrain().get(iTerrain-1).getIdTerrain().equals(t.getIdTerrain());
                    boolean sameTerrain = resultats.get(iUser-1).getTerrain() != null && resultats.get(iUser-1).getTerrain().get(iTerrain-1).getIdTerrain().equals(t.getIdTerrain());
                    if( sameTerrain && pc != null )
                    {
                        resultats.get(iUser-1).getTerrain().get(iTerrain-1).addParcelleCulture(pc);
                        iParcelle ++;
                    }
                    if( newTerrain )
                    {
                        iParcelle = 0;
                        if(pc != null)
                        { 
                            t.addParcelleCulture(pc);
                            iParcelle++;
                        }
                        resultats.get(iUser-1).addTerrain(t);
                        iTerrain ++;
                    }
                }
                else if(iUser == 0 || newUser)
                {
                    if(newUser) iTerrain = 0;
                    if(t != null) 
                    {
                        if(pc != null)
                        { 
                            t.addParcelleCulture(pc);
                            iParcelle++;
                        }
                        u.addTerrain(t);
                        iTerrain++;
                    }
                    resultats.add(u);
                    iUser++;
                }
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return resultats;
    }
    public List<Utilisateur> findParcelles() throws Exception
    {           
        Connection connection = null;
        Statement statement = null;
        List<Utilisateur> resultats = new ArrayList<>();
       try{
            connection = dbConnect.getConnection();
            statement = connection.createStatement();
            resultats = findParcelles(statement);
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
}
