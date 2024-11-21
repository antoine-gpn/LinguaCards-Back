package app.linguacards.repository;

import app.linguacards.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {

    @Query("{'user_id':  ?0}")
    List<Card> findByUser_id(Integer user_id);

    @Query("{'score': {'$lt': ?0}, 'user_id': ?1}")
    List<Card> findByScoreLessThanAndUser_id(Integer score, Integer userId);

    @Query(value = "{ 'score' : { $gte: 0, $lt: 6 } }", count = true)
    long countByScoreBetween0And5();

    long countByScoreBetween(int start, int end);
    long countByScoreGreaterThan(int score);
}
