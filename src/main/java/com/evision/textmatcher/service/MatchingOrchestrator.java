package com.evision.textmatcher.service;

import com.evision.textmatcher.config.MatcherProperties;
import com.evision.textmatcher.model.MatchResult;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MatchingOrchestrator {

    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final MatcherProperties props;
    private final FileLoaderService fileLoader;
    private final WordExtractorService wordExtractor;
    private final SimilarityCalculatorService calculator;
    private List<MatchResult> results = null;


    public MatchingOrchestrator(MatcherProperties props, FileLoaderService fileLoader, WordExtractorService wordExtractor, SimilarityCalculatorService calculator) {
        this.props = props;
        this.fileLoader = fileLoader;
        this.wordExtractor = wordExtractor;
        this.calculator = calculator;
    }


    public void runMatching() throws IOException {
        results = new ArrayList<>();
        Set<String> baseLines = fileLoader.loadFile(props.getBaseFilePath());
        Set<String> baseWords = wordExtractor.extractWords(baseLines);

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try {
            File folder = new File(props.getPoolDirectory());
            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("Directory not found: " + folder);
                return;
            }
            File[] files = folder.listFiles();
            if (files == null) return;

            for (File file : files) {
                if (file.getPath().contains(".txt")) {
                    executorService.submit(() -> {
                        try {
                            Set<String> fileLines = fileLoader.loadFile(file.getPath());
                            Set<String> fileWords = wordExtractor.extractWords(fileLines);
                            double score = calculator.calculate(baseWords, fileWords);
                            results.add(new MatchResult(file.getName(), score));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

    public List<MatchResult> getMatchResults() throws IOException {
        if (results == null) {
            runMatching();
        }
        return results;
    }
}