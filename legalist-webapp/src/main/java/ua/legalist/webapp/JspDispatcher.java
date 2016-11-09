package ua.legalist.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.legalist.service.NodeService;

@Controller
public class JspDispatcher {

    @Autowired
    NodeService nodeService;

//    @GetMapping("/")
//    public String defauldPath() {
//        return "redirect:/index";
//    }

    @GetMapping("/index")
    public Model index(Model model) {
        return model;
    }

    @GetMapping("/user")
    public Model user(Model model) {
        return model;
    }

//    @RequestMapping("/login")
//    public Model login (Model model) {
//        return model;
//    }
}
