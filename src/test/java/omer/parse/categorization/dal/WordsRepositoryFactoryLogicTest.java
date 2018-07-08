package omer.parse.categorization.dal;

import omer.parse.categorization.utils.DictionaryFileUtils;
import omer.parse.categorization.utils.TestingData;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordsRepositoryFactoryLogicTest {
    private DictionaryFileUtils dictionaryFileUtils;
    private WordsRepositoryFactoryLogic serviceUnderTest;

    @Before
    public void setUp() throws Exception {
        dictionaryFileUtils = mock(DictionaryFileUtils.class);
        when(dictionaryFileUtils.readFile("dictionary.txt"))
                .thenReturn(TestingData.INSTANCE.getDictionary().stream().collect(Collectors.toList()));
        serviceUnderTest = new WordsRepositoryFactoryLogic(dictionaryFileUtils);
    }

    @Test
    public void createPartialDictionary() {
        Set<String> partialDictionary = serviceUnderTest.createPartialDictionary(TestingData.INSTANCE.getDictionary());
        assertTrue(partialDictionary.containsAll(TestingData.INSTANCE.getPartialDictionary()));
    }

    @Test
    public void readDictionary() {
        Set<String> strings = serviceUnderTest.readDictionary("dictionary.txt");
        assertTrue(strings.containsAll(TestingData.INSTANCE.getDictionary()));
    }
}