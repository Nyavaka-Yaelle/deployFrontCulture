package com.example.client.controller.culture;

import com.example.client.model.culture.*;
import com.example.client.service.culture.*;
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
@RequestMapping("/cultures")
public class CultureController {
    @Autowired
    CultureService cultureService;

    @Autowired
    GeneraliteService generaliteService;

    @GetMapping
    public String getAll() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(cultureService.findAll());
    }
    // @GetMapping("/condition")
    // public String getCondition() throws Exception{
    //     Culture c = new Culture("C2","katsaka", new TypeCulture("Type1", "Cereale"), 90);
    //     String condition = "";
    //     // condition = generaliteService.condition(condition, c.getNomCulture(), "nomCulture");
    //     condition = generaliteService.condition(condition, c.getTypeCulture().getIdTypeCulture(), "idTypeCulture");
    //     condition = generaliteService.condition(condition, c.getTempsCroissance(), "tempsCroissance");
    //     return condition;
    // }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Culture culture)  throws Exception{
        if (culture.isInsertable()) {
            cultureService.setCulture(culture);
            cultureService.insert();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Culture culture)  throws Exception{
        if (culture.isUpdatable()) {
            cultureService.setCulture(culture);
            cultureService.update();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Update successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed update\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Culture culture)  throws Exception{
        if (culture.isDeletable()) {
            cultureService.setCulture(culture);
            cultureService.delete();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Successfully deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }   
}