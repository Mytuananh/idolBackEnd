package com.codegym.quanlyidol.controller;

import com.codegym.quanlyidol.model.Idol;
import com.codegym.quanlyidol.service.idol.IdolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.env.Environment;
import sun.misc.IOUtils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/idols")
public class IdolController {
    @Autowired
    private IdolService idolService;

    @Autowired
    Environment env;

    @GetMapping("")
    public ResponseEntity<Iterable<Idol>> findAll() {
        return new ResponseEntity<>(idolService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Idol> findById(@PathVariable Long id) {
        Optional<Idol> idolOptional = idolService.findById(id);
        return idolOptional.map(idol -> new ResponseEntity<>(idol, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping
//    public ResponseEntity<Idol> addIdol(@RequestBody Idol idol) {
//        return new ResponseEntity<>(idolService.save(idol), HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<Idol> saveArtist(@RequestPart("file") MultipartFile file, @RequestPart("newIdol") String idol) {
        String file1 = file.getOriginalFilename();
        try {
            Idol idol1 = new ObjectMapper().readValue(idol, Idol.class);
            idol1.setImage(file1);
            idolService.save(idol1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String fileUpload = env.getProperty("upload.path");
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + file1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Idol> updateIdol(@RequestPart("file") MultipartFile file, @PathVariable Long id) {
        Optional<Idol> idol = idolService.findById(id);
        if (!idol.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String fileUpload = env.getProperty("upload.path");
        File file1 = new File(fileUpload + idol.get().getImage());
        try {
            FileInputStream inputStream = new FileInputStream(file1);
            try {
                file = new MockMultipartFile("fileNew",file1.getName(),"jpe,.../image", IOUtils.readNBytes(inputStream,1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Idol> deleteById(@PathVariable Long id) {
        idolService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
