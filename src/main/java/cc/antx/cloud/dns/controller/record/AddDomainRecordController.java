package cc.antx.cloud.dns.controller.record;

import cc.antx.cloud.dns.api.record.AddDomainRecord;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class AddDomainRecordController {
//    @RequestMapping(value = "/record")
//    public JSONObject addDomainRecord(
//            @RequestParam(required = false, value = "") String domainName,
//            @RequestParam(required = false, value = "") String RR,
//            @RequestParam(required = false, value = "") String type,
//            @RequestParam(required = false, value = "") String value,
//            @RequestParam(required = false, value = "") Long TTL,
//            @RequestParam(required = false, value = "") Long priority,
//            @RequestParam(required = false, value = "") String line,
//            @RequestParam(required = false, value = "") String userClientIp,
//            @RequestParam(required = false, value = "") String lang) throws Exception {
//        return AddDomainRecord.AddDomainRecordByAllParams(domainName, RR, type, value, TTL, priority, line, userClientIp, lang);
//    }
}
