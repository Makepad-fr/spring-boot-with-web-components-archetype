/* (C)2022 Makepad SARL*/
package io.makepad.visulog.web.backend.controller;

import io.makepad.core.dto.SampleDto;
import io.makepad.visulog.web.backend.utils.MediaType;
import io.makepad.visulog.web.frontend.TemplateManager;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "/repositories/{id}", produces = MediaType.JSON)
    public SampleDto getRepositoryById(@PathVariable String id) {
        // TODO: Complete method implementation
        SampleDto repositoryToReturn = new SampleDto(id);
        return repositoryToReturn;
    }

    @GetMapping(path = "/", produces = MediaType.HTML)
    public String getHomePage() {
        try {
            return TemplateManager.getTemplate("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
