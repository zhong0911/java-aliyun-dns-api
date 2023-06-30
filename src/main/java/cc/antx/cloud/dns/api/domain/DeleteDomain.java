package cc.antx.cloud.dns.api.domain;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.DeleteDomainRequest;
import com.aliyun.alidns20150109.models.DeleteDomainResponse;
import com.aliyun.alidns20150109.models.DeleteDomainResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;

public class DeleteDomain {
    /**
     * 根据传入参数删除域名
     * 必填参数: domainName
     *
     * @return 删除结果
     */
    public static JSONObject deleteDomain(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DeleteDomainRequest deleteDomainRequest = DeleteDomainRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DeleteDomainResponse response = client.deleteDomainWithOptions(deleteDomainRequest, runtime);
            DeleteDomainResponseBody responseBody = response.getBody();
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
