package com.example.client.controller.util;

import com.example.client.model.*;
import com.example.client.model.util.*;
import com.example.client.model.culture.*;
import com.example.client.service.culture.*;
import com.example.client.model.parcelle.*;
import com.example.client.service.parcelle.*;
import com.example.client.model.terrain.*;
import com.example.client.service.terrain.*;

import com.example.client.service.connection.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class RouteController {

    @Autowired
    private EConnection dbConnect;
    @Autowired
    TypeCultureService typeCultureService;
    @Autowired
    CultureService cultureService;
    @Autowired
    ParcelleModeleService parcelleModeleService;
    @Autowired
    TerrainService terrainService;
    @Autowired
    private Generalite generalite;
    @GetMapping("/")
    public String index(Model model) {
        return "login"; 
    }
   @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "redirect:/dashboard/cultures/types";
    }
    @GetMapping("/dashboard/terrains")
    public String dashboardTerrain(Model model) {
        try {
            List<Terrain> terrains = terrainService.findAll();

            model.addAttribute("confirmation", "page/confirmationForm");
            model.addAttribute("sectionTable", "page/sectionTableTerrain");
            model.addAttribute("newEdit", "page/addNewTerrain");
            model.addAttribute("generalite", generalite);
            model.addAttribute("active", "terrain");
            model.addAttribute("terrains", terrains);

            return "modele"; 
        } catch (Exception e) {
            // Gérer l'exception appropriée, par exemple en journalisant l'erreur
            model.addAttribute("error", e.toString());
            return "error"; // Retourner une vue d'erreur
        }
    }
    @GetMapping("/dashboard/parcelles")
    public String dashboardParcelle(Model model) {
        try {
            List<ParcelleModele> parcelleModeles = parcelleModeleService.findAll();
            List<TypeCulture> typeCultures = typeCultureService.findAll();

            model.addAttribute("confirmation", "page/confirmationForm");
            model.addAttribute("sectionTable", "page/sectionTableParcelleModele");
            model.addAttribute("newEdit", "page/addNewParcelleModele");
            model.addAttribute("generalite", generalite);
            model.addAttribute("active", "parcelleModele");
            model.addAttribute("parcelleModeles", parcelleModeles);
            model.addAttribute("typeCultures", typeCultures);

            return "modele"; 
        } catch (Exception e) {
            // Gérer l'exception appropriée, par exemple en journalisant l'erreur
            model.addAttribute("error", e.toString());
            return "error"; // Retourner une vue d'erreur
        }
    }
    @GetMapping("/dashboard/parcelles/{id}")
    public String editParcelleModelePage(@PathVariable String id, Model model) {
        // Logique pour récupérer et retourner le contenu de la page en fonction de l'ID
        try {
            String content = "Contenu de la page avec l'ID " + id;
            String idParcelleModele = generalite.getSubstringFrom16(id);
            System.out.println("id: "+id);
            System.out.println("id-> "+idParcelleModele);
            model.addAttribute("idParcelleModele",idParcelleModele );
            List<ParcelleModele> parcelleModeles = parcelleModeleService.findAll();
            List<TypeCulture> typeCultures = typeCultureService.findAll();            
            model.addAttribute("confirmation", "page/confirmationForm");
            model.addAttribute("sectionTable", "page/sectionTableParcelleModele");
            model.addAttribute("newEdit", "page/editParcelleModele");
                model.addAttribute("generalite", generalite);
                model.addAttribute("active", "parcelleModele");
                model.addAttribute("idParcelleModele", idParcelleModele);
                model.addAttribute("typeCultures", typeCultures);
            return "modele";
        } catch (Exception e) {
            // Gérer l'exception appropriée, par exemple en journalisant l'erreur
            model.addAttribute("error", e.toString());
            return "error"; // Retourner une vue d'erreur
        }
    }
    @GetMapping("/dashboard/cultures")
    public String dashboardCulture(Model model) {
        try {
            List<Culture> cultures = cultureService.findAll();
            List<TypeCulture> typeCultures = typeCultureService.findAll();

            model.addAttribute("confirmation", "page/confirmationForm");
            model.addAttribute("sectionTable", "page/sectionTableCulture");
            model.addAttribute("newEdit", "page/addNewCulture");
            model.addAttribute("generalite", generalite);
            model.addAttribute("active", "culture");
            model.addAttribute("cultures", cultures);
            model.addAttribute("typeCultures", typeCultures);

            return "modele"; 
        } catch (Exception e) {
            // Gérer l'exception appropriée, par exemple en journalisant l'erreur
            model.addAttribute("error", e.toString());
            return "error"; // Retourner une vue d'erreur
        }
    }
    @GetMapping("/dashboard/cultures/{id}")
    public String editCulturePage(@PathVariable String id, Model model) {
        // Logique pour récupérer et retourner le contenu de la page en fonction de l'ID
        try {
            String content = "Contenu de la page avec l'ID " + id;
            String idCulture = generalite.getSubstringFrom16(id);
            System.out.println("id: "+id);
            System.out.println("id-> "+idCulture);
            model.addAttribute("idCulture",idCulture );
            List<Culture> cultures = cultureService.findAll();
            List<TypeCulture> typeCultures = typeCultureService.findAll();            
            model.addAttribute("confirmation", "page/confirmationForm");
            model.addAttribute("sectionTable", "page/sectionTableCulture");
            model.addAttribute("newEdit", "page/editCulture");
                model.addAttribute("generalite", generalite);
                model.addAttribute("active", "typeCulture");
                model.addAttribute("cultures", cultures);
                model.addAttribute("typeCultures", typeCultures);
            return "modele";
        } catch (Exception e) {
            // Gérer l'exception appropriée, par exemple en journalisant l'erreur
            model.addAttribute("error", e.toString());
            return "error"; // Retourner une vue d'erreur
        }
    }
    @GetMapping("/dashboard/cultures/types")
    public String dashboardType(Model model) {
        try {
            List<TypeCulture> typeCultures = typeCultureService.findAll();
            model.addAttribute("confirmation", "page/confirmationForm");
            model.addAttribute("sectionTable", "page/sectionTableType");
            model.addAttribute("newEdit", "page/addNewType");
            model.addAttribute("generalite", generalite);
            model.addAttribute("active", "typeCulture");
            model.addAttribute("typeCultures", typeCultures);
            return "modele"; 
        } catch (Exception e) {
            // Gérer l'exception appropriée, par exemple en journalisant l'erreur
            model.addAttribute("error", e.toString());
            return "error"; // Retourner une vue d'erreur
        }
    }
    // @ResponseBody
    @GetMapping("/dashboard/cultures/types/{id}")
    public String editTypePage(@PathVariable String id, Model model) {
        // Logique pour récupérer et retourner le contenu de la page en fonction de l'ID
        try {
            String content = "Contenu de la page avec l'ID " + id;
            String idType = generalite.getSubstringFrom16(id);
            System.out.println("id: "+id);
            System.out.println("id-> "+idType);
            model.addAttribute("idTypeCulture",idType );
            List<TypeCulture> typeCultures = typeCultureService.find();
            model.addAttribute("confirmation", "page/confirmationForm");
            model.addAttribute("sectionTable", "page/sectionTableType");
            model.addAttribute("newEdit", "page/editType");
                model.addAttribute("generalite", generalite);
                model.addAttribute("active", "typeCulture");
                model.addAttribute("typeCultures", typeCultures);
            return "modele";
        } catch (Exception e) {
            // Gérer l'exception appropriée, par exemple en journalisant l'erreur
            model.addAttribute("error", e.toString());
            return "error"; // Retourner une vue d'erreur
        }
    }
    // @GetMapping("/login")
    // public String login(@RequestParam("username") String username, 
    //                     @RequestParam("password") String password) {
    //     // Traiter les données du formulaire ici
    //     return "redirect:/success"; // Rediriger vers une page de succès
    // }
}
