package com.example.client.controller.util;

import com.example.client.model.*;
import com.example.client.service.connection.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoClient;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/dbinfo")
public class DBController {

    @Autowired
    private EConnection dbConnect;
    
    @GetMapping("/psql")
    public String getPrimaryDatabaseInfo() {
        String response = "";
        try (Connection connection = dbConnect.getConnection()) {
            response = "Connected to database: " + connection.getMetaData().getURL();
            connection.close();
        } catch (SQLException e) {
            return "Error connecting to database: " + e.getMessage();
        }
        return response;
    }

    @GetMapping("/rpsql")
    public String getSecondaryDatabaseInfo() {
        String response = "";
        try (Connection connection = dbConnect.getConnectionRailway()) {
            response = "Connected to database: " + connection.getMetaData().getURL();
            connection.close();
        } catch (SQLException e) {
            return "Error connecting to database: " + e.getMessage();
        }
        return response;
    }

    @GetMapping("/mongo")
    public String getMongoDBInfo() {
        String response = "";
        try {
            MongoClient connection = dbConnect.getMongoClient();
            response = "Connected to mongo: " + connection;
            connection.close();
        } catch (Exception e) {
            return "Error connecting to database: " + e.getMessage();
        }
        return response;
    }
}
