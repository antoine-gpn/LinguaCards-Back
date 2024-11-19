package app.linguacards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUpdateRequest {
    private String front_text;
    private String back_text;
    private Integer score;

}
