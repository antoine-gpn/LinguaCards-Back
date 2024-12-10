package app.linguacards.repository;

import app.linguacards.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("{ 'user_id': ?0, " +
            "'score': { $gte: ?#{#minScore != null ? #minScore : {$exists: true}}, " +
            "$lte: ?#{#maxScore != null ? #maxScore : {$exists: true}} }, " +
            "$or: [ { 'front_text': { $regex: ?3, $options: 'i' } }, { 'back_text': { $regex: ?3, $options: 'i' } } ] }")
    List<Card> findByUserIdScoreRangeAndText(@Param("userId") String userId, @Param("minScore") Integer minScore, @Param("maxScore") Integer maxScore, @Param("text") String text);

    @Query("{ 'user_id': ?0, $or: [ { 'front_text': { $regex: ?1, $options: 'i' } }, { 'back_text': { $regex: ?1, $options: 'i' } } ] }")
    List<Card> findByUserIdAndText(String userId, String text);


    @Query("{ 'user_id': ?0, 'score': { $gte: ?1, $lte: ?2 }, $or: [ { 'front_text': { $regex: ?3, $options: 'i' } }, { 'back_text': { $regex: ?3, $options: 'i' } } ] }")
    List<Card> findByUserIdAndScoreRangeAndText(String userId, int minScore, int maxScore, String text);

    @Query("{ 'user_id': ?0, 'score': { $gte: ?1 }, $or: [ { 'front_text': { $regex: ?2, $options: 'i' } }, { 'back_text': { $regex: ?2, $options: 'i' } } ] }")
    List<Card> findByUserIdAndMinScoreAndText(String userId, int minScore, String text);
}
