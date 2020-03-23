package com.zufar.requestsystem.controller;

import com.zufar.requestsystem.dto.RequestDTO;
import com.zufar.requestsystem.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@RestController
@RequestMapping(value = "requests", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public @ResponseBody
    Collection<RequestDTO> getAll() {
            return this.requestService.getAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    RequestDTO getById(@PathVariable Long id) {
        return this.requestService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        this.requestService.deleteById(id);
    }

    @PostMapping
    public @ResponseBody
    RequestDTO save(@RequestBody RequestDTO order) {
        return this.requestService.save(order);
    }

    @PutMapping
    public @ResponseBody
    RequestDTO update(@RequestBody RequestDTO order) {
        return this.requestService.update(order);
    }
}
