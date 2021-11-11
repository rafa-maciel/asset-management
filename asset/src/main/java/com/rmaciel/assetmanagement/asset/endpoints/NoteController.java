package com.rmaciel.assetmanagement.asset.endpoints;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.Note;
import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.repositories.NoteRepository;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import com.rmaciel.assetmanagement.asset.endpoints.forms.NoteForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/assets/{assetId}/notes")
@Slf4j
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserAccountRepository userAccountRepository;
    private final AssetRepository assetRepository;

    protected NoteController(NoteRepository noteRepository, UserAccountRepository userAccountRepository, AssetRepository assetRepository) {
        this.noteRepository = noteRepository;
        this.userAccountRepository = userAccountRepository;
        this.assetRepository = assetRepository;
    }

    private Note findNoteOrNull(Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isEmpty())
            return null;

        return noteOptional.get();
    }

    private Asset findAssetOrNull(Long id) {
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isEmpty())
            return null;

        return assetOptional.get();
    }

    private UserAccount findAccountOrNull(String email) {
        Optional<UserAccount> accountOptional = userAccountRepository.findByEmail(email);
        if (accountOptional.isEmpty())
            return null;
        return accountOptional.get();
    }

    @PostMapping
    public ResponseEntity<Note> create(@PathVariable Long assetId, @RequestBody @Valid NoteForm form, Authentication auth) {
        UserDetails userAuth = (UserDetails) auth.getPrincipal();

        UserAccount account = findAccountOrNull(userAuth.getUsername());
        Asset asset = findAssetOrNull(assetId);

        if (asset == null || account == null) return ResponseEntity.badRequest().build();

        Note note = noteRepository.save(form.build(asset, account));
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @DeleteMapping("noteId")
    public ResponseEntity<?> delete(@PathVariable("noteId") Long id) {
        Note note = findNoteOrNull(id);
        if (note == null) return ResponseEntity.badRequest().build();
        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Note>> listAll(@PathVariable Long assetId) {
        Iterable<Note> notes = noteRepository.findAllByAssetId(assetId);
        return ResponseEntity.ok(notes);
    }
}
