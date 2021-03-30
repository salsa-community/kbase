package com.github.danimaniarqsoft.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.Contacto}.
 */
@RestController
@RequestMapping("/api")
public class InfoResource {

    @GetMapping("/info")
    public ResponseEntity<Info> getInfo() {
        return ResponseEntity.ok().body(new Info("2.0.0"));
    }
}

class Info {

    private String version;

    public Info(String version) {
        this.version = version;
    }

    public String getVersion(){
        return this.version;
    }
}
