package cc.antx.cloud.dns.api.record;


import com.alibaba.fastjson.JSON;
import com.aliyun.tea.TeaException;
import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teautil.models.RuntimeOptions;

public class DeleteDomainRecord {
    /**
     * @param params
     * @return
     */
    public static JSONObject deleteDomainRecord(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DeleteDomainRecordRequest deleteDomainRecordRequest = DeleteDomainRecordRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DeleteDomainRecordResponse response = client.deleteDomainRecordWithOptions(deleteDomainRecordRequest, runtime);
            DeleteDomainRecordResponseBody responseBody = response.getBody();
            String recordId = responseBody.getRecordId();
            String requestId = responseBody.getRequestId();
            JSONObject info = new JSONObject(true);
            info.put("recordId", recordId);
            info.put("requestId", requestId);
            return Output.success(info);
        } catch (TeaException teaException) {
            String code = teaException.getCode();
            String message = teaException.getMessage();
            return Output.error(code, message);
        } catch (Exception exception) {
            return Output.error("500", "Server Error");
        }
    }

    public static void main(String[] args) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recordId", "837329278624865280");
        System.out.println(deleteDomainRecord(jsonObject));
    }
}


