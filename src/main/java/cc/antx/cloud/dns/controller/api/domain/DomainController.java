package cc.antx.cloud.dns.controller.api.domain;

import cc.antx.cloud.dns.api.Domain;
import cc.antx.cloud.dns.controller.Common;
import cc.antx.cloud.dns.mapper.DNSDomainMapper;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RestController
public class DomainController {
    @RequestMapping(value = "/api/domain")

    public void action(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam Map<String, String> allParams
    ) throws IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("uid");
        PrintWriter writer = response.getWriter();
        if (!Common.checkLoginStatus(request))
            writer.write(String.valueOf(Output.error("ConsoleNeedLogin", "No logged in")));
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
                        //result = describeDomainsByUserId(params, Integer.parseInt(userId));
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

    public static JSONObject describeDomainsByUserId(Map<String, Object> allParams, int userId) {
        JSONObject params = new JSONObject();
        params.putAll(allParams);
        JSONArray aliyunDomainsOnly = Domain.describeDomainsOnly(params);
        JSONArray domains = new JSONArray();
        JSONObject aliyunDomainInfo = Domain.describeDomains(params);
        JSONObject jsonObject = DNSDomainMapper.getDnsDomainByUid(userId).toJSONObject();
        JSONArray info = new JSONArray();
        for (Object obj : jsonObject.getJSONArray("Domains")) {
            String domainName = JSON.parseObject(obj.toString()).getString("DomainName");
            if (aliyunDomainsOnly.contains(domainName)) {
                JSONArray json = aliyunDomainInfo.getJSONObject("info").getJSONObject("Domains").getJSONArray("Domain");
                for (Object o :
                        json) {
                    JSONObject object = new JSONObject((Map<String, Object>) o);
                    if (object.getString("DomainName").equals(domainName)) {
                        info.add(object);
                    }
                }
            }
        }
        JSONObject Domain = new JSONObject(true);
        Domain.put("Domain", info);
        JSONObject result = new JSONObject(true);
        result.put("Domains", Domain);
        return Output.success(result);
    }

    public static void main(String[] args) {
        System.out.println(Domain.describeDomains(new JSONObject()));
        Map<String, Object> map = new HashMap<>();
        System.out.println(describeDomainsByUserId(map, 1));
    }
}






