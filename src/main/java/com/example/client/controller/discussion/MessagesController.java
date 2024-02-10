package com.example.client.controller.discussion;

import com.example.client.model.discussion.*;
import com.example.client.service.discussion.*;
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
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    MessagesService messagesService;

    @Autowired
    GeneraliteService generaliteService;

    // @GetMapping("/condition")
    // public String getCondition() throws Exception{
    //     messages c = new messages("C2","katsaka", new Typemessages("Type1", "Cereale"), 90);
    //     String condition = "";
    //     // condition = generaliteService.condition(condition, c.getNommessages(), "nommessages");
    //     condition = generaliteService.condition(condition, c.getTypemessages().getIdTypemessages(), "idTypemessages");
    //     condition = generaliteService.condition(condition, c.getTempsCroissance(), "tempsCroissance");
    //     return condition;
    // }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Messages messages)  throws Exception{
        if (messages.isInsertable()) {
            messagesService.setMessages(messages);
            messagesService.insert();
            String responseMessages = "{\"status\": \"success\", \"messages\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessages, HttpStatus.OK);
        } else {
            String responseMessages = "{\"status\": \"failed\", \"messages\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessages, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Messages messages)  throws Exception{
        if (messages.isDeletable()) {
            messagesService.setMessages(messages);
            messagesService.delete();
            String responseMessages = "{\"status\": \"success\", \"messages\": \"Successfully deleted\"}";
            return new ResponseEntity<>(responseMessages, HttpStatus.OK);
        } else {
            String responseMessages = "{\"status\": \"failed\", \"messages\": \"Failed deleted\"}";
            return new ResponseEntity<>(responseMessages, HttpStatus.UNAUTHORIZED);
        }
    }   
}