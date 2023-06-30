package cc.antx.cloud.dns.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/dns")
public class ConsoleController {
    @RequestMapping(value = "/record")
    public String showRecordPage(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "record";
    }
}