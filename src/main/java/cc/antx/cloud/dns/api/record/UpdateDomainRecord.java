package cc.antx.cloud.dns.api.record;

import com.aliyun.tea.TeaException;
import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teautil.models.RuntimeOptions;

public class UpdateDomainRecord {

    /**
     * 修改域名解析记录
     * 必填参数:
     * 参数名        类型    介绍
     * recordId    String   记录ID
     * RR          String   主机记录
     * type        String   解析记录类型
     * value       String   记录值
     *
     * @param params 参数
     * @return 修改结果
     */
    public static JSONObject updateDomainRecord(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            UpdateDomainRecordRequest updateDomainRecordRequest = UpdateDomainRecordRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            UpdateDomainRecordResponse response = client.updateDomainRecordWithOptions(updateDomainRecordRequest, runtime);
            UpdateDomainRecordResponseBody responseBody = response.getBody();
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
        } catch (Exception exception) {
            return Output.error("500", "Server Error");
        }
    }
}
