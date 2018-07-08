package omer.parse.categorization.dal;

import omer.parse.categorization.utils.TestingData;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordsRepositoryImplTest {
    private WordsRepositoryImpl serviceUnderTest;
    private WordsRepositoryFactoryLogic wordsRepositoryFactoryLogic;
    @Before
    public void setUp() throws Exception {
        wordsRepositoryFactoryLogic = mock(WordsRepositoryFactoryLogic.class);
        when(wordsRepositoryFactoryLogic.readDictionary(any())).thenReturn(TestingData.INSTANCE.getDictionary());
        when(wordsRepositoryFactoryLogic.createPartialDictionary(TestingData.INSTANCE.getDictionary()))
                .thenReturn(TestingData.INSTANCE.getPartialDictionary());

        serviceUnderTest = new WordsRepositoryImpl("dictionary.txt", wordsRepositoryFactoryLogic);
    }

    @Test
    public void isWordExists() {
        assertTrue(serviceUnderTest.isWordExists("Vise President"));
        assertFalse(serviceUnderTest.isWordExistsAsPartial("omer"));
    }

    @Test
    public void isWordExistsAsPartial() {
        assertFalse(serviceUnderTest.isWordExists("Vise"));
        assertTrue(serviceUnderTest.isWordExistsAsPartial("Vise"));
    }
}