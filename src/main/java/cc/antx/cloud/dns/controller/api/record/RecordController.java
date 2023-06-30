package cc.antx.cloud.dns.controller.api.record;

import cc.antx.cloud.dns.controller.Common;
import cc.antx.cloud.dns.utils.Output;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@RestController
@RequestMapping(value = "/api")
public class RecordController {
    @RequestMapping(value = "/record")
    public static void action(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        if (!Common.checkLoginStatus(request)) {
            writer.write(String.valueOf(Output.error("No logged in")));
        } else {

        }
    }
}
