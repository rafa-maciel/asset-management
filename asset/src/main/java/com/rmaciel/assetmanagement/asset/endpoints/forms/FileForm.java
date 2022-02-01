package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class FileForm {
    private String name;
    private String note;
    private MultipartFile file;

    public File build(Asset asset, String pathname) {
        File fileCreated = new File(name, note, pathname);
        fileCreated.setAsset(asset);

        return file;
    }
}
