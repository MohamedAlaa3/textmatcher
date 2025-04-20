package com.evision.textmatcher.model;

public class MatchResult {
    private final String fileName;
    private final double score;

    public MatchResult(String fileName, double score) {
        this.fileName = fileName;
        this.score = score;
    }

    public String getFileName() {
        return fileName;
    }

    public double getScore() {
        return score;
    }
}