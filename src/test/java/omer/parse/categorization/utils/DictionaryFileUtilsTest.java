package omer.parse.categorization.utils;

import omer.parse.categorization.model.exception.PharseCategirizationRuntimeException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DictionaryFileUtilsTest {
    private DictionaryFileUtils serviceUnderTest;

    @Before
    public void setUp() throws Exception {
        serviceUnderTest = new DictionaryFileUtils();
    }

    @Test
    public void readFile() {
        PharseCategirizationRuntimeException exception = null;
        try{
            serviceUnderTest.readFile("sadas.txt");
        } catch (PharseCategirizationRuntimeException e){
            exception = e;
        }
        assertNotNull(exception);

        List<String> strings = serviceUnderTest.readFile("dictionary.txt");
        assertEquals(9,strings.size());

    }
}