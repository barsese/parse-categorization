package omer.parse.categorization.service;

import omer.parse.categorization.model.Pharse;
import omer.parse.categorization.model.PharseWord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PharseGeneratorServiceImpl implements PharseGeneratorService {
    @Override
    public Pharse createPharse(String pharse) {
        if (null == pharse){
            return new Pharse(new ArrayList<>());
        }
        List<PharseWord> words = new ArrayList<>();
        String[] splittedPharse = pharse.split("\\s");
        int totalLengthUpToHere = 0;
        for(int idx = 0; idx < splittedPharse.length ; idx++){
            String curWord = splittedPharse[idx];
            words.add(PharseWord.create(curWord,totalLengthUpToHere));
            totalLengthUpToHere += curWord.length() + 1;
        }
        return new Pharse(words);
    }

    @Override
    public PharseWord combinePharseWord(List<PharseWord> pharseWords) {
        return Objects.isNull(pharseWords) ||
                pharseWords.isEmpty() ?
                PharseWord.create("",-1) :
                PharseWord.create(createCombinedPharse(pharseWords),
                        pharseWords.get(0).getStartingIdx());
    }

    @Override
    public PharseWord combinePharseWord(Pharse pharse, int fromIdx, int toIdx) {
        return Objects.isNull(pharse) ||
                fromIdx > toIdx ||
                pharse.getWords().size() < toIdx ?
                PharseWord.create("",-1) :
                combinePharseWord(pharse.getWords().subList(fromIdx, toIdx));
    }

    private String createCombinedPharse(List<PharseWord> pharseWords) {
        return pharseWords.stream()
                .map(word -> word.getWord())
                .collect(Collectors.joining(" "));
    }
}
