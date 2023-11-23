package com.uadybank.uadybankbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface iController<Entity> {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(@PathVariable Long id);

    ResponseEntity<?> save(@RequestBody Entity entity);

    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Entity entity);

    ResponseEntity<?> delete(@PathVariable Long id);

}
