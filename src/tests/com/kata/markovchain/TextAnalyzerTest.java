package com.kata.markovchain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzerTest {


    @Test
    void analyze_returns_ListOfWordsFromText() {
        String text = "Hello World";
        TextAnalyzer textAnalyzer = new TextAnalyzer(text);
        textAnalyzer.analyze();
    }
}