package org.parcial_1.controllers;

import jakarta.validation.Valid;
import org.parcial_1.dtos.DnaRequest;
import org.parcial_1.dtos.DnaResponse;
import org.parcial_1.services.DnaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mutant")
public class DnaController {

    private final DnaService dnaService;

    public DnaController(DnaService dnaService){

        this.dnaService = dnaService;
    }

    @PostMapping
    public ResponseEntity<DnaResponse> checkMutant(@Valid @RequestBody DnaRequest dnaRequest){

        boolean isMutant = dnaService.analyzeDna(dnaRequest.getDna());
        DnaResponse dnaResponse = new DnaResponse(isMutant);
        if (isMutant){
            return ResponseEntity.ok(dnaResponse);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);
        }
    }
}
