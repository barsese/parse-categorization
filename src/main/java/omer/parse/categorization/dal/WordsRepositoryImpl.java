package omer.parse.categorization.dal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * might use memdb or fast db as well..
 */
@Service
public class WordsRepositoryImpl implements WordsRepository {
    private static Logger LOGGER = LoggerFactory.getLogger(WordsRepositoryImpl.class);

    private Set<String> dictionary;
    private Set<String> partialDicionary;

    /**
     * dictionary file from resources, filename from env or config (prioritized)
     */
    @Autowired
    public WordsRepositoryImpl(@Value("${dictionary.filename:dictionary.txt}") String filanameConfig,
                               WordsRepositoryFactoryLogic wordsRepositoryFactoryLogic,
                               Environment env
    ) {
        String filename = env.getProperty("dictionary.filename", filanameConfig);
        this.dictionary = wordsRepositoryFactoryLogic.readDictionary(filename);
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
