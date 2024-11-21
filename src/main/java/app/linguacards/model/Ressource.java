package app.linguacards.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ressource_cards")
@Getter
@Setter
public class Ressource {

    @Id
    private Integer id;
    private Integer ressource_pack_id;
    private String lang;
    private String front_text;
    private String back_text;

    public Ressource(){

    }
}
