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
@RequestMapping("/simulations")
public class SimulationController {
    @Autowired
    SimulationService simulationService;

    @Autowired
    GeneraliteService generaliteService;

    @GetMapping
    public String getAll() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(simulationService.find());
    }
    @GetMapping("/historiques")
    public String getAllWithParcelles() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(simulationService.find());
    }

    @GetMapping("/historiques/u{idUtilisateur}")
    public String getAllWithParcellesCultures(@PathVariable String idUtilisateur) throws Exception{
        Gson gson = new Gson();
        Simulation s = new Simulation();
        s.setUtilisateur(new Utilisateur(idUtilisateur));     
        simulationService.setSimulation(s);   
        return gson.toJson(simulationService.find());
    }
    // @GetMapping("/condition")
    // public String getCondition() throws Exception{
    //     simulation c = new simulation("C2","katsaka", new Typesimulation("Type1", "Cereale"), 90);
    //     String condition = "";
    //     // condition = generaliteService.condition(condition, c.getNomsimulation(), "nomsimulation");
    //     condition = generaliteService.condition(condition, c.getTypesimulation().getIdTypesimulation(), "idTypesimulation");
    //     condition = generaliteService.condition(condition, c.getNbParcelle(), "tempsCroissance");
    //     return condition;
    // }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Simulation simulation)  throws Exception{
        if (simulation.isInsertable()) {
            simulationService.setSimulation(simulation);
            simulationService.insert();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
}