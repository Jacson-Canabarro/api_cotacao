package br.com.jacson.api_exchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ApiController {


    @GetMapping
    public String swagger() {

        return "redirect:swagger-ui.html";
    }

}
