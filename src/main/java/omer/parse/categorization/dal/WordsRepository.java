package omer.parse.categorization.dal;

public interface WordsRepository {
    boolean isWordExists(String parse);
    boolean isWordExistsAsPartial(String parse);
}
