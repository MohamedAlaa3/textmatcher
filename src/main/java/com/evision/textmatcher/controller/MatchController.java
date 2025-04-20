package com.evision.textmatcher.controller;

import com.evision.textmatcher.model.MatchResult;
import com.evision.textmatcher.service.MatchingOrchestrator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MatchController {

    private final MatchingOrchestrator orchestrator;

    public MatchController(MatchingOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    /**
     * Endpoint to fetch match results.
     * It runs the matching process if not already done and returns the results.
     *
     * @return List of MatchResult objects
     */
    @GetMapping("/api/match-results")
    public ResponseEntity<List<MatchResult>> getMatchResults() {
        try {
            List<MatchResult> matchResults = orchestrator.getMatchResults();
            return ResponseEntity.ok(matchResults);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
