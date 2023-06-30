package cc.antx.cloud.dns.demo;

import cc.antx.cloud.dns.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequestMapping(value = "/demo")
public class User {

    @RequestMapping("/user")
    public void queryUser(HttpServletResponse response,
                          @RequestParam(required = false, defaultValue = "zhong") String username) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) {
        String salt = StringUtils.getRandomString(32);
        System.out.println(salt);
        String password = StringUtils.getStringSHA512(salt+"Zjh911");
        System.out.println(password);
    }
}
