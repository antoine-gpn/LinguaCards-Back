package app.linguacards.repository;

import app.linguacards.model.Card;
import app.linguacards.model.Ressource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RessourceRepository extends MongoRepository<Ressource, Integer> {

    @Query("{'ressource_pack_id':  ?0}")
    List<Ressource> findByRessource_pack_id(Integer ressource_pack_id);
}
