package com.rmaciel.assetmanagement.location.endpoints;

import com.rmaciel.academy.core.models.Location;
import com.rmaciel.academy.core.repositories.LocationRepository;
import com.rmaciel.academy.core.specifications.LocationSpecifications;
import com.rmaciel.assetmanagement.location.endpoints.forms.LocationCreateForm;
import com.rmaciel.assetmanagement.location.endpoints.forms.LocationUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    private Location findLocationOrNull(Long id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if (optionalLocation.isEmpty())
            return null;

        return optionalLocation.get();
    }

    @PostMapping
    public ResponseEntity<Location> create(@RequestBody @Valid LocationCreateForm form) {
        Location location = locationRepository.save(form.build());
        return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@PathVariable Long id, @RequestBody @Valid LocationUpdateForm form) {
        Location savedLocation = findLocationOrNull(id);
        if (savedLocation == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Location location = locationRepository.save(form.updateFrom(savedLocation));
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Location savedLocation = findLocationOrNull(id);
        if (savedLocation == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        locationRepository.delete(savedLocation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> details(@PathVariable Long id) {
        Location location = findLocationOrNull(id);
        if (location == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(location);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Location>> search(Pageable pageable, String title) {
        Specification<Location> specs = LocationSpecifications.likeTitle(title);
        Page<Location> page = locationRepository.findAll(specs, pageable);
        return ResponseEntity.ok(page);
    }


}
