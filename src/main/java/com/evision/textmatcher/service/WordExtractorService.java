package com.evision.textmatcher.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WordExtractorService {
    public Set<String> extractWords(Set<String> lines) {
        if (lines == null) {
            return new HashSet<>();
        }
        Set<String> words = new HashSet<>();

        for (String line : lines) {
            String[] parts = line.split("\\W+");

            for (String word : parts) {
                if (!word.isEmpty() && word.matches("[a-zA-Z]+")) {
                    words.add(word.toLowerCase());
                }
            }
        }

        return words;
    }
}