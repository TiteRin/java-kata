package com.kata.markovchain;

import com.kata.markovchain.text.SimpleTextPreprocessor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTextPreprocessorTest {

    @Test
    void tokenize_returns_ListOfWords() {
        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
        assertEquals(List.of("hello", "world"), preprocessor.tokenize("hello world"));
    }

    @Test
    void tokenize_returns_EmptyListIfTextIsEmpty() {
        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
        assertTrue(preprocessor.tokenize("").isEmpty());
    }

    @Test
    void tokenize_returns_accentuatedWords() {
        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
        assertEquals(List.of("quelle", "œuvre", "élégante"), preprocessor.tokenize("Quelle œuvre élégante!"));
    }

    @Test
    void tokenize_returns_ListOfWordsInLowerCase() {
        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
        assertEquals(List.of("quelle", "belle", "journée"), preprocessor.tokenize("QUELLE BELLE JOURNÉE"));
    }

    @Test
    void tokenize_return_ListOfWordsWithoutPunctuation() {
        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
        assertEquals(List.of("hello", "world"), preprocessor.tokenize("hello, world!"));
    }
}