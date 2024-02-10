package com.example.client.controller.culture;

import com.example.client.model.culture.*;
import com.example.client.service.culture.*;
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
@RequestMapping("/cultures/types")
public class TypeCultureController {
    @Autowired
    TypeCultureService typeCultureService;

    @GetMapping
    public String getAll() throws Exception{
        Gson gson = new Gson();        
        return gson.toJson(typeCultureService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody TypeCulture typeCulture)  throws Exception{
        if (typeCulture.isInsertable()) {
            typeCultureService.setTypeCulture(typeCulture);
            typeCultureService.insert();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody TypeCulture typeCulture)  throws Exception{
        if (typeCulture.isUpdatable()) {
            typeCultureService.setTypeCulture(typeCulture);
            typeCultureService.update();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Update successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed update\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody TypeCulture typeCulture)  throws Exception{
        if (typeCulture.isDeletable()) {
            typeCultureService.setTypeCulture(typeCulture);
            typeCultureService.delete();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Successfully deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
}