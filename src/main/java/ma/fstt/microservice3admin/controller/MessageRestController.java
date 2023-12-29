package ma.fstt.microservice3admin.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/service3")

public class MessageRestController {


    @GetMapping("/sayhello/{message}")
    public String SayHello(@PathVariable String message){

        return "Hello : "+ message;
    }

    @GetMapping("/{msg}")
    public String Service3(@PathVariable String msg){

        return "Microservice 3 AFTER DOCKERFILE: "+ msg;
    }

}
