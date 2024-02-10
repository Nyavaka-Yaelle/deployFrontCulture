package com.example.client.controller.parcelle;

import com.example.client.model.parcelle.*;
import com.example.client.model.culture.*;
import com.example.client.service.parcelle.*;
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
@RequestMapping("/parcelles")
public class ParcelleModeleController {
    @Autowired
    ParcelleModeleService parcelleModeleService;
    
    @Autowired
    GeneraliteService generaliteService;

    @GetMapping
    public String getAll() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(parcelleModeleService.findAll());
    }
    @GetMapping("/{idParcelleModele}")
    public String getOne(@PathVariable String idParcelleModele) throws Exception{
        Gson gson = new Gson();  
        parcelleModeleService.setParcelleModele(new ParcelleModele(idParcelleModele));
        return gson.toJson(parcelleModeleService.find());
    }
    // @GetMapping("/condition")
    // public String getCondition() throws Exception{
    //     parcelleModele c = new parcelleModele("PM2", new TypeCulture("Type1", "Cereale"), 90);
    //     String condition = "";
    //     // condition = generaliteService.condition(condition, c.getNomParcelleModele(), "nomparcelleModele");
    //     condition = generaliteService.condition(condition, c.getTypeCulture().getIdTypeCulture(), "idTypeCulture");
    //     condition = generaliteService.condition(condition, c.getTempsCroissance(), "tempsCroissance");
    //     return condition;
    // }
    @PostMapping("/cultures")
    public ResponseEntity<String> insert(@RequestBody ParcelleCulture parcelleCulture)  throws Exception{
        if (parcelleCulture.isInsertable()) {
            parcelleModeleService.setParcelleCulture(parcelleCulture);
            if(parcelleCulture.getCulture()!=null && parcelleCulture.getCulture().getIdCulture()!=null)
            {
                parcelleModeleService.getParcelleCulture().setDateDebut(new java.sql.Date(new java.util.Date().getTime()));
                parcelleModeleService.ajouterCulture();
            }
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody ParcelleModele parcelleModele)  throws Exception{
        if (parcelleModele.isInsertable()) {
            parcelleModeleService.setParcelleModele(parcelleModele);
            parcelleModeleService.insert();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody ParcelleModele parcelleModele)  throws Exception{
        if (parcelleModele.isUpdatable()) {
            parcelleModeleService.setParcelleModele(parcelleModele);
            parcelleModeleService.update();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Update successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed update\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody ParcelleModele parcelleModele)  throws Exception{
        if (parcelleModele.isDeletable()) {
            parcelleModeleService.setParcelleModele(parcelleModele);
            parcelleModeleService.delete();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Successfully deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
}