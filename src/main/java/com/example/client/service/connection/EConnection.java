package com.example.client.service.connection;

import com.example.client.configuration.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import com.mongodb.client.MongoClient;

@Service
public class EConnection {

    @Autowired
    @Qualifier("primaryDataSource") //bean configuration.PrimaryDataSource
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("secondaryDataSource") //bean configuration.SecondaryDataSource
    private DataSource secondaryDataSource;
    
    @Autowired
    private MongoDBConfiguration mongoDBConf;

    public Connection getConnection() throws SQLException {
        // return primaryDataSource.getConnection();
        return secondaryDataSource.getConnection();

    }

    public Connection getConnectionRailway() throws SQLException {
        return secondaryDataSource.getConnection();
    }

    public MongoClient getMongoClient() {
        return mongoDBConf.mongoClient();
    }
}

