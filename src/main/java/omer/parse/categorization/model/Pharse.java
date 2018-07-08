package omer.parse.categorization.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class Pharse {
    private List<PharseWord> words;

    public Pharse(List<PharseWord> pharseWords) {
        words = pharseWords;
    }
}
