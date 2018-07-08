package omer.parse.categorization.dal;

import lombok.AllArgsConstructor;
import omer.parse.categorization.utils.DictionaryFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WordsRepositoryFactoryLogic {
    private DictionaryFileUtils dictionaryFileUtils;

    public Set<String> createPartialDictionary(Set<String> dictionary) {
        return dictionary.stream().filter(word -> word.contains(" "))
                .map(word -> Arrays.asList(word.split("\\s")))
                .map(this::wordArrayToCummolativeArray)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    public Set<String> readDictionary(String filaname) {
        return new HashSet(dictionaryFileUtils.readFile(filaname));
    }

    private List<String> wordArrayToCummolativeArray(List<String> words){
        List<String> res = new ArrayList<>();
        Optional<String> fullTerm = words.stream().reduce((a, b) -> {
            res.add(a);
            return a + " " + b;
        });
        fullTerm.ifPresent(res::add);
        return res;
    }
}
