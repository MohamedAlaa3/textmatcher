package com.evision.textmatcher.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileLoaderService {
    public Set<String> loadFile(String path) throws IOException {
        System.out.println("path " + path);

        Path filePath = Path.of(path);
        if (!Files.exists(filePath)) {
            System.err.println("File not found: " + path);
            return null;
        }

        return Files.lines(filePath, StandardCharsets.UTF_8).collect(Collectors.toSet());
    }
}