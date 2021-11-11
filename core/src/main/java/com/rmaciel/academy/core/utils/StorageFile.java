package com.rmaciel.academy.core.utils;

import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class StorageFile {
    private final Path pathFile;

    public StorageFile(String absolutFilename) {
        pathFile = Paths.get(absolutFilename);
    }

    public Resource getResource() throws MalformedURLException {
        return new UrlResource(pathFile.toUri());
    }

    public String getMimeType() throws IOException {
        return Files.probeContentType(pathFile);
    }

    public String getFileName() {
        return pathFile.getFileName().toString();
    }

}
