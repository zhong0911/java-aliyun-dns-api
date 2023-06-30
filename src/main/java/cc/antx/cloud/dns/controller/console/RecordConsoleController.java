package cc.antx.cloud.dns.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RecordConsoleController {
    @RequestMapping(value = "/dns/record/{domainName}")
    public String showRecordPage(
            Model model,
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("domainName") String domainName
    ) {
        model.addAttribute("domainName", domainName);
        return "record";
    }
}