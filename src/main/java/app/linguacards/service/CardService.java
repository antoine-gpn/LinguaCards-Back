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

    public List<Card> getAllCardsByUser(Integer user_id){
        return cardRepository.findByUser_id(user_id);
    }

    public Optional<Card> getCardById(Integer id){
        return cardRepository.findById(id);
    }

    public List<Card> getLearningCards(Integer id){
        return cardRepository.findByScoreLessThanAndUser_id(5, id);
    }

    public void deleteCardById(Integer id){
        cardRepository.deleteById(id);
    }

    public boolean updateCardById(Integer id, String front_text, String back_text, Integer score) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {

            if (front_text != null) card.setFront_text(front_text);
            if (back_text != null) card.setBack_text(back_text);
            if (score != null) card.setScore(score);

            cardRepository.save(card);
            return true;
        }
        return false;
    }

    public boolean updateCardScore(Integer id, Integer score) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {

            if (score != null) card.setScore(score);
            cardRepository.save(card);
            return true;
        }
        return false;
    }

}
