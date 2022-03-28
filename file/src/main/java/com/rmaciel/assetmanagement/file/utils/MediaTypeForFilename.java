package com.rmaciel.assetmanagement.file.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;

@Slf4j
public abstract  class MediaTypeForFilename {

    public static MediaType getType(ServletContext context, String filename) {
        String mimeType = context.getMimeType(filename);
        try {
            return MediaType.parseMediaType(mimeType);
        } catch (Exception e) {
            log.info(e.toString());
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
