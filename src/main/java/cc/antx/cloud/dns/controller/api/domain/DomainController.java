package cc.antx.cloud.dns.controller.api.domain;

import cc.antx.cloud.dns.api.Domain;
import cc.antx.cloud.dns.controller.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
public class DomainController {
    @RequestMapping(value = "/api/domain")

    public void action(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam Map<String, String> allParams
    ) throws IOException {
        PrintWriter writer = response.getWriter();
        if (!Common.checkLoginStatus(request))
            writer.write(String.valueOf(Output.error("No logged in")));
        else {
            JSONObject result;
            String action = allParams.get("action");
            if (action.equals("")) {
                result = Output.error("MissingAction", "Action cannot be empty");
            } else {
                JSONObject params = new JSONObject();
                params.putAll(allParams);
                action = action.toLowerCase();
                switch (action) {
                    case "describedomains": {
                        result = Domain.describeDomains(params);
                        break;
                    }
                    case "describedomainns": {
                        result = Domain.describeDomainNs(params);
                        break;
                    }
                    case "describedomaininfo": {
                        result = Domain.describeDomainInfo(params);
                        break;
                    }
                    case "adddomain": {
                        result = Domain.addDomain(params);
                        break;
                    }
                    case "deletedomain": {
                        result = Domain.deleteDomain(params);
                        break;
                    }
                    default: {
                        result = Output.error(400, "ActionError", "Action does not exist");
                    }
                }
            }
            writer.write(result.toJSONString());
        }
        writer.flush();
        writer.close();
    }
}
