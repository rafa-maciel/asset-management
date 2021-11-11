package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.Note;
import com.rmaciel.academy.core.models.UserAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class NoteForm {
    private String text;

    public Note build(Asset asset, UserAccount author) {
        return new Note(asset, text, author);
    }
}
