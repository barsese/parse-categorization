package omer.parse.categorization.service;

import omer.parse.categorization.model.Pharse;
import omer.parse.categorization.model.PharseWord;

import java.util.List;

public interface PharseGeneratorService {
    Pharse createPharse(String pharse);

    /**
     * combine >= 1 pharses into one
     */
    PharseWord combinePharseWord(List<PharseWord> pharseWords);
    /**
     * combine >= 1 pharses into one
     */
    PharseWord combinePharseWord(Pharse pharse, int fromIdx, int toIdx);
}
