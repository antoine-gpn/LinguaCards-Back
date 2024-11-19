package app.linguacards.repository;

import app.linguacards.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, Integer> {

    @Query("{'user_id':  ?0}")
    List<Card> findByUser_id(Integer user_id);

    @Query("{'score': {'$lt': ?0}, 'user_id': ?1}")
    List<Card> findByScoreLessThanAndUser_id(Integer score, Integer userId);

}
