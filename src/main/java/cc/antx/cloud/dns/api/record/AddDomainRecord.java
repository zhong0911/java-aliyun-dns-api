package cc.antx.cloud.dns.api.record;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;

public class AddDomainRecord {

    /**
     * 根据传入参数添加解析记录
     * 必填参数: domainName, RR, type, value
     *
     * @param params 参数
     * @return 记录添加结果
     */
    public static JSONObject addDomainRecord(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            AddDomainRecordRequest addDomainRecordRequest = AddDomainRecordRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            AddDomainRecordResponse response = client.addDomainRecordWithOptions(addDomainRecordRequest, runtime);
            AddDomainRecordResponseBody responseBody = response.getBody();
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
