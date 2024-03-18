package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class MainController {

    private static final Logger logger = LogManager.getLogger("HelloWorld");

    @PostMapping("/logger")
    public ResponseEntity<String> logs(@RequestHeader(name="X-Api-Version", required = false) String apiVersion) {
        try {
            if (apiVersion != null && !apiVersion.isEmpty()) {
                logger.info("Received a request for API version " + apiVersion);
                return ResponseEntity.ok("Don't attack me!!!");
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing HTTP Header - 'X-Api-Version'");
            }
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getReason());
        }
    }

    @GetMapping("/logger")
    public ResponseEntity<String> getMethodNotAllowed() {
        try {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "GET method not allowed for this endpoint");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getReason());
        }
    }


    @GetMapping("/")
    public String index() {
            return "55 32 56 6a 64 58 4a 70 64 48 6b 67 64 47 68 79 62 33 56 6e 61 43 42 76 59 6e 4e 6a 64 58 4a 70 64 48 6b 67 5a 47 39 6c 63 79 42 75 62 33 51 67 59 57 78 33 59 58 6c 7a 49 48 64 76 63 6d 73 75 43 6b 64 76 64 43 42 30 62 79 41 76 62 47 39 6e 5a 32 56 79 49 47 35 76 64 79 41 68 49 51 3d 3d";
    }

}