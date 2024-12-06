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
    private String user_id;
    private String front_text;
    private String back_text;
    private String image;
    private Integer score;

    public Card(){

    }

    public Card(String user_id, String front_text, String back_text, int score, String image){
        this.user_id = user_id;
        this.front_text = front_text;
        this.back_text = back_text;
        this.score = score;
        this.image = image;
    }

}
