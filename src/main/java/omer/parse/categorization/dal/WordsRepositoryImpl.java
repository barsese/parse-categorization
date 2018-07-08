package omer.parse.categorization.dal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import omer.parse.categorization.utils.DictionaryFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordsRepositoryImpl implements WordsRepository {
    private Set<String> dictionary;
    private Set<String> partialDicionary;

    @Autowired
    public WordsRepositoryImpl(@Value("${dictionary.filename:dictionary.txt}") String filaname,
                               WordsRepositoryFactoryLogic wordsRepositoryFactoryLogic
    ) {
        this.dictionary = wordsRepositoryFactoryLogic.readDictionary(filaname);
        this.partialDicionary = wordsRepositoryFactoryLogic.createPartialDictionary(dictionary);
    }

    @Override
    public boolean isWordExists(String parse) {
        return dictionary.contains(parse);
    }

    @Override
    public boolean isWordExistsAsPartial(String parse) {
        return partialDicionary.contains(parse);
    }
}
