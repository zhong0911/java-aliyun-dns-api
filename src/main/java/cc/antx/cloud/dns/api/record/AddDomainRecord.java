package cc.antx.cloud.dns.api.record;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;


public class AddDomainRecord {
    public static JSONObject addDomainRecord(JSONObject params) throws Exception {
        Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        AddDomainRecordRequest addDomainRecordRequest = AddDomainRecordRequest.build(params);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            AddDomainRecordResponse response = client.addDomainRecordWithOptions(addDomainRecordRequest, runtime);
            AddDomainRecordResponseBody responseBody = response.getBody();
            String recordId = responseBody.getRecordId();
            String requestId = responseBody.getRequestId();
            JSONObject info = new JSONObject(true);
            info.put("recordId", recordId);
            info.put("requestId", requestId);
            return Output.success(info);
        } catch (TeaException error) {
            String code = error.getCode();
            String message = error.getMessage();
            return Output.error(code, message);
        }
    }
}
