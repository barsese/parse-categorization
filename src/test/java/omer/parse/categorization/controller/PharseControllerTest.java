package omer.parse.categorization.controller;

import omer.parse.categorization.service.PharseMatcherService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PharseControllerTest {
    private PharseController serviceUnderTest;
    private PharseMatcherService pharseMatcherService;
    private String pharse;
    private Map<String, Integer> res;

    @Before
    public void setUp() throws Exception {
        pharse = "omer bar";
        pharseMatcherService = mock(PharseMatcherService.class);
        res = new HashMap<>();
        res.put("omer",0);
        res.put("bar",5);
        when(pharseMatcherService.matchPharse(pharse)).thenReturn(res);
        serviceUnderTest = new PharseController(pharseMatcherService);
    }

    @Test
    public void processPharse() {
        Map<String, Integer> stringIntegerMap = serviceUnderTest.matchPharse(pharse);
        assertEquals(res, stringIntegerMap);
        verify(pharseMatcherService).matchPharse(pharse);
    }
}