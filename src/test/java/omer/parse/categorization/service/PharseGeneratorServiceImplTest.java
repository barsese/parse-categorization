package omer.parse.categorization.service;

import omer.parse.categorization.model.Pharse;
import omer.parse.categorization.model.PharseWord;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PharseGeneratorServiceImplTest {
    private PharseGeneratorService serviceUnderTest;

    @Before
    public void setUp() throws Exception {
        serviceUnderTest = new PharseGeneratorServiceImpl();
    }

    @Test
    public void createPharse() {
        Pharse pharse = serviceUnderTest.createPharse("omer bar");
        assertEquals(2, pharse.getWords().size());
        assertEquals(PharseWord.create("omer",0), pharse.getWords().get(0));
        assertEquals(PharseWord.create("bar",5), pharse.getWords().get(1));
    }

    @Test
    public void combinePharseWord() {
        Pharse pharse = serviceUnderTest.createPharse("omer bar");
        final PharseWord pharseWord1 = serviceUnderTest.combinePharseWord(pharse, 0, 1);
        assertEquals("omer", pharseWord1.getWord());
        assertEquals(0, pharseWord1.getStartingIdx());
        final PharseWord pharseWord2 = serviceUnderTest.combinePharseWord(pharse, 0, 2);
        assertEquals("omer bar", pharseWord2.getWord());
        assertEquals(0, pharseWord2.getStartingIdx());

        final PharseWord pharseWord3 = serviceUnderTest.combinePharseWord(pharse, 1, 2);
        assertEquals("bar", pharseWord3.getWord());
        assertEquals(5, pharseWord3.getStartingIdx());
    }

}