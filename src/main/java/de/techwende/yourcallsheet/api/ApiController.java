package de.techwende.yourcallsheet.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controls the api that clients may use.
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class ApiController {
    // get, post, put, delete

    /**
     * Simple method to fetch the central callsheet.
     *
     * @return callsheet JSON representation
     */
    @GetMapping("/fetch-callsheet")
    public String fetchCallsheet() {
        log.debug("fetched a callsheet");
        boolean isFound = true;
        if (!isFound) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Callsheet not found");
        }
        return
                """
                        {
                            "date": "2026-02-18"
                        }
                        """;
    }
}
