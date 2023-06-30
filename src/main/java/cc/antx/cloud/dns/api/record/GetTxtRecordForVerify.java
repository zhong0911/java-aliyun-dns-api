package cc.antx.cloud.dns.api.record;

import com.aliyun.tea.TeaException;
import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teautil.models.RuntimeOptions;

public class GetTxtRecordForVerify {
    /**
     * 生成txt记录。用于域名、子域名找回、添加子域名验证、批量找回等功能
     * 必填参数: domainName, type
     * 说明: type的值: ADD_SUB_DOMAIN, RETRIEVAL
     *
     * @param params 参数
     * @return 返回结果
     */
    public static JSONObject getTxtRecordForVerify(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            GetTxtRecordForVerifyRequest getTxtRecordForVerifyRequest = GetTxtRecordForVerifyRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            GetTxtRecordForVerifyResponse response = client.getTxtRecordForVerifyWithOptions(getTxtRecordForVerifyRequest, runtime);
            GetTxtRecordForVerifyResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(true);
            info.put("domainName", responseBody.getDomainName());
            info.put("RR", responseBody.getRR());
            info.put("value", responseBody.getValue());
            info.put("requestId", responseBody.getRequestId());
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
