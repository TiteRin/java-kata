package com.kata.markovchain;

import com.kata.markovchain.builder.Builder;
import com.kata.markovchain.builder.Chain;
import com.kata.markovchain.builder.Model;
import com.kata.markovchain.builder.State;
import com.kata.markovchain.text.SimpleTextPreprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovTextGenerator {

    private final Model model;

    public MarkovTextGenerator(String text) {

        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
        List<String> tokens = preprocessor.tokenize(text);

        model = Builder.build(tokens);
    }

    public String generateText(int length) {

        Chain chain = new Chain(model, model.getRandomState(), new Random());
        List<String> generatedText = new ArrayList<>();

        for (int i = 0; i < length; i++) {

            if (!chain.hasNext()) {
                chain = new Chain(model, model.getRandomState(), new Random());
            }

            generatedText.add(chain.next());
        }

        return String.join(" ", generatedText);
    }
}
