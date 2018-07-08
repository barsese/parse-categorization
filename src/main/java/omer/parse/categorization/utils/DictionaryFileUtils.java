package omer.parse.categorization.utils;

import omer.parse.categorization.model.exception.PharseCategirizationRuntimeException;
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
    public List<String> readFile(String filePath){
        Path path;
        try {
            URL path1 = getClass().getClassLoader().getResource(filePath);
            if (path1 == null){
                throw new PharseCategirizationRuntimeException("no file found");
            }
            path = Paths.get(path1.toURI());
        } catch (URISyntaxException e) {
            throw new PharseCategirizationRuntimeException("uri exception", e);
        }
        if (!Files.exists(path)){
            throw new PharseCategirizationRuntimeException("dictionary file not found");
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new PharseCategirizationRuntimeException("cant read file", e);
        }
    }
}
