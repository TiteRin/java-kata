package com.kata.markovchain.text;

import java.util.List;

public interface TextPreprocessor {
    List<String> tokenize(String rawText);
}
