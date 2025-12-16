package com.kata.markovchain.text;

import java.util.List;

public class SimpleTextPreprocessor implements TextPreprocessor {
    @Override
    public List<String> tokenize(String rawText) {

        if (rawText.trim().isEmpty()) {
            return List.of();
        }

        return List.of(
                rawText
                        .toLowerCase()
                        .split("[^\\p{L}\\p{N}]+")
        );
    }
}
