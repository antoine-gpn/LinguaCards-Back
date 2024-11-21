package app.linguacards.controller;
import app.linguacards.dto.CardUpdateRequest;
import app.linguacards.model.Card;
import app.linguacards.repository.CardRepository;
import app.linguacards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("")
    public List<Card> getAllCards(){
        return this.cardService.getAllCardsByUser(1);
    }

    @GetMapping("{id}")
    public Optional<Card> getCardById(@PathVariable String id){
        return this.cardService.getCardById(id);
    }

    @GetMapping("{user_id}/learning")
    public List<Card> getLearningCards(@PathVariable Integer user_id){
        return this.cardService.getLearningCards(user_id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCardById(@PathVariable String id){
        this.cardService.deleteCardById(id);
    }

    @PutMapping("/update/{id}")
    public void updateCard(@PathVariable String id, @RequestBody CardUpdateRequest updateRequest) {
        boolean updated = cardService.updateCardById(id, updateRequest.getFront_text(), updateRequest.getBack_text(), updateRequest.getScore());

        if (!updated) {
            throw new RuntimeException("La mise à jour a échoué pour la carte avec id : " + id);
        }
    }

    @PutMapping("/updateScore/{id}")
    public void updateCardScore(@PathVariable String id, @RequestBody CardUpdateRequest updateRequest) {
        boolean updated = cardService.updateCardScore(id, updateRequest.getScore());

        if (!updated) {
            throw new RuntimeException("La mise à jour a échoué pour la carte avec id : " + id);
        }
    }

    @GetMapping("/stats")
    public Map<String, Long> getScoreStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("to learn", cardService.countToLearn());
        stats.put("learning", cardService.countLearning());
        stats.put("learned", cardService.countLearned());
        return stats;
    }

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody Card newCard){
        Card savedCard = this.cardRepository.save(newCard);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }
}
