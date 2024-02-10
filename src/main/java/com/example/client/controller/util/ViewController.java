package com.example.client.controller.util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.client.model.culture.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
@Controller
public class ViewController {

    @GetMapping("/redirect")
    public RedirectView redirect()
    {
        return new RedirectView("/hw");
    }
    @GetMapping("/hw")
    public String hello(Model model) {
        List<TypeCulture> productList = new ArrayList<>();
        productList.add(new TypeCulture("Tp1", "10.0"));
        productList.add(new TypeCulture("Tp2", "20.0"));
        productList.add(new TypeCulture("Tp3", "30.0"));
        model.addAttribute("message", "Hello, world!");
        model.addAttribute("products", productList);
        return "hello"; // Cela affichera le fichier hello.html dans le répertoire templates
    }
}
