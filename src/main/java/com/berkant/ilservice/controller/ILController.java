package com.berkant.ilservice.controller;

import com.berkant.ilservice.Exception.IlAlreadyExistsException;
import com.berkant.ilservice.Exception.IlNotFoundException;
import com.berkant.ilservice.model.Il;
import com.berkant.ilservice.service.IlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class ILController {
    private  final IlService ilService;

    @GetMapping()
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){
        return new ResponseEntity<>(ilService.getIller(name),OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Il>getIl(@PathVariable String id) {
        return new ResponseEntity<>(ilService.getIlById( id),OK);
    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){
        return new ResponseEntity<>(ilService.createIl(newIl),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putIl(@PathVariable String id,@RequestBody Il newIl){
        ilService.updateIl(id, newIl);
        return new ResponseEntity<>(OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id){
        ilService.deleteIl(id);
        return new ResponseEntity<>(OK);
    }

    private Il getIlById(String id){
        return ilService.getIlById(id);
    }
    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),NOT_FOUND);
    }
    @ExceptionHandler(IlAlreadyExistsException.class)
    public ResponseEntity<String> handleIlAlreadyExistsException(IlAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(),CONFLICT);
    }
}

