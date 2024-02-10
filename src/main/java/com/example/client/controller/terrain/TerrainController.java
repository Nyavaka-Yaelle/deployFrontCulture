package com.example.client.controller.terrain;

import com.example.client.model.terrain.*;
import com.example.client.service.terrain.*;
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
@RequestMapping("/terrains")
public class TerrainController {
    @Autowired
    TerrainService terrainService;

    @Autowired
    GeneraliteService generaliteService;

    @GetMapping
    public String getAll() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(terrainService.findAll());
    }
    @GetMapping("/{idTerrain}")
    public String getOne(@PathVariable String idTerrain) throws Exception{
        Gson gson = new Gson();  
        terrainService.setTerrain(new Terrain(idTerrain));
        return gson.toJson(terrainService.find());
    }
    // @GetMapping("/condition")
    // public String getCondition() throws Exception{
    //     terrain c = new terrain("C2","katsaka", new Typeterrain("Type1", "Cereale"), 90);
    //     String condition = "";
    //     // condition = generaliteService.condition(condition, c.getNomTerrain(), "nomterrain");
    //     condition = generaliteService.condition(condition, c.getTypeterrain().getIdTypeterrain(), "idTypeterrain");
    //     condition = generaliteService.condition(condition, c.getNbParcelle(), "tempsCroissance");
    //     return condition;
    // }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Terrain terrain)  throws Exception{
        if (terrain.isInsertable()) {
            terrainService.setTerrain(terrain);
            terrainService.insert();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Terrain terrain)  throws Exception{
        if (terrain.isUpdatable()) {
            terrainService.setTerrain(terrain);
            terrainService.update();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Update successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed update\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Terrain terrain)  throws Exception{
        if (terrain.isDeletable()) {
            terrainService.setTerrain(terrain);
            terrainService.delete();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Successfully deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
}