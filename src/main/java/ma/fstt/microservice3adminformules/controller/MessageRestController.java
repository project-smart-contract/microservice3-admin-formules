package ma.fstt.microservice3adminformules.controller;


import org.springframework.web.bind.annotation.*;

import jakarta.persistence.*;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/formule")

public class MessageRestController {


    @GetMapping("/sayhello/{message}")
    public String SayHello(@PathVariable String message){

        return "Hello : "+ message;
    }

//    @GetMapping("/{msg}")
//    public String Service3(@PathVariable String msg){
//
//        return "Microservice 3 AFTER DOCKERFILE: "+ msg;
//    }

}
