package app.linguacards.repository;

import app.linguacards.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {

    @Query("{'user_id':  ?0}")
    List<Card> findByUser_id(String user_id);

    @Query("{'score': {'$lt': ?0}, 'user_id': ?1}")
    List<Card> findByScoreLessThanAndUser_id(int score, String userId);

    @Query(value = "{ 'score' : { $gte: 0, $lt: 6 }, 'user_id' : ?0 }", count = true)
    long countByScoreBetween0And5(String user_id);

    @Query(value = "{ 'score' : { $gte: 0, $lt: 6 }, 'user_id' : ?0 }", count = true)
    long countByScoreBetweenAndUser_id(int start, int end, String userId);
    @Query(value = "{ 'score' : {$gt: 10 }, 'user_id' : ?0 }", count = true)
    long countByScoreGreaterThanAndUser_id(int score, String userId);
}
