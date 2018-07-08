package omer.parse.categorization.utils;

import omer.parse.categorization.model.exception.PharseCategirizationRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DictionaryFileUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(DictionaryFileUtils.class);

    public List<String> readFile(String filePath){
        LOGGER.info(String.format("trying read dictionary file: %s", filePath));
        Path path;
        try {
            URL path1 = getClass().getClassLoader().getResource(filePath);
            if (path1 == null){
                LOGGER.error("no file found");
                throw new PharseCategirizationRuntimeException("no file found");
            }
            path = Paths.get(path1.toURI());
        } catch (URISyntaxException e) {
            LOGGER.error("no file found", e);
            throw new PharseCategirizationRuntimeException("uri exception", e);
        }
        if (!Files.exists(path)){
            LOGGER.error("dictionary file not found");
            throw new PharseCategirizationRuntimeException("dictionary file not found");
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            LOGGER.error("cant read file", e);
            throw new PharseCategirizationRuntimeException("cant read file", e);
        }
    }
}
