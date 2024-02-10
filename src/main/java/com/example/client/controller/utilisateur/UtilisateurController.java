package com.example.client.controller.utilisateur;

import com.example.client.model.utilisateur.*;
import com.example.client.model.culture.*;
import com.example.client.model.parcelle.*;
import com.example.client.service.utilisateur.*;
import com.example.client.service.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    UtilisateurFicheService utilisateurFicheService;

    @Autowired
    GeneraliteService generaliteService;

    @GetMapping
    public String getAll() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(utilisateurService.find());
    }
    @GetMapping("/parcelles")
    public String getAllWithParcelles() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(utilisateurFicheService.findParcelles());
    }

    @GetMapping("/fiches/u{idUtilisateur}/p{idParcelleModele}/c{idCulture}")
    public String getAllWithParcellesCultures(@PathVariable String idUtilisateur,@PathVariable String idParcelleModele,@PathVariable String idCulture) throws Exception{
        Gson gson = new Gson();     
        utilisateurFicheService.setUtilisateurFiche(new UtilisateurFiche( new Utilisateur(idUtilisateur), new ParcelleModele(idParcelleModele),new Culture(idCulture)));   
        return gson.toJson(utilisateurFicheService.findParcelles());
    }
    @PostMapping("/fiches/u/p/c")
    public ResponseEntity<String> getUPC(@RequestBody UtilisateurFiche utilisateurFiche)  throws Exception{
        if (utilisateurFiche.isUsable()) {
            utilisateurFicheService.setUtilisateurFiche(utilisateurFiche);
            Gson gson = new Gson();     
            String responseMessage = "{\"status\": \"success\", \"message\": "+gson.toJson(utilisateurFicheService.findParcelles())+"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Loading page failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    // @GetMapping("/condition")
    // public String getCondition() throws Exception{
    //     utilisateur c = new utilisateur("C2","katsaka", new Typeutilisateur("Type1", "Cereale"), 90);
    //     String condition = "";
    //     // condition = generaliteService.condition(condition, c.getNomUtilisateur(), "nomutilisateur");
    //     condition = generaliteService.condition(condition, c.getTypeutilisateur().getIdTypeutilisateur(), "idTypeutilisateur");
    //     condition = generaliteService.condition(condition, c.getNbParcelle(), "tempsCroissance");
    //     return condition;
    // }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Utilisateur utilisateur)  throws Exception{
        if (utilisateur.isInsertable()) {
            utilisateurService.setUtilisateur(utilisateur);
            utilisateurService.insert();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Utilisateur utilisateur)  throws Exception{
        // return new ResponseEntity<>(new Gson().toJson(utilisateur), HttpStatus.OK);
        if (utilisateur.isLogin()) {
            utilisateurService.setUtilisateur(utilisateur);
            Utilisateur user = utilisateurService.exist();
            if(user!=null)
            {
                Gson gson = new Gson();
                String responseMessage = "{\"status\": \"success\", \"message\": \"Login successfully\", \"utilisateur\": "+gson.toJson(user)+"}";
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            } 
            else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Count not found\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Login failed\", \"error\": \""+utilisateur.getError()+"\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }   
    }
    @PostMapping("/loginAdmin")
    public ResponseEntity<String> loginAdmin(@RequestBody Utilisateur utilisateur)  throws Exception{
        // return new ResponseEntity<>(new Gson().toJson(utilisateur), HttpStatus.OK);
        if (utilisateur.isLogin()) {
            utilisateurService.setUtilisateur(utilisateur);
            Utilisateur user = utilisateurService.exist();
            if(utilisateur.isAdmin())
            {
                Gson gson = new Gson();
                String responseMessage = "{\"status\": \"success\", \"message\": \"Login successfully\", \"utilisateur\": "+gson.toJson(user)+"}";
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            } 
            else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Count not found\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Login failed\", \"error\": \""+utilisateur.getError()+"\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }   
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Utilisateur utilisateur)  throws Exception{
        if (utilisateur.isUpdatable()) {
            utilisateurService.setUtilisateur(utilisateur);
            utilisateurService.update();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Update successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed update\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Utilisateur utilisateur)  throws Exception{
        if (utilisateur.isDeletable()) {
            utilisateurService.setUtilisateur(utilisateur);
            utilisateurService.delete();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Successfully deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
}