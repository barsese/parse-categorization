package omer.parse.categorization.service;

import lombok.AllArgsConstructor;
import omer.parse.categorization.dal.WordsRepository;
import omer.parse.categorization.model.Pharse;
import omer.parse.categorization.model.PharseWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PharseMatcherServiceImpl implements PharseMatcherService {
    private static Logger LOGGER = LoggerFactory.getLogger(PharseMatcherServiceImpl.class);

    private PharseGeneratorService pharseGeneratorService;
    private WordsRepository wordsRepository;

    /**
     * assuming two occurances of same keyword results return the earlier one
     * assuming each word can assiciate with one string from dict at most, at to the longest one
     */
    @Override
    public Map<String, Integer> matchPharse(String pharseStr) {
        LOGGER.info(String.format("match pharse againts repo: %s", pharseStr));
        Map<String, Integer> retMatchedPharses = new HashMap();
        if (null == pharseStr){
            return retMatchedPharses;
        }
        Pharse pharse = pharseGeneratorService.createPharse(pharseStr);
        Integer startIdx = 0;
        while(startIdx < pharse.getWords().size()){
            startIdx = matchingFromStartingIndex(pharse, retMatchedPharses, startIdx);
        }
        return retMatchedPharses;
    }

    private Integer matchingFromStartingIndex(Pharse pharse, Map<String, Integer> retMatchedPharses, Integer startIdx) {
        Map<Integer, PharseWord> st = new HashMap();
        for (int endIdx = startIdx + 1 ; endIdx <= pharse.getWords().size(); endIdx++){
            PharseWord pharseWord = pharseGeneratorService.combinePharseWord(pharse, startIdx, endIdx);
            if(wordsRepository.isWordExists(pharseWord.getWord())){
                st.put(endIdx, pharseWord);
            } else if(isWordNotExistsAsPartial(pharseWord)){
                break;
            }
        }
        Optional<Map.Entry<Integer, PharseWord>> maxOpt = st.entrySet().stream().max(Comparator.comparing(pharseWord -> pharseWord.getKey()));
        maxOpt.ifPresent(pharseWord -> {
            retMatchedPharses.putIfAbsent(pharseWord.getValue().getWord(), pharseWord.getValue().getStartingIdx());
        });
        return maxOpt.isPresent() ?
                maxOpt.get().getKey()  :
                startIdx + 1;
    }

    private boolean isWordNotExistsAsPartial(PharseWord pharseWord) {
        return !wordsRepository.isWordExistsAsPartial(pharseWord.getWord());
    }
}
