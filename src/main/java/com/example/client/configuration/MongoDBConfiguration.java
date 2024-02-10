package com.example.client.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoTemplate mongoTemplate() {
        SimpleMongoClientDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName());
        return new MongoTemplate(mongoDbFactory);
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    private String getDatabaseName() {
        // Extraire le nom de la base de données de l'URI MongoDB
        // Exemple d'URI : mongodb://localhost:27017/mydb
        String[] uriParts = mongoUri.split("/");
        return uriParts[uriParts.length - 1];
    }
}
