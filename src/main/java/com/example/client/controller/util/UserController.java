package com.example.client.controller.util;

import com.example.client.model.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final List<Users> users;
    @GetMapping("/hello")
    public String direBonjour() {
        return "Bonjour, bienvenue dans votre premier web service Spring Boot!";
    }
    public UserController() {
        // Initialiser la liste d'utilisateurs
        users = new ArrayList<>();
        users.add(new Users(1, "John Doe", 30));
        users.add(new Users(2, "Jane Doe", 25));
        users.add(new Users(3, "Bob Smith", 40));
    }

    @GetMapping
    public String getAllUsersAsJson() {
        // Utiliser Gson pour sérialiser la liste en format JSON
        Gson gson = new Gson();
        return gson.toJson(users);
    }
    @GetMapping("/{userId}")
    public String getUserByIdAsJson(@PathVariable int userId) {
        // Rechercher l'utilisateur par ID
        Users user = users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElse(null);

        // Si l'utilisateur est trouvé, le sérialiser en format JSON
        if (user != null) {
            Gson gson = new Gson();
            return gson.toJson(user);
        } else {
            // Si l'utilisateur n'est pas trouvé, renvoyer une réponse vide ou un message d'erreur
            return "{}"; // ou vous pouvez renvoyer un message d'erreur JSON approprié
        }
    }
}