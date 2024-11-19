package app.linguacards.controller;
import app.linguacards.dto.CardUpdateRequest;
import app.linguacards.model.Card;
import app.linguacards.service.CardService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("")
    public List<Card> getAllCards(){
        return this.cardService.getAllCardsByUser(1);
    }

    @GetMapping("{id}")
    public Optional<Card> getCardById(@PathVariable Integer id){
        return this.cardService.getCardById(id);
    }

    @GetMapping("{user_id}/learning")
    public List<Card> getLearningCards(@PathVariable Integer user_id){
        return this.cardService.getLearningCards(user_id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCardById(@PathVariable Integer id){
        this.cardService.deleteCardById(id);
    }

    @PutMapping("/update/{id}")
    public void updateCard(@PathVariable Integer id, @RequestBody CardUpdateRequest updateRequest) {
        boolean updated = cardService.updateCardById(id, updateRequest.getFront_text(), updateRequest.getBack_text(), updateRequest.getScore());

        if (!updated) {
            throw new RuntimeException("La mise à jour a échoué pour la carte avec id : " + id);
        }
    }

    @PutMapping("/updateScore/{id}")
    public void updateCardScore(@PathVariable Integer id, @RequestBody CardUpdateRequest updateRequest) {
        boolean updated = cardService.updateCardScore(id, updateRequest.getScore());

        if (!updated) {
            throw new RuntimeException("La mise à jour a échoué pour la carte avec id : " + id);
        }
    }
}
