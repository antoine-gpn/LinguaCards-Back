package app.linguacards.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
@Getter
@Setter
public class Card {

    @Id
    private String id;
    private Integer user_id;
    private String front_text;
    private String back_text;
    private Integer score;

    public Card(){

    }

    public Card(String front_text, String back_text){
        this.front_text = front_text;
        this.back_text = back_text;
    }

}
