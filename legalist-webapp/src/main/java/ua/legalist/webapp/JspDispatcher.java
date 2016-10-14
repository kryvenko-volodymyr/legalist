package ua.legalist.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.legalist.service.NodeService;
import ua.legalist.service.util.StubDataLoader;

@Controller
public class JspDispatcher {

    @Autowired
    StubDataLoader stubDataLoader;

    @Autowired
    NodeService nodeService;

    @GetMapping("/")
    public String defauldPath() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public Model index(Model model) {
//        model.addAttribute("nodes", nodeService.getAll());
//        model.addAttribute("simpleHierarchy", nodeService.getSimpleHierarchy());
//        model.addAttribute("fullHierarchy", nodeService.getFullHierarchy());
        return model;
    }

    @GetMapping("/user")
    public Model user(Model model) {
        return model;
    }

//    @GetMapping("/login")
//    public Model login (Model model) {
//        return model;
//    }
}
