package omer.parse.categorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "create")
@Data
public class PharseWord {
    private String word;
    private int startingIdx;
}
