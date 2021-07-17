package main.controllers;

import main.service.DefaultControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class DefaultController {

    @Autowired
    private DefaultControllerService defaultControllerService;

@RequestMapping("/")
public String index(Model model ){return defaultControllerService.index(model);}

}
