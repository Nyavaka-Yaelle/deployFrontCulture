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
@RequestMapping("/discussions")
public class DiscussionController {
    @Autowired
    DiscussionService discussionService;

    @Autowired
    GeneraliteService generaliteService;

    // @GetMapping("/condition")
    // public String getCondition() throws Exception{
    //     discussion c = new discussion("C2","katsaka", new Typediscussion("Type1", "Cereale"), 90);
    //     String condition = "";
    //     // condition = generaliteService.condition(condition, c.getNomdiscussion(), "nomdiscussion");
    //     condition = generaliteService.condition(condition, c.getTypediscussion().getIdTypediscussion(), "idTypediscussion");
    //     condition = generaliteService.condition(condition, c.getTempsCroissance(), "tempsCroissance");
    //     return condition;
    // }
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Discussion discussion)  throws Exception{
        if (discussion.isInsertable()) {
            discussionService.setDiscussion(discussion);
            discussionService.insert();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Inserted successfully\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Insertion failed\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Discussion discussion)  throws Exception{
        if (discussion.isDeletable()) {
            discussionService.setDiscussion(discussion);
            discussionService.delete();
            String responseMessage = "{\"status\": \"success\", \"message\": \"Successfully deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            String responseMessage = "{\"status\": \"failed\", \"message\": \"Failed deleted\"}";
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
    }   
}