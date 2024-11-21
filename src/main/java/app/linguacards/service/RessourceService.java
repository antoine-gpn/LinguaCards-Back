package app.linguacards.service;

import app.linguacards.model.Card;
import app.linguacards.model.Ressource;
import app.linguacards.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessourceService {

    @Autowired
    private RessourceRepository ressourceRepository;

    public List<Ressource> getAllRessources(Integer id){
        return this.ressourceRepository.findByRessource_pack_id(id);
    }
}
