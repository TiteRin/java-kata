package com.kata.markovchain;

import java.util.List;

public interface TextPreprocessor {
    List<String> tokenize(String rawText);
}
