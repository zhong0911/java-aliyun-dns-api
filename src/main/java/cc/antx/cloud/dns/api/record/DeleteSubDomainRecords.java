package cc.antx.cloud.dns.api.record;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;

public class DeleteSubDomainRecords {
    /**
     * 根据传入参数删除主机记录对应的解析记录
     * 必填参数: domainName, RR
     *
     * @param params 参数
     * @return 记录删除结果
     */
    public static JSONObject deleteSubDomainRecords(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DeleteSubDomainRecordsRequest deleteSubDomainRecordsRequest = DeleteSubDomainRecordsRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DeleteSubDomainRecordsResponse response = client.deleteSubDomainRecordsWithOptions(deleteSubDomainRecordsRequest, runtime);
            DeleteSubDomainRecordsResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(true);
            info.put("requestId", responseBody.getRequestId());
            info.put("RR", responseBody.getRR());
            info.put("totalCount", responseBody.getTotalCount());
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
