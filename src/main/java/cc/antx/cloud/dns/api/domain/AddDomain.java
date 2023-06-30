package cc.antx.cloud.dns.api.domain;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.aliyun.tea.TeaException;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teautil.models.RuntimeOptions;

public class AddDomain {
    /**
     * 根据传入参数添加域名
     * 必填参数: domainName
     *
     * @return 线路列表
     */
    public static JSONObject addDomain(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            AddDomainRequest addDomainRequest = AddDomainRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            AddDomainResponse response = client.addDomainWithOptions(addDomainRequest, runtime);
            AddDomainResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(true);
            info.putAll(responseBody.toMap());
            return Output.success(info);
        } catch (TeaException error) {
            String code = error.getCode();
            String message = error.getMessage();
            return Output.error(code, message);
        } catch (Exception exception) {
            return Output.error(500, "ServerError", "Server Error");
        }
    }
}
