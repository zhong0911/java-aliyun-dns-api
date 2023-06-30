package cc.antx.cloud.dns.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@RestController
public class Login {
    @RequestMapping("/auth/login")
    public void login(HttpSession session,
                      @RequestParam(required = false, defaultValue = "") String oath_callback) {
        session.setAttribute("username", "zhong");
        session.setAttribute("password", "0a105e8b2278b112d3d3fd59ba291372c84e0eb571f70b09a8dd677ea4ea715c0a4c2a7e6a6f26673c6733b575c068eda10f4bdf8c5960697b88530c0fe22429");
        session.setAttribute("salt", "5nmIPtPdrvKArlbl6hyn0bxmRUA59XuU");

    }
}
