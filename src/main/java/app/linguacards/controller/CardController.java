package app.linguacards.controller;
import app.linguacards.dto.CardUpdateRequest;
import app.linguacards.model.Card;
import app.linguacards.repository.CardRepository;
import app.linguacards.service.CardService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/user/{id}")
    public List<Card> getAllCards(@PathVariable String id){
        return this.cardService.getAllCardsByUser(id);
    }

    @GetMapping("{id}")
    public Optional<Card> getCardById(@PathVariable String id){
        return this.cardService.getCardById(id);
    }

    @GetMapping("{user_id}/learning")
    public List<Card> getLearningCards(@PathVariable String user_id){
        return this.cardService.getLearningCards(user_id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCardById(@PathVariable String id){
        this.cardService.deleteCardById(id);
    }

    @PutMapping("/update/{id}")
    public void updateCard(@PathVariable String id, @RequestBody CardUpdateRequest updateRequest) {
        boolean updated = cardService.updateCardById(id, updateRequest.getFront_text(), updateRequest.getBack_text(), updateRequest.getScore(), updateRequest.getImage());

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

    @GetMapping("/stats/{id}")
    public Map<String, Long> getScoreStats(@PathVariable String id) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("to learn", cardService.countToLearn(id));
        stats.put("learning", cardService.countLearning(id));
        stats.put("learned", cardService.countLearned(id));
        return stats;
    }

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody Card newCard){
        Card savedCard = this.cardRepository.save(newCard);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @PostMapping("/addBeginCards/{user_id}")
    public void addBeginCards(@PathVariable String user_id) throws IOException, URISyntaxException {
        //Getting the json file containing beggining cards
        Path path = Paths.get(Objects.requireNonNull(CardController.class.getClassLoader().getResource("cards.json")).toURI());
        String fileContent = new String(Files.readAllBytes(path));

        //Json conversion from json string
        JSONObject jsonObject = new JSONObject(fileContent);
        JSONArray cardsArray = jsonObject.getJSONArray("cards");

        //Loop trough cards objects on json
        for (int i = 0; i < cardsArray.length(); i++) {
            JSONObject currentCard = cardsArray.getJSONObject(i);

            //Getting values from json
            String front_text = currentCard.getString("front_text");
            String back_text = currentCard.getString("back_text");
            String image = currentCard.getString("image");

            //Convert datas to a Card object and save
            Card card = new Card(user_id, front_text, back_text, 0, image);
            this.cardRepository.save(card);
        }
    }
}
