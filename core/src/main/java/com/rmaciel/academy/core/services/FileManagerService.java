package com.rmaciel.academy.core.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileManagerService {
    @Value("${assetmanagement.files.pathname}")
    private String pathname;

    public String save(String filename, byte[] bytes) throws IOException {
        String absolutPath = pathname + filename;
        File newFile = new File(absolutPath);
        newFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(bytes);
        fos.close();

        return absolutPath;
    }

    public void deleteFile(String absolutFilename) throws IOException {
        Files.delete(Paths.get(absolutFilename));
    }

}
