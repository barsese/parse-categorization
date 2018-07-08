package omer.parse.categorization.service;

import omer.parse.categorization.dal.WordsRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PharseMatcherServiceImplTest {
    private PharseMatcherService serviceUnderTest;
    private PharseGeneratorService pharseGeneratorService;
    private WordsRepository wordsRepository;

    @Before
    public void setUp() throws Exception {
        wordsRepository = mock(WordsRepository.class);
        when(wordsRepository.isWordExists("Vice President")).thenReturn(true);
        when(wordsRepository.isWordExists("President")).thenReturn(true);
        when(wordsRepository.isWordExists("Sales")).thenReturn(true);
        when(wordsRepository.isWordExists("Marketing")).thenReturn(true);
        when(wordsRepository.isWordExistsAsPartial("Vice")).thenReturn(true);
        pharseGeneratorService = new PharseGeneratorServiceImpl();
        serviceUnderTest = new PharseMatcherServiceImpl(pharseGeneratorService, wordsRepository);
    }

    @Test
    public void processPharse() {
        Map<String, Integer> stringIntegerMap = serviceUnderTest.matchPharse("Vice President of Sales and Marketing");
        System.out.println(stringIntegerMap);
    }
}