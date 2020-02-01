package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BankerService;

@RestController
public class BankerController {
    private final BankerService bankerService;

    @Autowired
    public BankerController(BankerService bankerService) {
        this.bankerService = bankerService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "riva";
    }

    @GetMapping("/check")
    public boolean check() {
        return  true;
    }
}
