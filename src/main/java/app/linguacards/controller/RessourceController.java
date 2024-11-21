package app.linguacards.controller;

import app.linguacards.model.Ressource;
import app.linguacards.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ressources")
public class RessourceController {

    @Autowired
    private RessourceService ressourceService;

    @GetMapping("/{id}")
    public List<Ressource> getAllRessources(@PathVariable Integer id){
        return this.ressourceService.getAllRessources(id);
    }
}
