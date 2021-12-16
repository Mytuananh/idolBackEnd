package com.codegym.quanlyidol.controller;

import com.codegym.quanlyidol.model.Idol;
import com.codegym.quanlyidol.model.Type;
import com.codegym.quanlyidol.service.idol.IdolService;
import com.codegym.quanlyidol.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("")
    public ResponseEntity<Iterable<Type>> findAll() {
        return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> findById(@PathVariable Long id) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (!typeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type> addType(@RequestBody Type type) {
        return new ResponseEntity<>(typeService.save(type), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Type> updateType(@RequestBody Type type) {
        return new ResponseEntity<>(typeService.save(type), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Type> deleteById(@PathVariable Long id) {
        typeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
