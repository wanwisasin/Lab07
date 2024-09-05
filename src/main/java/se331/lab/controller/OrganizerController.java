package se331.lab.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrganizerController {

    final OrganizerService organizerService;

    @GetMapping("/organizers")
    public ResponseEntity<?> getOrganizerLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        List<Organizer> output = null;
        Integer organizerSize = organizerService.getOrganizerSize();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(organizerSize));

        try {
            output = organizerService.getOrganizers(perPage, page);
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.OK);
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/organizers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}
