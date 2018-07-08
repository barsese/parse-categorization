package omer.parse.categorization.dal;

import omer.parse.categorization.utils.TestingData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordsRepositoryImplTest {
    private WordsRepositoryImpl serviceUnderTest;
    private WordsRepositoryFactoryLogic wordsRepositoryFactoryLogic;
    private Environment env;
    @Before
    public void setUp() throws Exception {
        wordsRepositoryFactoryLogic = mock(WordsRepositoryFactoryLogic.class);
        when(wordsRepositoryFactoryLogic.readDictionary(any())).thenReturn(TestingData.INSTANCE.getDictionary());
        when(wordsRepositoryFactoryLogic.createPartialDictionary(TestingData.INSTANCE.getDictionary()))
                .thenReturn(TestingData.INSTANCE.getPartialDictionary());
        env = mock(Environment.class);
        when(env.getProperty("dictionary.filename", "dictionary.txt")).thenReturn("dictionary.txt");
        serviceUnderTest = new WordsRepositoryImpl("dictionary.txt", wordsRepositoryFactoryLogic, env);
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