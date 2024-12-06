package app.linguacards.service;

import app.linguacards.model.Card;
import app.linguacards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCardsByUser(String user_id){
        return cardRepository.findByUser_id(user_id);
    }

    public Optional<Card> getCardById(String id){
        return cardRepository.findById(id);
    }

    public List<Card> getLearningCards(String id){
        return cardRepository.findByScoreLessThanAndUser_id(5, id);
    }

    public void deleteCardById(String id){
        cardRepository.deleteById(id);
    }

    public boolean updateCardById(String id, String front_text, String back_text, Integer score, String image) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {

            if (front_text != null) card.setFront_text(front_text);
            if (back_text != null) card.setBack_text(back_text);
            if (score != null) card.setScore(score);
            if (image != null) card.setImage(image);

            cardRepository.save(card);
            return true;
        }
        return false;
    }

    public boolean updateCardScore(String id, Integer score) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {

            if (score != null) card.setScore(score);
            cardRepository.save(card);
            return true;
        }
        return false;
    }

    public long countToLearn(String user_id) {
        return cardRepository.countByScoreBetween0And5(user_id);
    }

    public long countLearning(String user_id) {
        return cardRepository.countByScoreBetweenAndUser_id(5, 11, user_id);
    }

    public long countLearned(String user_id) {
        return cardRepository.countByScoreGreaterThanAndUser_id(10, user_id);
    }

}
