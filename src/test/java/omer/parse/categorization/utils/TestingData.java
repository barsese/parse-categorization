package omer.parse.categorization.utils;

import lombok.Getter;
import omer.parse.categorization.model.Pharse;
import omer.parse.categorization.service.PharseGeneratorService;
import omer.parse.categorization.service.PharseGeneratorServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Getter
public enum TestingData {
    INSTANCE;

    private String pharseStr = "Vice President of Sales and Marketing";
    private Set<String> dictionary;
    private Set<String> partialDictionary;
    private Pharse pharse;
    private PharseGeneratorService pharseGeneratorService;


    TestingData(){
        dictionary = new HashSet<>();
        dictionary.add("President");
        dictionary.add("Vise President");
        dictionary.add("Sales");
        dictionary.add("Marketing");
        dictionary.add("IT");
        dictionary.add("CFO");
        dictionary.add("CTO");
        dictionary.add("Banking");
        dictionary.add("eComerce");

        partialDictionary = new HashSet<>();
        partialDictionary.add("Vise");
        partialDictionary.add("Vise President");

        pharseGeneratorService = new PharseGeneratorServiceImpl();
    }

}
