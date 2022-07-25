package com.fourkbsys.eventms.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch/events")
public class BatchController {

    @PostMapping
    public ResponseEntity<String> proccessEvents(){

        return new ResponseEntity("Ok", HttpStatus.OK);
    }

}
