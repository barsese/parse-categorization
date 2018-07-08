package omer.parse.categorization.controller;

import lombok.AllArgsConstructor;
import omer.parse.categorization.service.PharseMatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PharseController {
    private static Logger LOGGER = LoggerFactory.getLogger(PharseController.class);
    private PharseMatcherService pharseMatcherService;

    @RequestMapping("/categorize")
    public Map<String, Integer> matchPharse(@RequestParam("phrase") String pharse) {
        LOGGER.info(String.format("match pharse: %s", pharse));
        return pharseMatcherService.matchPharse(pharse);
    }
}
