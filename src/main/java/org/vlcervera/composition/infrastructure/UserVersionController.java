package org.vlcervera.composition.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/{version}")
public class UserVersionController {
    @GetMapping(path = "/test-version", produces = "application/json")
    public Versions getVersion(@PathVariable("version") Versions version) {
        return version;
    }
}
