package com.evision.textmatcher.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SimilarityCalculatorService {
    public double calculate(Set<String> baseWords, Set<String> otherWords) {
        if (baseWords.isEmpty()) return 0.0;
        if (otherWords.isEmpty()) return 0.0;
        if (baseWords.hashCode() == otherWords.hashCode()) return 100.0;
        long matched = 0;
        for (String word : otherWords) {
            if (baseWords.contains(word)) {
                matched++;
            }
        }
        return 100.0 * matched / baseWords.size();
    }
}