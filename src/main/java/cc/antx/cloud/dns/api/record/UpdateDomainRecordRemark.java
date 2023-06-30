package cc.antx.cloud.dns.api.record;

import com.aliyun.tea.TeaException;
import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teautil.models.RuntimeOptions;

public class UpdateDomainRecordRemark {
    /**
     * 修改域名解析记录的备注
     * 必填参数: recordId
     * 可选参数: remark
     *
     * @param params 参数
     * @return 修改结果
     */
    public static JSONObject updateDomainRecordRemark(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            UpdateDomainRecordRemarkRequest updateDomainRecordRemarkRequest = UpdateDomainRecordRemarkRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            UpdateDomainRecordRemarkResponse response = client.updateDomainRecordRemarkWithOptions(updateDomainRecordRemarkRequest, runtime);
            UpdateDomainRecordRemarkResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(true);
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
