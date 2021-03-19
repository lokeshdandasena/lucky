package com.stackroute.controller;


import com.stackroute.model.WordMeaning;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("api/v1/dictionary")
@CrossOrigin(value = "*")
public class DictionaryController {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/meaning/{word}")
    public ResponseEntity<?> getMeaning(@PathVariable String word) {
        try {
            String url = "https://api.dictionaryapi.dev/api/v2/entries/en_US/" + word;
            Object wordObjects =restTemplate().getForObject(url, Object.class);
            return new ResponseEntity<>(Arrays.asList(wordObjects), HttpStatus.OK);
//            return new ResponseEntity<>(wordObjects,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Meaning NOt Found", HttpStatus.NOT_FOUND);
        }

    }
}
