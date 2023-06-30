package cc.antx.cloud.dns.api.record;


import com.aliyun.tea.TeaException;
import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teautil.models.RuntimeOptions;

public class DeleteDomainRecord {
    /**
     * 根据传入参数删除解析记录
     * 必填参数: recordId
     *
     * @param params 参数
     * @return 记录删除结果
     */
    public static JSONObject deleteDomainRecord(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DeleteDomainRecordRequest deleteDomainRecordRequest = DeleteDomainRecordRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DeleteDomainRecordResponse response = client.deleteDomainRecordWithOptions(deleteDomainRecordRequest, runtime);
            DeleteDomainRecordResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(true);
            info.putAll(responseBody.toMap());
            return Output.success(info);
        } catch (TeaException teaException) {
            String code = teaException.getCode();
            String message = teaException.getMessage();
            return Output.error(code, message);
        } catch (Exception exception) {
            return Output.error(500, "ServerError", "Server Error");
        }
    }
}


