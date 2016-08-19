package ua.legalist.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    
    @GetMapping("/")
    public String defauldPath () {
        return "redirect:/index";
    }
    
    @GetMapping("/index")
    public Model index (Model model) {
        return model;
    }
}
