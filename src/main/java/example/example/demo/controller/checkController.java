package example.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class checkController {

    @GetMapping("/")
    public String greet() {
        return "Hey you are connected";
    }
}
